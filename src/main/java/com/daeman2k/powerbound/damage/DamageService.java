package com.daeman2k.powerbound.damage;

import com.daeman2k.powerbound.config.StrengthConfig;
import org.bukkit.entity.LivingEntity;

import java.util.Map;

public final class DamageService {
    private final StrengthConfig config;

    public DamageService(StrengthConfig config) {
        this.config = config;
    }

    public double computeScaledDamage(double base, int strengthLevel) {
        Map<String, Double> scaling = config.getStrengthScaling();
        double multiplier = scaling.getOrDefault(String.valueOf(strengthLevel), scaling.getOrDefault("1", 1.0));
        return base * multiplier;
    }

    public void applyDirectDamage(LivingEntity target, double amount) {
        double hp = target.getHealth();
        target.setHealth(Math.max(0.0, hp - amount));
    }
}
