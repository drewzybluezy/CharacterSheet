package com.dmurphy.dnd.csheet.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmurphy.dnd.csheet.CSheetReusables;
import com.dmurphy.dnd.csheet.MainActivity;
import com.dmurphy.dnd.csheet.R;
import com.dmurphy.dnd.csheet.character.Race;

public class RaceFragment extends Fragment {
	private MainActivity activity;
	private Vibrator vibe;
	private TextView title;
	private TextView name;
	private TextView description;
	private ImageView picture;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.race_class_fragment, container,
				false);

		activity = (MainActivity) getActivity();
		vibe = activity.getVibe();

		final Button previous = (Button) v.findViewById(R.id.previousButton);
		final Button next = (Button) v.findViewById(R.id.nextButton);

		title = (TextView) v.findViewById(R.id.abilityName);
		title.setText(R.string.race);
		
		name = (TextView) v.findViewById(R.id.name);
		name.setText("");

		description = (TextView) v.findViewById(R.id.description);
		description
				.setText("\nClick on a race to find out more information about racial bonuses and features.");

		picture = (ImageView) v.findViewById(R.id.picture);
		picture.setImageResource(R.drawable.idle);

		next.setEnabled(false);
		next.setAlpha(0.3f);
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(50);
				activity.swapFragment(new ClassFragment(), "class_fragment");
			}
		});

		previous.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vibe.vibrate(50);
				getFragmentManager().popBackStack();
			}
		});

		ListView listView = (ListView) v.findViewById(R.id.raceClassList);
		final ArrayList<String> list = new ArrayList<String>();

		final List<Race> races = activity.getRaces();

		for (Race race : races) {
			list.add(race.getName());
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(
				v.getContext(), android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				formatRaceAttributes(races.get(position), parent.getContext());
				activity.getCharacter().setRace(races.get(position));
				next.setEnabled(true);
				next.setAlpha(1f);
			}
		});

		try {
			Race savedRace = activity.getCharacter().getRace();
			formatRaceAttributes(savedRace, v.getContext());
			next.setEnabled(true);
			next.setAlpha(1f);
		} catch (NullPointerException e) {

		}

		return v;
	}

	private void formatRaceAttributes(Race race, Context context) {
		picture.setImageResource(context.getResources().getIdentifier(
				race.getPicName(), "drawable", context.getPackageName()));
		name.setText(race.getName());

		int[] abilityMods = race.getAbilityMods();

		String ability = "";
		for (int i = 0; i < abilityMods.length; i++) {
			if (abilityMods[i] > 0) {
				ability += " +2 " + CSheetReusables.abilityNames[i] + ",";
			}
		}

		if (ability.length() >= 1) {
			ability = ability.substring(0, ability.length() - 1);
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
				skills += " +2 " + CSheetReusables.skillNames[i] + ",";
			}
		}

		if (skills.length() >= 1) {
			skills = skills.substring(0, skills.length() - 1);
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

		String descript = "\nAbility Scores: " + ability + "\n\nSize: "
				+ race.getSize() + "\n\nSpeed: " + race.getSpeed()
				+ "\n\nVision: " + race.getSight() + "\n\nLanguages: Common, "
				+ languageDisplay + "\n\nSkills: " + skills + "\n\nFeatures: "
				+ feature;
		description.setText(descript);

	}

	public String getFormattedLanguage(String toFormat) {
		for (int i = 0; i < CSheetReusables.languageAbbrevs.length; i++) {
			if (toFormat.equals(CSheetReusables.languageAbbrevs[i])) {
				return CSheetReusables.languageNames[i];
			}

			if (toFormat.equals("CHOICE"))
				return "Choice";
		}
		return "";
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

}
