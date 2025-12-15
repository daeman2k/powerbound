package com.daeman2k.powerbound.commands;

import com.daeman2k.powerbound.PowerboundPlugin;
import com.daeman2k.powerbound.relic.RelicManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RelicCommand implements CommandExecutor {
    private final PowerboundPlugin plugin;
    private final RelicManager relicManager;

    public RelicCommand(PowerboundPlugin plugin, RelicManager relicManager) {
        this.plugin = plugin;
        this.relicManager = relicManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage("Usage: /claimrelic <warden|wither|dragon> or /claimrelic use");
            return true;
        }
        if ("use".equalsIgnoreCase(args[0])) {
            boolean ok = relicManager.triggerActive(p);
            if (!ok) p.sendMessage("Relic active failed or on cooldown.");
            return true;
        }
        relicManager.claim(p, args[0]);
        p.sendMessage("You have claimed: " + args[0]);
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage("Usage: /claimrelic <warden|wither|dragon>");
            return true;
        }
        relicManager.claim(p, args[0]);
        p.sendMessage("You have claimed: " + args[0]);
        return true;
    }
}
