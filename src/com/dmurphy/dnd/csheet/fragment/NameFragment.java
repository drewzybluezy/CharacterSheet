package com.dmurphy.dnd.csheet.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dmurphy.dnd.csheet.MainActivity;
import com.dmurphy.dnd.csheet.R;

public class NameFragment extends Fragment {
	private MainActivity activity;
	private Vibrator vibe;
	private AlertDialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.name_fragment, container, false);

		activity = (MainActivity) getActivity();
		vibe = activity.getVibe();

		Button cancel = (Button) v.findViewById(R.id.cancelButton);
		final Button next = (Button) v.findViewById(R.id.nextButton);
		final EditText nameEdit = (EditText) v.findViewById(R.id.char_name);

		next.setEnabled(false);
		next.setAlpha(0.3f);

		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (nameEdit.getText().length() > 0) {
					activity.getCharacter().setName(
							nameEdit.getText().toString());
				}
				vibe.vibrate(50);
				activity.swapFragment(new RaceFragment(), "race_fragment");
			}
		});

		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vibe.vibrate(50);
				dialog.show();
			}
		});

		nameEdit.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable arg0) {
				if (arg0.length() > 0) {
					next.setAlpha(1.0f);
					next.setEnabled(true);
				} else {
					next.setEnabled(false);
					next.setAlpha(0.3f);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}
		});

		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// 2. Chain together various setter methods to set the dialog
		// characteristics
		builder.setMessage("Are you sure you wish to discard this character?")
				.setTitle(R.string.cancel);

		// Set the action buttons
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				vibe.vibrate(50);
				getFragmentManager().popBackStack();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				vibe.vibrate(50);
			}
		});

		dialog = builder.create();

		return v;
	}
	
	public void onBackPressed() {
		dialog.show();
	}
}
