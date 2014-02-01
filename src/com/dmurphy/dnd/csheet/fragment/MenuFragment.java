package com.dmurphy.dnd.csheet.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.dmurphy.dnd.csheet.MainActivity;
import com.dmurphy.dnd.csheet.R;
import com.dmurphy.dnd.csheet.character.CharacterBuild;

public class MenuFragment extends Fragment {
	private MainActivity activity;
	private Vibrator vibe;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.menu_fragment, container, false);
		
		activity = (MainActivity) getActivity();
		vibe = activity.getVibe();

		Button newCharacter = (Button) v.findViewById(R.id.new_char);
		Button loadCharacter = (Button) v.findViewById(R.id.load_char);
		Button about = (Button) v.findViewById(R.id.about);

		newCharacter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				activity.setCharacter(new CharacterBuild());
				
				vibe.vibrate(50);
				activity.swapFragment(new NameFragment(), "name_fragment");
			}
		});
		
		loadCharacter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(50);
				activity.swapFragment(new LoadFragment(), "load_fragment");
			}
		});
		
		about.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(50);
				activity.swapFragment(new AboutFragment(), "about_fragment");
			}
		});

		return v;
	}

}
