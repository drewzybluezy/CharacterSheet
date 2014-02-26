package com.dmurphy.dnd.csheet.character;

import com.dmurphy.dnd.csheet.character.CharClass.Source;

public class Power {

	private String name;
	private PowerType powerType;
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

	private AreaType areaType;
	private int aoe;
	private int range;

	private String requirement;
	private String trigger;
	private String hit;
	private String miss;
	private String effect;
	private String special;

	private String formattedReq;
	private String formattedAttributes;
	private String formattedEffect;

	public enum Frequency {
		AT_WILL("At-Will"), ENCOUNTER("Encounter"), DAILY("Daily");

		private final String name;

		private Frequency(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}
	}

	public enum Action {
		NONE("None"), MINOR("Minor"), MOVE("Move"), STANDARD("Standard"), FREE(
				"Free"), IMMEDIATE("IMMEDIATE");

		private final String name;

		private Action(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}
	}

	public enum AttackRange {
		NONE(""), MELEE("Melee"), CLOSE("Close"), AREA("Area"), RANGED("Ranged"), PERSONAL(
				"Personal"), WEAPON("Melee or Ranged");

		private final String name;

		private AttackRange(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}
	}

	public enum PowerType {
		NONE(""), ATTACK("Attack "), UTILITY("Utility ");

		private final String name;

		private PowerType(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}
	}

	public enum DamageType {
		NONE(""), ACID("Acid"), COLD("Cold"), FIRE("Fire"), FORCE("Force"), LIGHTNING(
				"Lightning"), NECROTIC("Necrotic"), POISON("Poison"), PSYCHIC(
				"Psychic"), RADIANT("Radiant"), THUNDER("Thunder");

		private final String name;

		private DamageType(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}
	}

	public enum EffectType {
		CHARM("Charm"), CONJURATION("Conjuration"), FEAR("Fear"), HEALING(
				"Healing"), ILLUSION("Illusion"), POISON("Poison"), POLYMORPH(
				"Polymorph"), RELIABLE("Reliable"), SLEEP("Sleep"), STANCE(
				"Stance"), TELEPORT("Teleport"), ZONE("Zone"), NONE("");

		private final String name;

		private EffectType(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}
	}

	public enum WeaponType {
		IMPLEMENT("Implement"), WEAPON("Weapon"), NONE("");

		private final String name;

		private WeaponType(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}
	}

	public enum AreaType {
		NONE(""), BLAST("blast"), BURST("burst"), WALL("wall"), WEAPON("weapon");

		private final String name;

		private AreaType(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PowerType getPowerType() {
		return powerType;
	}

	public void setPowerType(PowerType powerType) {
		this.powerType = powerType;
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

	public AreaType getAreaType() {
		return areaType;
	}

	public void setAreaType(AreaType areaType) {
		this.areaType = areaType;
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

	public String getFormattedReq() {
		return formattedReq;
	}

	public void setFormattedReq(String formattedReq) {
		this.formattedReq = formattedReq;
	}

	public String getFormattedAttributes() {
		return formattedAttributes;
	}

	public void setFormattedAttributes(String formattedAttributes) {
		this.formattedAttributes = formattedAttributes;
	}

	public String getFormattedEffect() {
		return formattedEffect;
	}

	public void setFormattedEffect(String formattedEffect) {
		this.formattedEffect = formattedEffect;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

}
