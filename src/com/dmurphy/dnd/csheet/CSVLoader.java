package com.dmurphy.dnd.csheet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.dmurphy.dnd.csheet.character.CharClass;
import com.dmurphy.dnd.csheet.character.Race;

public class CSVLoader {

	private static List<String[]> loadCSV(String fileName, Context context) {
		List<String[]> stringList = new ArrayList<String[]>();

		try {
			InputStream is = context.getAssets().open(fileName);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] rowData = line.split(",");
				stringList.add(rowData);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stringList;
	}

	public static List<Race> initRaces(Context context) {
		final List<String[]> csvList = loadCSV("race.csv", context);
		List<Race> races = new ArrayList<Race>();

		for (int i = 0; i < csvList.size(); ++i) {
			String[] currentArray = csvList.get(i);
			Race currentRace = new Race();
			currentRace.setName(currentArray[0]);
			currentRace.setPicName(currentArray[1]);
			currentRace.setSpeed(Integer.parseInt(currentArray[2]));

			String sight = currentArray[3];
			if (sight.equals("low")) {
				sight = "Low-light";
			} else {
				sight = "Normal";
			}

			currentRace.setSight(sight);

			String size = currentArray[4];
			if (size.equals("small")) {
				size = "Small";
			} else if (size.equals("large")) {
				size = "Large";
			} else {
				size = "Medium";
			}

			currentRace.setSize(size);

			int[] abilityMods = new int[6];

			String abilityMod1 = currentArray[5];
			String abilityMod2 = currentArray[6];

			for (int j = 0; j < CSheetReusables.abilityAbbrevs.length; j++) {
				checkMods(abilityMods, j, abilityMod1, abilityMod2,
						CSheetReusables.abilityAbbrevs[j]);
			}

			currentRace.setAbilityMods(abilityMods);

			int[] skillMods = new int[17];

			String skillMod1 = currentArray[7];
			String skillMod2 = currentArray[8];
			String skillMod3 = currentArray[9];

			for (int j = 0; j < CSheetReusables.skillAbbrevs.length; j++) {
				checkMods(skillMods, j, skillMod1, skillMod2, skillMod3,
						CSheetReusables.skillAbbrevs[j]);
			}

			currentRace.setSkillMods(skillMods);

			String[] languages = new String[2];
			String languageOne = currentArray[10];
			String languageTwo = currentArray[11];

			languages[0] = languageOne;
			languages[1] = languageTwo;

			currentRace.setLanguages(languages);

			List<String> raceFeatures = new ArrayList<String>();
			for (int j = 12; j < currentArray.length; j++) {
				raceFeatures.add(currentArray[j]);
			}

			currentRace.setFeatures(raceFeatures);
			currentRace.setDescription(buildRaceDescription(currentRace));
			races.add(currentRace);
		}

		return races;
	}

	public static List<CharClass> initClasses(Context context) {
		final List<String[]> csvList = loadCSV("class.csv", context);
		List<CharClass> classes = new ArrayList<CharClass>();

		for (int i = 0; i < csvList.size(); ++i) {
			String[] currentArray = csvList.get(i);
			CharClass currentClass = new CharClass();
			currentClass.setName(currentArray[0]);
			currentClass.setPicName(currentArray[1]);

			switch (currentArray[2].charAt(0)) {
			case 'L':
				currentClass.setRole(CharClass.Role.LEADER);
				break;
			case 'D':
				currentClass.setRole(CharClass.Role.DEFENDER);
				break;
			case 'S':
				currentClass.setRole(CharClass.Role.STRIKER);
				break;
			case 'C':
				currentClass.setRole(CharClass.Role.CONTROLLER);
				break;
			}

			String source = currentArray[3];
			if (source.equals("div")) {
				currentClass.setSource(CharClass.Source.DIVINE);
			} else if (source.equals("arc")) {
				currentClass.setSource(CharClass.Source.ARCANE);
			} else if (source.equals("mart")) {
				currentClass.setSource(CharClass.Source.MARTIAL);
			} else if (source.equals("psi")) {
				currentClass.setSource(CharClass.Source.PSIONIC);
			} else if (source.equals("prim")) {
				currentClass.setSource(CharClass.Source.PRIMAL);
			} else if (source.equals("shad")) {
				currentClass.setSource(CharClass.Source.SHADOW);
			}

			currentClass.setAvailableArmors(checkArmor(currentArray[4]));
			currentClass.setAvailableWeapons(checkWeapons(currentArray[5]));
			currentClass.setImplement(currentArray[6].split(";"));
			currentClass.setDefenseBonus(checkDefBonuses(currentArray[7]));
			currentClass.setBaseHP(Integer.parseInt(currentArray[8]));
			currentClass.setHpPerLevel(Integer.parseInt(currentArray[9]));
			currentClass.setHealingSurgesPerDay(Integer
					.parseInt(currentArray[10]));

			setClassSkills(currentArray[11], currentClass);

			currentClass.setBuildOptions(currentArray[12].split(";"));

			List<String> classFeatures = new ArrayList<String>();
			for (int j = 13; j < currentArray.length; j++) {
				classFeatures.add(currentArray[j]);
			}

			currentClass.setFeatures(classFeatures);
			currentClass.setDescription(buildClassDescription(currentClass));
			classes.add(currentClass);
		}

		return classes;
	}

