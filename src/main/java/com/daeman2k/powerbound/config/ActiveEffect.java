package com.daeman2k.powerbound.config;

import java.util.List;

public class ActiveEffect {
    private String name;
    private Integer damage;
    private List<String> effects;
    private Integer cooldown;
    private String sound;
    private String particle;
    private Integer particle_count;
    private Double particle_offset;
    private Double sound_volume;
    private Double sound_pitch;

    public Integer getDamage() {
        return damage == null ? 0 : damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getParticle_count() {
        return particle_count == null ? 10 : particle_count;
    }

    public void setParticle_count(Integer particle_count) {
        this.particle_count = particle_count;
    }

    public double getParticle_offset() {
        return particle_offset == null ? 0.35 : particle_offset;
    }

    public void setParticle_offset(Double particle_offset) {
        this.particle_offset = particle_offset;
    }

    public double getSound_volume() {
        return sound_volume == null ? 1.0 : sound_volume;
    }

    public void setSound_volume(Double sound_volume) {
        this.sound_volume = sound_volume;
    }

    public double getSound_pitch() {
        return sound_pitch == null ? 1.0 : sound_pitch;
    }

    public void setSound_pitch(Double sound_pitch) {
        this.sound_pitch = sound_pitch;
    }
}
