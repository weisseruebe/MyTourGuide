package swe.semesterarbeit;

import java.io.File;

import swe.model.Tour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

public class MainActivity extends Activity {
	private GridView gridView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		createTourFolder();
		GridAdapter adapter = new GridAdapter(this);
		gridView = (GridView) findViewById(R.id.grid);
		gridView.setAdapter(adapter);
	}

	private void createTourFolder() {
		new File(new File(getFilesDir()+"/tours").getAbsolutePath()).mkdirs();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK){
			switch (requestCode) {
			case 123:
				Tour result = (Tour) data.getSerializableExtra("tour");
				if (result != null){
					Intent intent = new Intent(this,RecordRouteMapActivity.class);
					intent.putExtra("tour", result);
					startActivityForResult(intent, 124);
				}
				break;

			default:
				break;
			}
		}
	}
	

}