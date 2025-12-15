package com.daeman2k.powerbound.commands;

import com.daeman2k.powerbound.PowerboundPlugin;
import com.daeman2k.powerbound.ability.AbilityManager;
import com.daeman2k.powerbound.cooldown.CooldownManager;
import com.daeman2k.powerbound.damage.DamageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AbilityCommand implements CommandExecutor {
    private final PowerboundPlugin plugin;
    private final AbilityManager abilityManager;

    public AbilityCommand(PowerboundPlugin plugin, AbilityManager abilityManager) {
        this.plugin = plugin;
        this.abilityManager = abilityManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage("Usage: /ability <light|medium|heavy|ultimate>");
            return true;
        }
        boolean ok = abilityManager.triggerAbility(p, args[0]);
        if (!ok) p.sendMessage("Ability failed or on cooldown.");
        return true;
    }
}
