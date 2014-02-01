package com.dmurphy.dnd.csheet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Vibrator;

import com.dmurphy.dnd.csheet.character.CharClass;
import com.dmurphy.dnd.csheet.character.CharacterBuild;
import com.dmurphy.dnd.csheet.character.Race;
import com.dmurphy.dnd.csheet.fragment.MenuFragment;
import com.dmurphy.dnd.csheet.fragment.NameFragment;

public class MainActivity extends Activity {
	private MediaPlayer player;
	private boolean musicPlayed = false;
	private FragmentManager fm;
	private Vibrator vib;
	private CharacterBuild character = null;
	private List<Race> races = null;
	private List<CharClass> classes = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		initRaces();
		initClasses();

		player = MediaPlayer.create(MainActivity.this, R.raw.music);
		player.start();
		player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				musicPlayed = true;
			}
		});

		fm = getFragmentManager();

		MenuFragment menuFrag = new MenuFragment();
		fm.beginTransaction().add(R.id.frag_container, menuFrag, "menu_frag")
				.commit();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (player.isPlaying()) {
			player.stop();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (player.isPlaying()) {
			player.pause();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!player.isPlaying() && !musicPlayed) {
			player.start();
		}
	}

	@Override
	public void onBackPressed() {
		Fragment frag = fm.findFragmentByTag("name_fragment");
		if (frag instanceof NameFragment && frag.isVisible()) {
			NameFragment f = (NameFragment) frag;
			f.onBackPressed();
		} else {
			super.onBackPressed();
		}
	}

	public void swapFragment(Fragment fragment, String tag) {
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.frag_container, fragment, tag);
		ft.addToBackStack(null);
		ft.commit();
	}

	private List<String[]> loadCSV(String fileName) {
		List<String[]> stringList = new ArrayList<String[]>();

		try {
			InputStream is = getAssets().open(fileName);
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

	private void initRaces() {
		final List<String[]> csvList = loadCSV("race.csv");
		races = new ArrayList<Race>();

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
	}

	private void initClasses() {
		final List<String[]> csvList = loadCSV("class.csv");
		classes = new ArrayList<CharClass>();

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

			classes.add(currentClass);
		}
	}

	private void checkMods(int[] toMod, int index, String arg0, String arg1,
			String compareTo) {
		if (arg0.equals(compareTo) || arg1.equals(compareTo))
			toMod[index] += 2;
	}

	private void checkMods(int[] toMod, int index, String arg0, String arg1,
			String arg2, String compareTo) {
		if (arg0.equals(compareTo) || arg1.equals(compareTo)
				|| arg2.equals(compareTo))
			toMod[index] += 2;
	}

	public Vibrator getVibe() {
		return vib;
	}

	public void setCharacter(CharacterBuild characterBuild) {
		this.character = characterBuild;
	}

	public CharacterBuild getCharacter() {
		return character;
	}

	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	public List<CharClass> getClasses() {
		return classes;
	}

	public void setClasses(List<CharClass> classes) {
		this.classes = classes;
	}

}
