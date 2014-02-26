package com.dmurphy.dnd.csheet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmurphy.dnd.csheet.character.Power;

public class AbilityView extends RelativeLayout {
	private View v;
	private RelativeLayout color;
	private TextView name;
	private TextView req;
	private TextView flavorText;
	private TextView attributes;
	private TextView effect;

	public AbilityView(Context context, Power power) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (inflater != null) {
			v = inflater.inflate(R.layout.ability, this);
		}

		name = (TextView) v.findViewById(R.id.abilityName);
		req = (TextView) v.findViewById(R.id.abilityReq);
		flavorText = (TextView) v.findViewById(R.id.abilityFlavorText);
		attributes = (TextView) v.findViewById(R.id.abilityAttributes);
		effect = (TextView) v.findViewById(R.id.abilityEffect);
		color = (RelativeLayout) v.findViewById(R.id.abilityColor);

		String[] titleColors = new String[2];

		switch (power.getFreq()) {
		case AT_WILL:
			titleColors[0] = "#155C27"; // dark green
			titleColors[1] = "#219C3F"; // lighter green
			break;
		case ENCOUNTER:
			titleColors[0] = "#78001E"; // dark red
			titleColors[1] = "#BF0030"; // lighter red
			break;
		case DAILY:
			titleColors[0] = "#2E2D2D"; // dark grey
			titleColors[1] = "#707070"; // lighter grey
			break;
		}

		GradientDrawable gd = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						Color.parseColor(titleColors[0]),
						Color.parseColor(titleColors[1]) });
		gd.setCornerRadius(0f);
		color.setBackground(gd);

		name.setText(power.getName());
		flavorText.setText(power.getFlavorText());
		req.setText(power.getFormattedReq());
		attributes.setText(Html.fromHtml(power.getFormattedAttributes()));
		effect.setText(Html.fromHtml(power.getFormattedEffect()));
	}

	public AbilityView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public AbilityView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AbilityView(Context context) {
		super(context);
	}

	public TextView getName() {
		return name;
	}

	public void setName(TextView name) {
		this.name = name;
	}

	public TextView getReq() {
		return req;
	}

	public void setReq(TextView req) {
		this.req = req;
	}

	public TextView getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(TextView flavorText) {
		this.flavorText = flavorText;
	}

	public TextView getAttributes() {
		return attributes;
	}

	public void setAttributes(TextView attributes) {
		this.attributes = attributes;
	}

	public TextView getEffect() {
		return effect;
	}

	public void setEffect(TextView effect) {
		this.effect = effect;
	}

}
