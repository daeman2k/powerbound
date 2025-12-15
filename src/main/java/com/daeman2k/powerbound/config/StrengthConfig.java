package com.daeman2k.powerbound.config;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StrengthConfig {
    private Map<String,Object> baseDamage = new HashMap<>();
    private Map<String,Double> strengthScaling = new HashMap<>();
    private Map<String,Map<String,Ability>> abilities = new HashMap<>();
    private Map<String,Object> gui = new HashMap<>();
    private Map<String,BossRelic> bossRelics = new HashMap<>();
    private Integer axeDamageModifierPercent = 0;

    public Map<String, Object> getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(Map<String, Object> baseDamage) {
        this.baseDamage = baseDamage;
    }

    public Map<String, Double> getStrengthScaling() {
        return strengthScaling;
    }

    public void setStrengthScaling(Map<String, Double> strengthScaling) {
        this.strengthScaling = strengthScaling;
    }

    public Map<String, Map<String, Ability>> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<String, Map<String, Ability>> abilities) {
        this.abilities = abilities;
    }

    public Map<String, Object> getGui() {
        return gui;
    }

    public void setGui(Map<String, Object> gui) {
        this.gui = gui;
    }

    public Map<String, BossRelic> getBossRelics() {
        return bossRelics;
    }

    public void setBossRelics(Map<String, BossRelic> bossRelics) {
        this.bossRelics = bossRelics;
    }

    public Integer getAxeDamageModifierPercent() {
        return axeDamageModifierPercent == null ? 0 : axeDamageModifierPercent;
    }

    public void setAxeDamageModifierPercent(Integer axeDamageModifierPercent) {
        this.axeDamageModifierPercent = axeDamageModifierPercent;
    }

    @JsonAnySetter
    public void set(String name, Object value) {
        // Catch-all for unknown properties to keep config forward-compatible
    }
}
