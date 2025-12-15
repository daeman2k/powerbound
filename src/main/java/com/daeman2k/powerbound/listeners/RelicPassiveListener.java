package com.daeman2k.powerbound.listeners;

import com.daeman2k.powerbound.PowerboundPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RelicPassiveListener implements Listener {
    private final PowerboundPlugin plugin;

    public RelicPassiveListener(PowerboundPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player attacker = (Player) event.getDamager();
        Entity ent = event.getEntity();
        if (!(ent instanceof org.bukkit.entity.LivingEntity)) return;

        // Crimson Wither Star passive: Wither I on hit (1s)
        if (plugin.getRelicManager().has(attacker, "crimson_wither_star")) {
            ((org.bukkit.entity.LivingEntity) ent).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20, 0));
        }
    }
}
