package com.daeman2k.powerbound.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ability {
    private int damage;
    private String effect;
    private String extra;
    private Integer cooldown_seconds;
    private String sound;
    private String particle;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getCooldown_seconds() {
        return cooldown_seconds == null ? 0 : cooldown_seconds;
    }

    public void setCooldown_seconds(Integer cooldown_seconds) {
        this.cooldown_seconds = cooldown_seconds;
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
