package swe.semesterarbeit;

import java.io.File;

import android.app.ListActivity;
import android.os.Bundle;

/***
 * Vorschlag für Modellierung:
 * Eine FileListView, die den Adapter und das Layout automatisch erzeugt
 * und nur mit dem Dateitypen fuer einen Filter gefüttert wird.
 * @author andreasrettig
 *
 */

public class FileListActivity extends ListActivity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String subFolder = getIntent().getStringExtra("folder");
		setContentView(R.layout.myroutes_listactivity);
		
		final File rootFolder = new File(getFilesDir().getAbsolutePath()+subFolder);
		File[] routes = rootFolder.listFiles();
		
		RouteListAdapter adapter = new RouteListAdapter(this, R.layout.myroutes_listitem, routes);
		setListAdapter(adapter);
	}
	
}
