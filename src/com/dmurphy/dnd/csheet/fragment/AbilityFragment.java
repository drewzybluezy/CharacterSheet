package com.dmurphy.dnd.csheet.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.dmurphy.dnd.csheet.MainActivity;
import com.dmurphy.dnd.csheet.R;

public class AbilityFragment extends Fragment {
	private MainActivity activity;
	private Vibrator vibe;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.ability_fragment, container, false);

		activity = (MainActivity) getActivity();
		vibe = activity.getVibe();

		Button previous = (Button) v.findViewById(R.id.previousButton);
		Button next = (Button) v.findViewById(R.id.nextButton);

		final Spinner strength = (Spinner) v.findViewById(R.id.strength);
		final Spinner constitution = (Spinner) v
				.findViewById(R.id.constitution);
		final Spinner dexterity = (Spinner) v.findViewById(R.id.dexterity);
		final Spinner intelligence = (Spinner) v
				.findViewById(R.id.intelligence);
		final Spinner wisdom = (Spinner) v.findViewById(R.id.wisdom);
		final Spinner charisma = (Spinner) v.findViewById(R.id.charisma);

		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				activity.getCharacter()
						.setStrength(
								Integer.parseInt(strength.getSelectedItem()
										.toString()));
				activity.getCharacter().setConstitution(
						Integer.parseInt(constitution.getSelectedItem()
								.toString()));
				activity.getCharacter()
						.setDexterity(
								Integer.parseInt(dexterity.getSelectedItem()
										.toString()));
				activity.getCharacter().setIntelligence(
						Integer.parseInt(intelligence.getSelectedItem()
								.toString()));
				activity.getCharacter().setWisdom(
						Integer.parseInt(wisdom.getSelectedItem().toString()));
				activity.getCharacter()
						.setCharisma(
								Integer.parseInt(charisma.getSelectedItem()
										.toString()));

				vibe.vibrate(50);
				activity.swapFragment(new SkillsFragment(), "skills_fragment");
			}
		});

		previous.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vibe.vibrate(50);
				getFragmentManager().popBackStack();
			}
		});

		return v;
	}

}
