package com.daeman2k.powerbound.ui;

import com.daeman2k.powerbound.PowerboundPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public final class StrengthGui {
    public static void openFor(PowerboundPlugin plugin, Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "Strength");

        // Fill glass border
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta gm = glass.getItemMeta(); gm.setDisplayName(" "); glass.setItemMeta(gm);
        for (int i=0;i<54;i++) inv.setItem(i, glass);

        // Center slot 13 (0-based) show strength
        ItemStack center = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta cm = center.getItemMeta();
        cm.setDisplayName(ChatColor.GOLD + "Strength: " + ChatColor.AQUA + plugin.getStrengthManager().getStrength(player.getUniqueId()));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Tier: HEAVY");
        lore.add(ChatColor.GRAY + "Damage: +15%");
        cm.setLore(lore);
        center.setItemMeta(cm);
        inv.setItem(13, center);

        // Ability slots (example)
        inv.setItem(20, makeNamed(Material.YELLOW_CONCRETE, ChatColor.YELLOW + "Light"));
        inv.setItem(21, makeNamed(Material.ORANGE_CONCRETE, ChatColor.GOLD + "Medium"));
        inv.setItem(22, makeNamed(Material.RED_CONCRETE, ChatColor.RED + "Heavy"));
        inv.setItem(23, makeNamed(Material.MAGENTA_CONCRETE, ChatColor.DARK_RED + "Ultimate"));

        // Reroll
        inv.setItem(30, makeNamed(Material.PAPER, ChatColor.GREEN + "Reroll"));
        inv.setItem(32, makeNamed(Material.BOOK, ChatColor.BLUE + "Help"));

        player.openInventory(inv);
    }

    private static ItemStack makeNamed(Material m, String name) {
        ItemStack it = new ItemStack(m);
        ItemMeta im = it.getItemMeta(); im.setDisplayName(name); it.setItemMeta(im);
        return it;
    }
}
