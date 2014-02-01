package com.dmurphy.dnd.csheet.character;

import java.util.List;

public class CharClass {

	private String name;
	private String picName;
	private Role role;
	private Source source;
	private boolean[] availableArmors;
	private boolean[] availableWeapons;
	private String[] implement;
	private int baseHP;
	private int hpPerLevel;
	private int healingSurgesPerDay;
	private List<String> features;

	public enum Role {
		LEADER, DEFENDER, CONTROLLER, STRIKER
	}

	public enum Source {
		ARCANE, DIVINE, MARTIAL, PSIONIC, PRIMAL, SHADOW
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public boolean[] getAvailableArmors() {
		return availableArmors;
	}

	public void setAvailableArmors(boolean[] availableArmors) {
		this.availableArmors = availableArmors;
	}

	public String[] getImplement() {
		return implement;
	}

	public void setImplement(String[] implement) {
		this.implement = implement;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public void setBaseHP(int baseHP) {
		this.baseHP = baseHP;
	}

	public int getHpPerLevel() {
		return hpPerLevel;
	}

	public void setHpPerLevel(int hpPerLevel) {
		this.hpPerLevel = hpPerLevel;
	}

	public int getHealingSurgesPerDay() {
		return healingSurgesPerDay;
	}

	public void setHealingSurgesPerDay(int healingSurgesPerDay) {
		this.healingSurgesPerDay = healingSurgesPerDay;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public boolean[] getAvailableWeapons() {
		return availableWeapons;
	}

	public void setAvailableWeapons(boolean[] availableWeapons) {
		this.availableWeapons = availableWeapons;
	}

}
