package com.dmurphy.dnd.csheet.fantasy;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class FantasyEditText extends EditText {
	public FantasyEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public FantasyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FantasyEditText(Context context) {
		super(context);
		init();
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"vinque.ttf");
		setTypeface(tf);
	}

}
