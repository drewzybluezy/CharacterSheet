package com.dmurphy.dnd.csheet.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dmurphy.dnd.csheet.MainActivity;
import com.dmurphy.dnd.csheet.R;

public class ArmorFragment extends Fragment {
	private MainActivity activity;
	private Vibrator vibe;
	private Button cloth;
	private Button leather;
	private Button hide;
	private Button chainmail;
	private Button scale;
	private Button plate;
	private Button lightShield;
	private Button heavyShield;
	private boolean[] selectedArmor = new boolean[8];

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.armor_fragment, container, false);

		activity = (MainActivity) getActivity();
		vibe = activity.getVibe();

		Button previous = (Button) v.findViewById(R.id.previousButton);
		Button next = (Button) v.findViewById(R.id.nextButton);

		cloth = (Button) v.findViewById(R.id.cloth_armor);
		leather = (Button) v.findViewById(R.id.leather_armor);
		hide = (Button) v.findViewById(R.id.hide_armor);
		chainmail = (Button) v.findViewById(R.id.chainmail);
		scale = (Button) v.findViewById(R.id.scale_armor);
		plate = (Button) v.findViewById(R.id.plate_armor);
		lightShield = (Button) v.findViewById(R.id.light_shield);
		heavyShield = (Button) v.findViewById(R.id.heavy_shield);
		
		setArmorEnabled();

		TextView gold = (TextView) v.findViewById(R.id.gold);
		gold.setText(activity.getCharacter().getWealth() + "");

		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(50);
				activity.swapFragment(new WeaponsFragment(), "weapons_fragment");
			}
		});

		previous.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vibe.vibrate(50);
				getFragmentManager().popBackStack();
			}
		});

		cloth.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(25);
				if (!selectedArmor[0]) {
					cloth.setBackground(getResources().getDrawable(R.drawable.cloth_armor_s));
					selectedArmor[0] = true;
				} else {
					cloth.setBackground(getResources().getDrawable(R.drawable.cloth_armor));
					selectedArmor[0] = false;
				}
			}
		});
		
		leather.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(25);
				if (!selectedArmor[1]) {
					leather.setBackground(getResources().getDrawable(R.drawable.leather_armor_s));
					selectedArmor[1] = true;
				} else {
					leather.setBackground(getResources().getDrawable(R.drawable.leather_armor));
					selectedArmor[1] = false;
				}
			}
		});
		
		hide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(25);
				if (!selectedArmor[2]) {
					hide.setBackground(getResources().getDrawable(R.drawable.hide_armor_s));
					selectedArmor[2] = true;
				} else {
					hide.setBackground(getResources().getDrawable(R.drawable.hide_armor));
					selectedArmor[2] = false;
				}
			}
		});
		
		chainmail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(25);
				if (!selectedArmor[3]) {
					chainmail.setBackground(getResources().getDrawable(R.drawable.chainmail_s));
					selectedArmor[3] = true;
				} else {
					chainmail.setBackground(getResources().getDrawable(R.drawable.chainmail));
					selectedArmor[3] = false;
				}
			}
		});
		
		scale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(25);
				if (!selectedArmor[4]) {
					scale.setBackground(getResources().getDrawable(R.drawable.scale_armor_s));
					selectedArmor[4] = true;
				} else {
					scale.setBackground(getResources().getDrawable(R.drawable.scale_armor));
					selectedArmor[4] = false;
				}
			}
		});
		
		plate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(25);
				if (!selectedArmor[5]) {
					plate.setBackground(getResources().getDrawable(R.drawable.plate_armor_s));
					selectedArmor[5] = true;
				} else {
					plate.setBackground(getResources().getDrawable(R.drawable.plate_armor));
					selectedArmor[5] = false;
				}
			}
		});
		
		lightShield.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(25);
				if (!selectedArmor[6]) {
					lightShield.setBackground(getResources().getDrawable(R.drawable.light_shield_s));
					selectedArmor[6] = true;
				} else {
					lightShield.setBackground(getResources().getDrawable(R.drawable.light_shield));
					selectedArmor[6] = false;
				}
			}
		});
		
		heavyShield.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(25);
				if (!selectedArmor[7]) {
					heavyShield.setBackground(getResources().getDrawable(R.drawable.heavy_shield_s));
					selectedArmor[7] = true;
				} else {
					heavyShield.setBackground(getResources().getDrawable(R.drawable.heavy_shield));
					selectedArmor[7] = false;
				}
			}
		});
		
		return v;
	}

	private void setArmorEnabled() {
		boolean[] armorChoices = activity.getCharacter().getClassChoice()
				.getAvailableArmors();
		for (int i = 0; i < armorChoices.length; i++) {
			switch (i) {
			case 0:
				setAlphaEnabled(cloth, armorChoices[i]);
				break;
			case 1:
				setAlphaEnabled(leather, armorChoices[i]);
				break;
			case 2:
				setAlphaEnabled(hide, armorChoices[i]);
				break;
			case 3:
				setAlphaEnabled(chainmail, armorChoices[i]);
				break;
			case 4:
				setAlphaEnabled(scale, armorChoices[i]);
				break;
			case 5:
				setAlphaEnabled(plate, armorChoices[i]);
				break;
			case 6:
				setAlphaEnabled(lightShield, armorChoices[i]);
				break;
			case 7:
				setAlphaEnabled(heavyShield, armorChoices[i]);
				break;
			}

		}
	}
	
	private void setAlphaEnabled(Button button, boolean enabled) {
		button.setEnabled(enabled);
		if (enabled) {
			button.setAlpha(1.0f);
		} else {
			button.setAlpha(0.3f);
		}
	}

}
