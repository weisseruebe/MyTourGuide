package swe.semesterarbeit;

import swe.model.Poi;
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
		Poi poi = (Poi)getIntent().getSerializableExtra("poi");
		setContentView(R.layout.editpoiactivity);

		TextView textPoiData = (TextView) findViewById(R.id.poidata);
		String lat = poi.lat+"";
		String lon = poi.lon+"";
		
		textPoiData.setText(poi.name+"\n"+lat+"\n"+lon);
		Button btnSavePos = (Button) findViewById(R.id.btnSavePoi);
		btnSavePos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				end();				
			}
		});
	}
	
	
	
	public void end(){
		EditText editPoi = (EditText) findViewById(R.id.editTextPoiName);
		Poi poi = (Poi)getIntent().getSerializableExtra("poi");
		Intent result = new Intent();
		poi.name = editPoi.getText().toString();
		result.putExtra("poi", poi);
		setResult(RESULT_OK,result);
		finish();
	}
	
	
}
