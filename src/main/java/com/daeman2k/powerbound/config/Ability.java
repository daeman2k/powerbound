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
    private Double sound_volume;
    private Double sound_pitch;
    private Integer particle_count;
    private Double particle_offset;

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

    public int getParticle_count() {
        return particle_count == null ? 6 : particle_count;
    }

    public void setParticle_count(Integer particle_count) {
        this.particle_count = particle_count;
    }

    public double getParticle_offset() {
        return particle_offset == null ? 0.2 : particle_offset;
    }

    public void setParticle_offset(Double particle_offset) {
        this.particle_offset = particle_offset;
    }
}
