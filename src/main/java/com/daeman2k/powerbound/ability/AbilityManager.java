package com.daeman2k.powerbound.ability;

import com.daeman2k.powerbound.PowerboundPlugin;
import com.daeman2k.powerbound.config.Ability;
import com.daeman2k.powerbound.config.StrengthConfig;
import com.daeman2k.powerbound.cooldown.CooldownManager;
import com.daeman2k.powerbound.damage.DamageService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;
import java.util.Optional;

public class AbilityManager {
    private final PowerboundPlugin plugin;
    private final StrengthConfig config;
    private final CooldownManager cooldowns;
    private final DamageService damageService;

    public AbilityManager(PowerboundPlugin plugin, CooldownManager cooldowns, DamageService damageService) {
        this.plugin = plugin;
        this.config = plugin.getStrengthConfig();
        this.cooldowns = cooldowns;
        this.damageService = damageService;
    }

    public boolean triggerAbility(Player player, String tier) {
        String weapon = detectWeaponKey(player);
        if (weapon == null) return false;
        Map<String, Map<String, Ability>> abilities = config.getAbilities();
        Map<String, Ability> weaponAbilities = abilities.get(weapon);
        if (weaponAbilities == null) return false;
        Ability ability = weaponAbilities.get(tier.toLowerCase());
        if (ability == null) return false;

        String cdKey = "ability." + weapon + "." + tier;
        if (cooldowns.isOnCooldown(player.getUniqueId(), cdKey)) return false;

        // Find target via ray trace
        Optional<Entity> hit = Optional.ofNullable(player.getTargetEntity(6));
        if (hit.isEmpty() || !(hit.get() instanceof LivingEntity)) return false;
        LivingEntity target = (LivingEntity) hit.get();

        double damage = ability.getDamage();
        // Apply strength multiplier (abilities also scale)
        int str = plugin.getStrengthManager().getStrength(player.getUniqueId());
        damage = damageService.computeScaledDamage(damage, str);

        // Specific per-ability behaviors
        String eff = ability.getEffect();
        if ("aoe".equalsIgnoreCase(eff)) {
            for (Entity e : target.getNearbyEntities(3,3,3)) {
                if (e instanceof LivingEntity && !e.equals(player)) {
                    ((LivingEntity) e).damage(damage, player);
                }
            }
        } else if ("cleave".equalsIgnoreCase(eff) && "ultimate".equalsIgnoreCase(tier) && "sword".equals(weapon)) {
            // cleave: damage targets in a short cone around the main target
            for (Entity e : target.getNearbyEntities(4,2,4)) {
                if (e instanceof LivingEntity && !e.equals(player)) {
                    ((LivingEntity) e).damage(damage * 0.8, player);
                }
            }
            // main target receives full damage
            target.damage(damage, player);
        } else {
            // Heavy sword ignores armor
            if ("sword".equals(weapon) && "heavy".equalsIgnoreCase(tier) ) {
                damageService.applyDirectDamage(target, damage);
            } else if ("crossbow".equals(weapon) && "ultimate".equalsIgnoreCase(tier)) {
                // execute if under 50%
                if (target.getHealth() < target.getMaxHealth() * 0.5) {
                    target.setHealth(0);
                } else {
                    target.damage(damage, player);
                }
            } else if ("trident".equals(weapon) && "heavy".equalsIgnoreCase(tier)) {
                // lightning
                target.getWorld().strikeLightning(target.getLocation());
                target.damage(damage, player);
            } else {
                target.damage(damage, player);
            }
        }

        // sample effect parsing (glow, slowness, weakness, wither)
        if (eff != null) {
            if (eff.contains("slowness")) target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 1));
            if (eff.contains("weakness")) target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 60, 1));
            if (eff.contains("wither")) target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
            if (eff.contains("glow")) Bukkit.getScheduler().runTask(plugin, () -> target.setGlowing(true));
            if ("shield_disable".equalsIgnoreCase(eff) && target instanceof Player) {
                // temporarily remove shield if present
                Player tp = (Player) target;
                org.bukkit.inventory.ItemStack off = tp.getInventory().getItemInOffHand();
                if (off != null && off.getType().name().contains("SHIELD")) {
                    tp.getInventory().setItemInOffHand(new org.bukkit.inventory.ItemStack(org.bukkit.Material.AIR));
                    final org.bukkit.inventory.ItemStack restore = off.clone();
                    Bukkit.getScheduler().runTaskLater(plugin, () -> tp.getInventory().setItemInOffHand(restore), 60L); // restore after 3s
                }
            }

            if ("self_slowness".equalsIgnoreCase(eff)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 1));
            }
        }

        int cd = ability.getCooldown_seconds();
        if (cd <= 0) cd = 5;
        cooldowns.setCooldown(player.getUniqueId(), cdKey, cd * 1000L);

        // play particle and sound via EffectsUtil
        com.daeman2k.powerbound.effects.EffectsUtil effects = new com.daeman2k.powerbound.effects.EffectsUtil(plugin);
        effects.playParticle(target.getLocation(), ability.getParticle(), ability.getParticle_count(), ability.getParticle_offset());
        effects.playSound(target.getLocation(), ability.getSound(), (float) ability.getSound_volume(), (float) ability.getSound_pitch());
        return true;
    }

    private String detectWeaponKey(Player player) {
        String name = player.getInventory().getItemInMainHand().getType().name().toLowerCase();
        if (name.contains("sword")) return "sword";
        if (name.contains("axe")) return "axe";
        if (name.contains("bow")) return "bow";
        if (name.contains("crossbow")) return "crossbow";
        if (name.contains("trident")) return "trident";
        return null;
    }
}
