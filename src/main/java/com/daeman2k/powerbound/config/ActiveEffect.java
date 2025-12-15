package com.daeman2k.powerbound.config;

import java.util.List;

public class ActiveEffect {
    private Integer damage;
    private List<String> effects;
    private Integer cooldown;
    private String sound;
    private String particle;

    public Integer getDamage() {
        return damage == null ? 0 : damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public List<String> getEffects() {
        return effects;
    }

    public void setEffects(List<String> effects) {
        this.effects = effects;
    }

    public Integer getCooldown() {
        return cooldown == null ? 0 : cooldown;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getParticle() {
        return particle;
    }

    public void setParticle(String particle) {
        this.particle = particle;
    }
}
