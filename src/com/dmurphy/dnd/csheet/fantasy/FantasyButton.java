package com.dmurphy.dnd.csheet.fantasy;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class FantasyButton extends Button {

	public FantasyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        init();
	}
	
	public FantasyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FantasyButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                                               "vinque.ttf");
        setTypeface(tf);
        this.setTextColor(getResources().getColor(android.R.color.black));
    }

}
