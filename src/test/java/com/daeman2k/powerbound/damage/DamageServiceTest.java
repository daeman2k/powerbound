package com.daeman2k.powerbound.damage;

import com.daeman2k.powerbound.config.StrengthConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class DamageServiceTest {
    @Test
    public void testScaling() {
        StrengthConfig cfg = new StrengthConfig();
        Map<String, Double> scaling = new HashMap<>();
        scaling.put("0", 0.5);
        scaling.put("4", 1.15);
        cfg.setStrengthScaling(scaling);

        DamageService ds = new DamageService(cfg);
        double scaled = ds.computeScaledDamage(8.0, 4);
        Assertions.assertEquals(8.0 * 1.15, scaled, 1e-6);
    }
}
