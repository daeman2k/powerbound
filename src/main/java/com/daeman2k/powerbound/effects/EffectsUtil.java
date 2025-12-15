package com.daeman2k.powerbound.effects;

import com.daeman2k.powerbound.PowerboundPlugin;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;

public final class EffectsUtil {
    private final PowerboundPlugin plugin;

    public EffectsUtil(PowerboundPlugin plugin) {
        this.plugin = plugin;
    }

    public void playParticle(Location loc, String particleName, int count, double offset) {
        if (particleName == null) return;
        try {
            Particle p = Particle.valueOf(particleName.toUpperCase());
            loc.getWorld().spawnParticle(p, loc, count, offset, offset, offset);
        } catch (Exception ignored) {
        }
    }

    public void playSound(Location loc, String soundName, float volume, float pitch) {
        if (soundName == null) return;
        try {
            Sound s = Sound.valueOf(soundName.toUpperCase());
            loc.getWorld().playSound(loc, s, volume, pitch);
        } catch (Exception ignored) {
        }
    }
}
