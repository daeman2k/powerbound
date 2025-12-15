package com.daeman2k.powerbound.strength;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class StrengthManagerTest {
    @Test
    public void testAddAndGet() {
        StrengthManager man = new StrengthManager();
        UUID u = UUID.randomUUID();
        Assertions.assertEquals(0, man.getStrength(u));
        man.addStrength(u, 3);
        Assertions.assertEquals(3, man.getStrength(u));
        man.addStrength(u, -1);
        Assertions.assertEquals(2, man.getStrength(u));
        man.setStrength(u, -5);
        Assertions.assertEquals(0, man.getStrength(u));
    }
}
