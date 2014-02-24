package com.dmurphy.dnd.csheet.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.dmurphy.dnd.csheet.AbilityAdapter;
import com.dmurphy.dnd.csheet.MainActivity;
import com.dmurphy.dnd.csheet.R;
import com.dmurphy.dnd.csheet.character.Power;

public class AtWillFragment extends Fragment {
    private MainActivity activity;
    private Vibrator vibe;
    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        activity = (MainActivity) getActivity();
        vibe = activity.getVibe();

        Button previous = (Button) v.findViewById(R.id.previousButton);
        Button next = (Button) v.findViewById(R.id.nextButton);

        List<Power> fullPowerList = activity.getPowers();
        List<Power> powerList = new ArrayList<Power>();
        for (int i = 0; i < fullPowerList.size(); i++) {
            Power currentPower = fullPowerList.get(i);
            if (currentPower.getReqClass().equals(activity.getCharacter().getClassChoice().getName())) powerList.add(currentPower);
        }
        AbilityAdapter adapter = new AbilityAdapter(v.getContext(), (ArrayList<Power>) powerList);

        list = (ListView) v.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setDivider(new ColorDrawable(android.R.color.transparent));
        list.setDividerHeight(30);

        TextView title = (TextView) v.findViewById(R.id.abilityName);
        title.setText(R.string.at_will);

        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vibe.vibrate(50);
                activity.swapFragment(new EncounterFragment(), "encounter_fragment");
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
