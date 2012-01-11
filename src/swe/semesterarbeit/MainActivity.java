package swe.semesterarbeit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends Activity {
	private GridView gridView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		GridAdapter adapter = new GridAdapter(this);
		gridView = (GridView) findViewById(R.id.grid);
		gridView.setAdapter(adapter);
	}

}