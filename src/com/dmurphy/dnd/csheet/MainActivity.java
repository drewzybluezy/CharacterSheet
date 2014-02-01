package com.dmurphy.dnd.csheet;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Vibrator;

import com.dmurphy.dnd.csheet.character.CharClass;
import com.dmurphy.dnd.csheet.character.CharacterBuild;
import com.dmurphy.dnd.csheet.character.Race;
import com.dmurphy.dnd.csheet.fragment.MenuFragment;
import com.dmurphy.dnd.csheet.fragment.NameFragment;

public class MainActivity extends Activity {
	private MediaPlayer player;
	private boolean musicPlayed = false;
	private FragmentManager fm;
	private Vibrator vib;
	private CharacterBuild character = null;
	private List<Race> races = null;
	private List<CharClass> classes = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		races = CSVLoader.initRaces(this);
		classes = CSVLoader.initClasses(this);

		player = MediaPlayer.create(MainActivity.this, R.raw.music);
		player.start();
		player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				musicPlayed = true;
			}
		});

		fm = getFragmentManager();

		MenuFragment menuFrag = new MenuFragment();
		fm.beginTransaction().add(R.id.frag_container, menuFrag, "menu_frag")
				.commit();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (player.isPlaying()) {
			player.stop();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (player.isPlaying()) {
			player.pause();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!player.isPlaying() && !musicPlayed) {
			player.start();
		}
	}

	@Override
	public void onBackPressed() {
		Fragment frag = fm.findFragmentByTag("name_fragment");
		if (frag instanceof NameFragment && frag.isVisible()) {
			NameFragment f = (NameFragment) frag;
			f.onBackPressed();
		} else {
			super.onBackPressed();
		}
	}

	public void swapFragment(Fragment fragment, String tag) {
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.frag_container, fragment, tag);
		ft.addToBackStack(null);
		ft.commit();
	}

	public Vibrator getVibe() {
		return vib;
	}

	public void setCharacter(CharacterBuild characterBuild) {
		this.character = characterBuild;
	}

	public CharacterBuild getCharacter() {
		return character;
	}

	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	public List<CharClass> getClasses() {
		return classes;
	}

	public void setClasses(List<CharClass> classes) {
		this.classes = classes;
	}

}
