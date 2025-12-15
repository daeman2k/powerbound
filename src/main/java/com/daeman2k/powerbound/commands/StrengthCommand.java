package com.daeman2k.powerbound.commands;

import com.daeman2k.powerbound.PowerboundPlugin;
import com.daeman2k.powerbound.ui.StrengthGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StrengthCommand implements CommandExecutor {
    private final PowerboundPlugin plugin;

    public StrengthCommand(PowerboundPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        StrengthGui.openFor(plugin, p);
        return true;
    }
}
