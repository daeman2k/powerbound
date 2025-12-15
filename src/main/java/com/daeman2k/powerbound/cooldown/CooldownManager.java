package com.daeman2k.powerbound.cooldown;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownManager {
    private final Map<UUID, Map<String, Long>> map = new ConcurrentHashMap<>();

    public boolean isOnCooldown(UUID player, String key) {
        Map<String, Long> user = map.get(player);
        if (user == null) return false;
        Long until = user.get(key);
        if (until == null) return false;
        return System.currentTimeMillis() < until;
    }

    public long getRemaining(UUID player, String key) {
        Map<String, Long> user = map.get(player);
        if (user == null) return 0;
        Long until = user.get(key);
        if (until == null) return 0;
        long rem = until - System.currentTimeMillis();
        return Math.max(0, rem);
    }

    public void setCooldown(UUID player, String key, long millis) {
        map.computeIfAbsent(player, k -> new ConcurrentHashMap<>()).put(key, System.currentTimeMillis() + millis);
    }
}
