package com.daeman2k.powerbound.listeners;

import com.daeman2k.powerbound.PowerboundPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {
    private final PowerboundPlugin plugin;

    public InventoryListener(PowerboundPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!event.getView().getTitle().contains("Strength")) return;
        event.setCancelled(true);
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null) return;
        // Simple handling: check display name
        if (clicked.getItemMeta() == null || !clicked.getItemMeta().hasDisplayName()) return;
        String name = clicked.getItemMeta().getDisplayName();
        if (name.contains("Light")) {
            if (event.getWhoClicked() instanceof org.bukkit.entity.Player) {
                org.bukkit.entity.Player p = (org.bukkit.entity.Player) event.getWhoClicked();
                plugin.getAbilityManager().triggerAbility(p, "light");
            }
        } else if (name.contains("Medium")) {
            if (event.getWhoClicked() instanceof org.bukkit.entity.Player) {
                org.bukkit.entity.Player p = (org.bukkit.entity.Player) event.getWhoClicked();
                plugin.getAbilityManager().triggerAbility(p, "medium");
            }
        } else if (name.contains("Heavy")) {
            if (event.getWhoClicked() instanceof org.bukkit.entity.Player) {
                org.bukkit.entity.Player p = (org.bukkit.entity.Player) event.getWhoClicked();
                plugin.getAbilityManager().triggerAbility(p, "heavy");
            }
        } else if (name.contains("Ultimate")) {
            if (event.getWhoClicked() instanceof org.bukkit.entity.Player) {
                org.bukkit.entity.Player p = (org.bukkit.entity.Player) event.getWhoClicked();
                plugin.getAbilityManager().triggerAbility(p, "ultimate");
            }
        } else if (name.contains("Reroll")) {
            event.getWhoClicked().sendMessage("Reroll is not implemented yet.");
        } else if (name.contains("Help")) {
            event.getWhoClicked().sendMessage("Open help panel: Gains, PvP rules, Boss effects.");
        } else {
            event.getWhoClicked().sendMessage("Clicked: " + name);
        }
    }
}