	private static void setClassSkills(String toSplit, CharClass currentClass) {
		ArrayList<Integer> availableSkillGroups = new ArrayList<Integer>();
		availableSkillGroups.add(Integer.valueOf(0));

		int[] availableSkills = new int[CSheetReusables.skillAbbrevs.length];
		for (String s : toSplit.split("/")) {
			if (s.charAt(0) == '&') {
				s = s.substring(1, s.length());
				for (String t : s.split(";")) {
					for (int i = 0; i < CSheetReusables.skillAbbrevs.length; i++) {
						if (t.equals(CSheetReusables.skillAbbrevs[i])) {
							availableSkills[i] = -1;
						}
					}
				}
			} else if (s.charAt(1) == '!') {
				availableSkillGroups.add(Integer.valueOf(Integer.parseInt(s
						.charAt(0) + "")));
				s = s.substring(2, s.length());
				for (String t : s.split(";")) {
					for (int i = 0; i < CSheetReusables.skillAbbrevs.length; i++) {
						if (t.equals(CSheetReusables.skillAbbrevs[i])
								&& availableSkills[i] != -1) {
							availableSkills[i] = availableSkillGroups.size() - 1;
						}
					}
				}
			}
		}

		currentClass.setAvailableSkillGroups(availableSkillGroups);
		currentClass.setAvailableSkills(availableSkills);
	}

	private static void checkMods(int[] toMod, int index, String arg0,
			String arg1, String compareTo) {
		if (arg0.equals(compareTo) || arg1.equals(compareTo))
			toMod[index] += 2;
	}

	private static void checkMods(int[] toMod, int index, String arg0,
			String arg1, String arg2, String compareTo) {
		if (arg0.equals(compareTo) || arg1.equals(compareTo)
				|| arg2.equals(compareTo))
			toMod[index] += 2;
	}

	private static boolean[] checkArmor(String toSplit) {
		String[] armorProfs = toSplit.split(";");
		boolean[] availArmor = new boolean[8];
		for (String s : armorProfs) {
			for (int i = 0; i < CSheetReusables.armorAbbrevs.length; i++) {
				if (s.equals(CSheetReusables.armorAbbrevs[i])) {
					availArmor[i] = true;
				}
			}
		}
		return availArmor;
	}

	private static boolean[] checkWeapons(String toSplit) {
		String[] weaponProfs = toSplit.split(";");
		boolean[] availWeapons = new boolean[37];
		for (String s : weaponProfs) {
			for (int i = 0; i < CSheetReusables.weaponAbbrevs.length; i++) {
				if (s.equals(CSheetReusables.weaponAbbrevs[i])) {
					availWeapons[i] = true;
				}
			}

			if (s.equals("sm")) {
				for (int i = 0; i < 10; i++) {
					availWeapons[i] = true;
				}
			} else if (s.equals("mm")) {
				for (int i = 10; i < 27; i++) {
					availWeapons[i] = true;
				}
			} else if (s.equals("pm")) {
				for (int i = 27; i < 31; i++) {
					availWeapons[i] = true;
				}
			} else if (s.equals("sr")) {
				for (int i = 31; i < 34; i++) {
					availWeapons[i] = true;
				}
			} else if (s.equals("mr")) {
				for (int i = 34; i < 36; i++) {
					availWeapons[i] = true;
				}
			} else if (s.equals("pr")) {
				availWeapons[36] = true;
			}
		}
		return availWeapons;
	}

