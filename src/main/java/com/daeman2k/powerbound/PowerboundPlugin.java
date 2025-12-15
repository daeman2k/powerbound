package com.daeman2k.powerbound;

import com.daeman2k.powerbound.config.ConfigLoader;
import com.daeman2k.powerbound.config.StrengthConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class PowerboundPlugin extends JavaPlugin {

    private StrengthConfig config;
    private com.daeman2k.powerbound.strength.StrengthManager strengthManager;
    private com.daeman2k.powerbound.damage.DamageService damageService;
    private com.daeman2k.powerbound.cooldown.CooldownManager cooldownManager;
    private com.daeman2k.powerbound.ability.AbilityManager abilityManager;
    private com.daeman2k.powerbound.relic.RelicManager relicManager;

    @Override
    public void onEnable() {
        // Load config from YAML resource
        try {
            this.config = ConfigLoader.loadFromYaml(getResourceAsStream("powerbound/strength.yml"));
            getLogger().info("Loaded strength config: base sword damage=" + config.getBaseDamage().getOrDefault("sword", "n/a"));
        } catch (Exception e) {
            getLogger().severe("Failed to load strength config: " + e.getMessage());
            e.printStackTrace();
        }

        // Initialize services
        this.strengthManager = new com.daeman2k.powerbound.strength.StrengthManager();
        this.damageService = new com.daeman2k.powerbound.damage.DamageService(config);
        this.cooldownManager = new com.daeman2k.powerbound.cooldown.CooldownManager();
        this.abilityManager = new com.daeman2k.powerbound.ability.AbilityManager(this, cooldownManager, damageService);
        this.relicManager = new com.daeman2k.powerbound.relic.RelicManager(this);

        // Register listeners and commands
        getServer().getPluginManager().registerEvents(new com.daeman2k.powerbound.listeners.DamageListener(this, strengthManager, damageService), this);
        getServer().getPluginManager().registerEvents(new com.daeman2k.powerbound.listeners.InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new com.daeman2k.powerbound.listeners.RelicPassiveListener(this), this);

        getCommand("strength").setExecutor(new com.daeman2k.powerbound.commands.StrengthCommand(this));
        getCommand("ability").setExecutor(new com.daeman2k.powerbound.commands.AbilityCommand(this, abilityManager));
        getCommand("claimrelic").setExecutor(new com.daeman2k.powerbound.commands.RelicCommand(this, relicManager));

        // TODO: register commands, listeners and GUI when implementing further features
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public StrengthConfig getStrengthConfig() {
        return config;
    }

    public com.daeman2k.powerbound.strength.StrengthManager getStrengthManager() {
        return strengthManager;
    }

    public com.daeman2k.powerbound.cooldown.CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public com.daeman2k.powerbound.ability.AbilityManager getAbilityManager() {
        return abilityManager;
    }

    public com.daeman2k.powerbound.relic.RelicManager getRelicManager() {
        return relicManager;
    }
}

