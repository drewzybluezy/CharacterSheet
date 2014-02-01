package com.dmurphy.dnd.csheet.fantasy;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FantasyTextView extends TextView {

	public FantasyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        init();
	}
	
	public FantasyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FantasyTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                                               "vinque.ttf");
        setTypeface(tf);
    }

}
