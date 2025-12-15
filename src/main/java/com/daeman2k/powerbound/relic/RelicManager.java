package com.daeman2k.powerbound.relic;

import com.daeman2k.powerbound.PowerboundPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RelicManager {
    private final Map<UUID, String> owner = new ConcurrentHashMap<>();
    private final PowerboundPlugin plugin;

    public RelicManager(PowerboundPlugin plugin) {
        this.plugin = plugin;
    }

    public void claim(Player p, String relicKey) {
        owner.put(p.getUniqueId(), relicKey);
        String title = plugin.getStrengthConfig().getBossRelics().getOrDefault(relicKey, null) != null ? plugin.getStrengthConfig().getBossRelics().get(relicKey).getTitle() : relicKey;
        Bukkit.broadcastMessage(p.getName() + " has claimed the " + title);
        // play sound, title, minimal particles
    }

    public boolean has(Player p, String relicKey) {
        return relicKey.equalsIgnoreCase(owner.get(p.getUniqueId()));
    }

    public boolean triggerActive(Player p) {
        String key = owner.get(p.getUniqueId());
        if (key == null) return false;
        com.daeman2k.powerbound.config.BossRelic relic = plugin.getStrengthConfig().getBossRelics().get(key);
        if (relic == null || relic.getActive() == null) return false;

        String cdKey = "relic.active." + key;
        if (plugin.getCooldownManager().isOnCooldown(p.getUniqueId(), cdKey)) return false;

        ActiveEffect active = relic.getActive();
        double damage = active.getDamage();

        if (active.getEffects() != null) {
            for (String s : active.getEffects()) {
                if (s.contains("slowness")) p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
                if (s.contains("weakness")) p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 60, 1));
                if (s.contains("wither")) p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
            }
        }

        // Play particle and sound if set
        if (active.getParticle() != null) {
            try {
                org.bukkit.Particle pType = org.bukkit.Particle.valueOf(active.getParticle().toUpperCase());
                p.getWorld().spawnParticle(pType, p.getLocation(), 10);
            } catch (Exception ignored) {}
        }
        if (active.getSound() != null) {
            try { p.getWorld().playSound(p.getLocation(), org.bukkit.Sound.valueOf(active.getSound()), 1f, 1f); } catch (Exception ignored) {}
        }

        // set cooldown
        int cdSeconds = active.getCooldown();
        plugin.getCooldownManager().setCooldown(p.getUniqueId(), cdKey, cdSeconds * 1000L);

        p.sendMessage("Activated relic: " + key);
        return true;
    }
}
