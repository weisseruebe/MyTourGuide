package swe.semesterarbeit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class UserPreferences extends PreferenceActivity {
	private static final String FONT_SIZE = "FONT_SIZE";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.userpreferences);
	}

	public static int getSETTINGS_FontSize(Context context) {
		int fontSize = 3;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		fontSize = Integer.parseInt(settings.getString(FONT_SIZE, "3"));
		return fontSize;
	}

}