package swe.semesterarbeit;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
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
		Intent intent = getIntent();
		
		/* Fuellen */
		TextView textPoiData = (TextView) findViewById(R.id.poidata);
		double lat = intent.getDoubleExtra("lat",0);
		double lon = intent.getDoubleExtra("lon",0);
		String latString = Location.convert(lat,Location.FORMAT_DEGREES);
		String lonString = Location.convert(lon,Location.FORMAT_DEGREES);
		
		textPoiData.setText(latString+"\n"+lonString);
		
		Button btnSavePos = (Button) findViewById(R.id.btnSavePoi);
		btnSavePos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				end();				
			}
		});
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
