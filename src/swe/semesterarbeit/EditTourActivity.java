package swe.semesterarbeit;

import swe.model.Tour;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/***
 * Vorschlag für Modellierung:
 * Eine FileListView, die den Adapter und das Layout automatisch erzeugt
 * und nur mit dem Dateitypen fuer einen Filter gefüttert wird.
 * @author andreasrettig
 *
 */

public class EditTourActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edittouractivity);
		Button btnSaveTour = (Button) findViewById(R.id.btnSaveTour);
		btnSaveTour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				end();
			}
		});
	}
	
	public void end(){
		EditText editTour = (EditText) findViewById(R.id.editTextTourName);
		Tour tour = new Tour();
		Intent result = new Intent();
		tour.name = editTour.getText().toString();
		result.putExtra("tour", tour);
		setResult(RESULT_OK,result);
		finish();
	}
	
	
}
