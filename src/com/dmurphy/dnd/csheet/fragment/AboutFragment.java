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

public class AboutFragment extends Fragment {
	private MainActivity activity;
	private Vibrator vibe;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.about_fragment, container, false);
		
		
		activity = (MainActivity) getActivity();
		vibe = activity.getVibe();
		
		Button back = (Button) v.findViewById(R.id.backButton);
		
		TextView tv = (TextView) v.findViewById(R.id.about_description);
		tv.setText("42");
		
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vibe.vibrate(50);
				getFragmentManager().popBackStack();
			}	
		});

		return v;
	}

}
