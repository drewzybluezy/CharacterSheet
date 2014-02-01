package com.dmurphy.dnd.csheet.character;

import java.util.List;

public class Race {
    
    private String name;
    private String picName;
    private int speed;
    private String sight;
    private String size;
    private int[] abilityMods;
    private int[] skillMods;
    private String[] languages;
    private List<String> features;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getSight() {
        return sight;
    }
    public void setSight(String sight) {
        this.sight = sight;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int[] getAbilityMods() {
        return abilityMods;
    }
    public void setAbilityMods(int[] abilityMods) {
        this.abilityMods = abilityMods;
    }
    public int[] getSkillMods() {
        return skillMods;
    }
    public void setSkillMods(int[] skillMods) {
        this.skillMods = skillMods;
    }
    public String[] getLanguages() {
        return languages;
    }
    public void setLanguages(String[] languages) {
        this.languages = languages;
    }
    public List<String> getFeatures() {
        return features;
    }
    public void setFeatures(List<String> features) {
        this.features = features;
    }
    
}
