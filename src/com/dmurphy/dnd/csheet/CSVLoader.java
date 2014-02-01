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
			currentClass.setBaseHP(Integer.parseInt(currentArray[8]));
			currentClass.setHpPerLevel(Integer.parseInt(currentArray[9]));
			currentClass.setHealingSurgesPerDay(Integer
					.parseInt(currentArray[10]));

			List<String> classFeatures = new ArrayList<String>();
			for (int j = 13; j < currentArray.length; j++) {
				classFeatures.add(currentArray[j]);
			}

			currentClass.setFeatures(classFeatures);

			classes.add(currentClass);
		}

		return classes;
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
}