	private static int[] checkDefBonuses(String toSplit) {
		String[] bonuses = toSplit.split(";");
		int[] bonusDef = new int[3];
		for (String s : bonuses) {
			if (s.charAt(1) == 'F') {
				bonusDef[0] = Integer.parseInt(s.charAt(0) + "");
			} else if (s.charAt(1) == 'R') {
				bonusDef[1] = Integer.parseInt(s.charAt(0) + "");
			} else if (s.charAt(1) == 'W') {
				bonusDef[2] = Integer.parseInt(s.charAt(0) + "");
			}
		}
		return bonusDef;
	}

	private static String buildRaceDescription(Race race) {
		int[] abilityMods = race.getAbilityMods();

		String ability = "";
		for (int i = 0; i < abilityMods.length; i++) {
			if (abilityMods[i] > 0) {
				ability += "+2 " + CSheetReusables.abilityNames[i] + ", ";
			}
		}

		if (ability.length() >= 1) {
			ability = ability.substring(0, ability.length() - 2);
		} else if (race.getName().equals("Human")) {
			ability = "+2 to one ability score of choice";
		}

		String[] languages = race.getLanguages();
		String languageDisplay = getFormattedLanguage(languages[0]) + ", "
				+ getFormattedLanguage(languages[1]);
		if (languageDisplay.lastIndexOf(',') == (languageDisplay.length() - 2)) {
			languageDisplay = languageDisplay.substring(0,
					languageDisplay.length() - 2);
		}

		int[] skillMods = race.getSkillMods();

		String skills = "";
		for (int i = 0; i < skillMods.length; i++) {
			if (skillMods[i] > 0) {
				skills += "+2 " + CSheetReusables.skillNames[i] + ", ";
			}
		}

		if (skills.length() >= 1) {
			skills = skills.substring(0, skills.length() - 2);
		} else if (race.getName().equals("Human")) {
			skills = "Extra Skill From Class List";
		}

		List<String> features = race.getFeatures();

		String feature = "";
		for (int i = 0; i < features.size(); i++) {
			feature += features.get(i) + ", ";
		}

		if (feature.length() >= 1) {
			feature = feature.substring(0, feature.length() - 2);
		}

		String descript = "Ability Scores: " + ability + "\n\nSize: "
				+ race.getSize() + "\nSpeed: " + race.getSpeed() + "\nVision: "
				+ race.getSight() + "\nLanguages: Common, " + languageDisplay
				+ "\n\nSkills: " + skills + "\n\nFeatures: " + feature;
		race.setDescription(descript);
		return descript;
	}

