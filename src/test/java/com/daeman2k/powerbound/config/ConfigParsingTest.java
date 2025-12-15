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
            var crimson = cfg.getBossRelics().get("crimson_wither_star");
            Assertions.assertNotNull(crimson, "Missing crimson_wither_star; available keys: " + cfg.getBossRelics().keySet());
            Assertions.assertNotNull(crimson.getActive());
            Assertions.assertTrue(crimson.getActive().getCooldown() > 0);
            // heavy sword should have particle config parsed
            Assertions.assertTrue(cfg.getAbilities().get("sword").get("heavy").getParticle_count() >= 1);
        }
    }
}
