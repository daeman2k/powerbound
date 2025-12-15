package com.daeman2k.powerbound.listeners;

import com.daeman2k.powerbound.PowerboundPlugin;
import com.daeman2k.powerbound.damage.DamageService;
import com.daeman2k.powerbound.config.StrengthConfig;
import com.daeman2k.powerbound.strength.StrengthManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {
    private final PowerboundPlugin plugin;
    private final DamageService damageService;
    private final StrengthManager strengthManager;
    private final StrengthConfig config;

    public DamageListener(PowerboundPlugin plugin, StrengthManager strengthManager, DamageService damageService) {
        this.plugin = plugin;
        this.config = plugin.getStrengthConfig();
        this.damageService = damageService;
        this.strengthManager = strengthManager;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player p = (Player) event.getDamager();
        Material mat = p.getInventory().getItemInMainHand().getType();

        double base = 1.0;
        Object swordBase = config.getBaseDamage().get("sword");
        Object axeBase = config.getBaseDamage().get("axe");
        Object crossbowBase = config.getBaseDamage().get("crossbow");
        Object tridentBase = config.getBaseDamage().get("trident");

        if (mat.name().contains("SWORD") && swordBase instanceof Number) {
            base = ((Number) swordBase).doubleValue();
        } else if (mat.name().contains("AXE") && axeBase instanceof Number) {
            base = ((Number) axeBase).doubleValue();
            int axeMod = config.getAxeDamageModifierPercent();
            base = base * (1.0 + axeMod / 100.0);
        } else if (mat.name().contains("CROSSBOW") && crossbowBase instanceof Number) {
            base = ((Number) crossbowBase).doubleValue();
        } else if (mat.name().contains("TRIDENT") && tridentBase instanceof Number) {
            base = ((Number) tridentBase).doubleValue();
        } else {
            // leave event damage unchanged for variable / other weapon types
            return;
        }

        int strength = strengthManager.getStrength(p.getUniqueId());
        double scaled = damageService.computeScaledDamage(base, strength);
        event.setDamage(scaled);
    }
}
