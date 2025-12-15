package com.daeman2k.powerbound.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class ConfigParsingTest {
    @Test
    public void testLoadYamlAbilities() throws Exception {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("powerbound/strength.yml")) {
            StrengthConfig cfg = ConfigLoader.loadFromYaml(in);
            Assertions.assertNotNull(cfg.getAbilities().get("sword").get("light"));
            Assertions.assertTrue(cfg.getAbilities().get("sword").get("light").getCooldown_seconds() > 0);
            // relic actives must have cooldown and optional particle/sound
            Assertions.assertNotNull(cfg.getBossRelics().get("crimson_wither_star").getActive());
            Assertions.assertTrue(cfg.getBossRelics().get("crimson_wither_star").getActive().getCooldown() > 0);
        }
    }
}
