package com.daeman2k.powerbound.strength;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StrengthManager {
    private final Map<UUID, Integer> strengthMap = new ConcurrentHashMap<>();

    public int getStrength(UUID player) {
        return strengthMap.getOrDefault(player, 0);
    }

    public void setStrength(UUID player, int value) {
        strengthMap.put(player, Math.max(0, value));
    }

    public void addStrength(UUID player, int delta) {
        setStrength(player, getStrength(player) + delta);
    }

    public void removeStrength(UUID player) {
        strengthMap.remove(player);
    }
}
