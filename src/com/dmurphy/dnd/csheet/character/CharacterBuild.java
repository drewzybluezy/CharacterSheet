package com.dmurphy.dnd.csheet.character;

import java.util.List;

public class CharacterBuild {

	private String name;
	private int level;

	private int strength;
	private int constitution;
	private int dexterity;
	private int intelligence;
	private int wisdom;
	private int charisma;

	private boolean abilitiesSet = false;

	private CharClass classChoice;
	private Race race;

	private List<Skill> skills;
	private List<Power> powers;
	private List<Feat> feats;
	private List<Armor> armor;
	private List<Weapon> weapons;
	private List<Equipment> equipment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public CharClass getClassChoice() {
		return classChoice;
	}

	public void setClassChoice(CharClass classChoice) {
		this.classChoice = classChoice;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Power> getPowers() {
		return powers;
	}

	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}

	public List<Feat> getFeats() {
		return feats;
	}

	public void setFeats(List<Feat> feats) {
		this.feats = feats;
	}

	public List<Armor> getArmor() {
		return armor;
	}

	public void setArmor(List<Armor> armor) {
		this.armor = armor;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}

	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

	public boolean areAbilitiesSet() {
		return abilitiesSet;
	}

	public void setAbilityFlag(boolean abilitiesSet) {
		this.abilitiesSet = abilitiesSet;
	}

}
