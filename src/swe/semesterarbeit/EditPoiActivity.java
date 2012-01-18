package swe.semesterarbeit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/***
 * Vorschlag für Modellierung:
 * Eine FileListView, die den Adapter und das Layout automatisch erzeugt
 * und nur mit dem Dateitypen fuer einen Filter gefüttert wird.
 * @author andreasrettig
 *
 */

public class EditPoiActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editpoiactivity);

		/* Fuellen per Hand */
		//		TextView textPoiData = (TextView) findViewById(R.id.lat);
		//		double lat = intent.getDoubleExtra("lat",0);
		//		double lon = intent.getDoubleExtra("lon",0);
		//		String latString = Location.convert(lat,Location.FORMAT_DEGREES);
		//		String lonString = Location.convert(lon,Location.FORMAT_DEGREES);

		autoFillWidgets();
		
		Button btnSavePos = (Button) findViewById(R.id.btnSavePoi);
		btnSavePos.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				end();				
			}
		});
	}

	private void autoFillWidgets() {
		Intent intent = getIntent();

		/* Automatisch fuellen */
		for (String key:intent.getExtras().keySet()){
			int id = getResources().getIdentifier(key, "id", this.getPackageName());
			View view = findViewById(id);
			if (view!=null){
				if (view instanceof TextView){
					((TextView) view).setText(intent.getExtras().get(key).toString());
				}
				if (view instanceof EditText){
					((EditText)view).setText(intent.getExtras().get(key).toString());
				}
			}
		}
	}

	public void end(){
		EditText poiName = (EditText) findViewById(R.id.editTextPoiName);
		EditText poiDesc = (EditText) findViewById(R.id.editTextPoiDesc);

		Intent result = getIntent();
		result.putExtra("name",poiName.getText().toString());
		result.putExtra("desc",poiDesc.getText().toString());

		setResult(RESULT_OK,result);
		finish();
	}

}
