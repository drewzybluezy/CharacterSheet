package com.dmurphy.dnd.csheet.character;

import com.dmurphy.dnd.csheet.character.CharClass.Source;

public class Power {

	private String name;
	private int level;
	private String reqClass;
	private Source source;
	private DamageType damageType;
	private EffectType effectType;
	private WeaponType weaponType;
	private Frequency freq;
	private Action action;
	private AttackRange attackType;
	private String target;
	private String flavorText;

	private String attackCheck;
	private int aoe;
	private int range;

	private String trigger;
	private String hit;
	private String miss;
	private String effect;
	private String special;

	public enum Frequency {
		AT_WILL, ENCOUNTER, DAILY
	}

	public enum Action {
		NONE, MINOR, MOVE, STANDARD, FREE
	}

	public enum AttackRange {
		MELEE, CLOSE, AREA, RANGED, PERSONAL, WEAPON
	}

	public enum DamageType {
		NONE, ACID, COLD, FIRE, FORCE, LIGHTNING, NECROTIC, POISON, PSYCHIC, RADIANT, THUNDER
	}

	public enum EffectType {
		CHARM, CONJURATION, FEAR, HEALING, ILLUSION, POISON, POLYMORPH, RELIABLE, SLEEP, STANCE, TELEPORT, ZONE, NONE
	}

	public enum WeaponType {
		IMPLEMENT, WEAPON, NONE
	}

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

	public String getReqClass() {
		return reqClass;
	}

	public void setReqClass(String reqClass) {
		this.reqClass = reqClass;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}

	public EffectType getEffectType() {
		return effectType;
	}

	public void setEffectType(EffectType effectType) {
		this.effectType = effectType;
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public Frequency getFreq() {
		return freq;
	}

	public void setFreq(Frequency freq) {
		this.freq = freq;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public AttackRange getAttackType() {
		return attackType;
	}

	public void setAttackType(AttackRange attackType) {
		this.attackType = attackType;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public String getAttackCheck() {
		return attackCheck;
	}

	public void setAttackCheck(String attackCheck) {
		this.attackCheck = attackCheck;
	}

	public int getAoe() {
		return aoe;
	}

	public void setAoe(int aoe) {
		this.aoe = aoe;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getMiss() {
		return miss;
	}

	public void setMiss(String miss) {
		this.miss = miss;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

}
