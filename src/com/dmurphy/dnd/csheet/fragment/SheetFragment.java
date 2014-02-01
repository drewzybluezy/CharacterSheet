package com.dmurphy.dnd.csheet.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmurphy.dnd.csheet.R;

public class SheetFragment extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.character_sheet, container, false);

		return v;
    }

}