	private static String buildClassDescription(CharClass c) {
		String role = "";
		switch (c.getRole()) {
		case LEADER:
			role = "Leader";
			break;
		case STRIKER:
			role = "Striker";
			break;
		case DEFENDER:
			role = "Defender";
			break;
		case CONTROLLER:
			role = "Controller";
			break;
		}

		String source = "";
		switch (c.getSource()) {
		case MARTIAL:
			source = "Martial";
			break;
		case ARCANE:
			source = "Arcane";
			break;
		case DIVINE:
			source = "Divine";
			break;
		case PSIONIC:
			source = "Psionic";
			break;
		case PRIMAL:
			source = "Primal";
			break;
		case SHADOW:
			source = "Shadow";
			break;
		}

		boolean[] availArmors = c.getAvailableArmors();

		String armor = "";
		for (int i = 0; i < availArmors.length; i++) {
			if (availArmors[i]) {
				armor += CSheetReusables.armorNames[i] + ", ";
			}
		}
		if (armor.lastIndexOf(',') == (armor.length() - 2)) {
			armor = armor.substring(0, armor.length() - 2);
		}

		boolean[] availWeapons = c.getAvailableWeapons();

		String weapons = "";
		weapons += findWeaponGroup(0, 10, "Simple Melee", availWeapons);
		weapons += findWeaponGroup(10, 27, "Military Melee", availWeapons);
		weapons += findWeaponGroup(27, 31, "Superior Melee", availWeapons);
		weapons += findWeaponGroup(31, 34, "Simple Ranged", availWeapons);
		weapons += findWeaponGroup(34, 36, "Military Ranged", availWeapons);

		if (weapons.lastIndexOf(',') == (weapons.length() - 2)) {
			weapons = weapons.substring(0, weapons.length() - 2);
		}

		String implement = "";
		if (!c.getImplement()[0].equals("")) {
			implement = "\n\nImplements: ";
			for (String s : c.getImplement()) {
				implement += s + ", ";
			}
			if (implement.lastIndexOf(',') == (implement.length() - 2)) {
				implement = implement.substring(0, implement.length() - 2);
			}
		}

		String defBonuses = "";
		int[] bonuses = c.getDefenseBonus();
		if (bonuses[0] > 0) {
			defBonuses += "+" + bonuses[0] + " Fortitude, ";
		}
		if (bonuses[1] > 0) {
			defBonuses += "+" + bonuses[1] + " Reflex, ";
		}
		if (bonuses[2] > 0) {
			defBonuses += "+" + bonuses[2] + " Will, ";
		}
		if (defBonuses.lastIndexOf(',') == (defBonuses.length() - 2)) {
			defBonuses = defBonuses.substring(0, defBonuses.length() - 2);
		}

		String skills = "";
		List<Integer> skillGroups = c.getAvailableSkillGroups();
		int[] availableSkills = c.getAvailableSkills().clone();

		for (int i = 0; i < availableSkills.length; i++) {
			if (availableSkills[i] == -1) {
				skills += CSheetReusables.skillNames[i] + ", ";
			}
		}

		for (int i = 0; i < availableSkills.length; i++) {
			if (availableSkills[i] > 0) {
				skills += "Choose " + skillGroups.get(availableSkills[i])
						+ " from: ";
				for (int j = i; j < availableSkills.length; j++) {
					if (availableSkills[i] == availableSkills[j]) {
						skills += CSheetReusables.skillNames[j] + ", ";
						if (j != i)
							availableSkills[j] = 0;
					}
				}
			}
		}

		if (skills.lastIndexOf(',') == (skills.length() - 2)) {
			skills = skills.substring(0, skills.length() - 2);
		}

		String buildOptions = "";
		for (String s : c.getBuildOptions()) {
			buildOptions += s + ", ";
		}
		if (buildOptions.lastIndexOf(',') == (buildOptions.length() - 2)) {
			buildOptions = buildOptions.substring(0, buildOptions.length() - 2);
		}

		String features = "";
		for (String s : c.getFeatures()) {
			features += s + ", ";
		}
		if (features.lastIndexOf(',') == (features.length() - 2)) {
			features = features.substring(0, features.length() - 2);
		}

		String descript = "Role: " + role + "\nPower Source: " + source
				+ "\n\nArmor: " + armor + "\n\nWeapons: " + weapons + implement
				+ "\n\nBonuses to Defense: " + defBonuses + "\n\nHP @ Lvl 1: "
				+ c.getBaseHP() + " + Cons. score" + "\nHP/Lvl: "
				+ c.getHpPerLevel() + "\nHealing Surges/Day: "
				+ c.getHealingSurgesPerDay() + " + Cons. mod"
				+ "\n\nTrained Skills: " + skills + "\n\nBuild options: "
				+ buildOptions + "\n\nClass Features: " + features;
		c.setDescription(descript);
		return descript;
	}

	private static String findWeaponGroup(int startIndex, int endIndex,
			String allFound, boolean[] availWeapons) {
		boolean flag = true;
		String currentWeaponGroup = "";

		for (int i = startIndex; i < endIndex; i++) {
			if (availWeapons[i]) {
				currentWeaponGroup += CSheetReusables.weaponNames[i] + ", ";
			} else
				flag = false;
		}
		if (flag) {
			return allFound + ", ";
		} else {
			return currentWeaponGroup;
		}
	}

	private static String getFormattedLanguage(String toFormat) {
		for (int i = 0; i < CSheetReusables.languageAbbrevs.length; i++) {
			if (toFormat.equals(CSheetReusables.languageAbbrevs[i])) {
				return CSheetReusables.languageNames[i];
			}

			if (toFormat.equals("CHOICE"))
				return "Choice";
		}
		return "";
	}
}
