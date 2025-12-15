package com.daeman2k.powerbound.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BossRelic {
    private String title;
    private String passive;
    private ActiveEffect active;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassive() {
        return passive;
    }

    public void setPassive(String passive) {
        this.passive = passive;
    }

    public ActiveEffect getActive() {
        return active;
    }

    public void setActive(ActiveEffect active) {
        this.active = active;
    }
}
