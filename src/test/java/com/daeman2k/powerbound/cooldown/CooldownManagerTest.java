package com.daeman2k.powerbound.cooldown;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CooldownManagerTest {
    @Test
    public void testCooldown() throws InterruptedException {
        CooldownManager cd = new CooldownManager();
        UUID u = UUID.randomUUID();
        String key = "test";
        Assertions.assertFalse(cd.isOnCooldown(u, key));
        cd.setCooldown(u, key, 200);
        Assertions.assertTrue(cd.isOnCooldown(u, key));
        Thread.sleep(250);
        Assertions.assertFalse(cd.isOnCooldown(u, key));
    }
}
