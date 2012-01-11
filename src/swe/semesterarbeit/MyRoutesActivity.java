package swe.semesterarbeit;

import java.io.File;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;

public class MyRoutesActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myroutes_listactivity);
		
		final File rootFolder = Environment.getExternalStorageDirectory();
		// creating mock up content
		File[] routes = rootFolder.listFiles();
		
		RouteListAdapter adapter = new RouteListAdapter(this, R.layout.myroutes_listitem, routes);
		setListAdapter(adapter);
	}
}
