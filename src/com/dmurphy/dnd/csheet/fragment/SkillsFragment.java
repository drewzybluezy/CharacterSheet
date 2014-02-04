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
import android.widget.ListView;
import android.widget.TextView;

import com.dmurphy.dnd.csheet.CSheetReusables;
import com.dmurphy.dnd.csheet.MainActivity;
import com.dmurphy.dnd.csheet.R;

public class SkillsFragment extends Fragment {
	private MainActivity activity;
	private Vibrator vibe;
	private Button next;
	private Button previous;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.list_fragment, container, false);

		activity = (MainActivity) getActivity();
		vibe = activity.getVibe();

		previous = (Button) v.findViewById(R.id.previousButton);
		next = (Button) v.findViewById(R.id.nextButton);

		TextView title = (TextView) v.findViewById(R.id.abilityName);
		title.setText(R.string.skills);

		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vibe.vibrate(50);
				activity.swapFragment(new FeatsFragment(), "feats_fragment");
			}
		});

		previous.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vibe.vibrate(50);
				getFragmentManager().popBackStack();
			}
		});

		ListView listView = (ListView) v.findViewById(R.id.list);
		final ArrayList<String> list = new ArrayList<String>();

		// List<Integer> skillGroups =
		// activity.getCharacter().getClassChoice().getAvailableSkillGroups();
		int[] availableSkills = activity.getCharacter().getClassChoice()
				.getAvailableSkills().clone();

		String trainedSkills = "Trained Skills: ";
		boolean trainedFlag = false;
		for (int i = 0; i < availableSkills.length; i++) {
			if (availableSkills[i] == -1) {
				if (!trainedFlag)
					trainedFlag = true;
				trainedSkills += CSheetReusables.skillNames[i] + ", ";
			}
		}
		if (trainedSkills.lastIndexOf(',') == (trainedSkills.length() - 2)) {
			trainedSkills = trainedSkills.substring(0,
					trainedSkills.length() - 2);
		}

		TextView trained = (TextView) v.findViewById(R.id.trained);
		if (trainedFlag)
			trained.setText(trainedSkills);

		for (int i = 0; i < availableSkills.length; i++) {
			if (availableSkills[i] > 0) {
				for (int j = i; j < availableSkills.length; j++) {
					if (availableSkills[i] == availableSkills[j]) {
						list.add(CSheetReusables.skillNames[j]);
						if (j != i)
							availableSkills[j] = 0;
					}
				}
			}
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(
				v.getContext(), android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				next.setEnabled(true);
				next.setAlpha(1f);
			}
		});

		return v;
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
