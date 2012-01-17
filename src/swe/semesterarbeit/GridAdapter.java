package swe.semesterarbeit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class GridAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater layoutInflater;
	
    private final Integer[] iconIDs = {
    		R.drawable.ic_launcher,
    		R.drawable.ic_launcher,
    		R.drawable.ic_launcher,
    		R.drawable.ic_launcher
    };
    private String[] btnTexts;
    
	public GridAdapter(Context c) {
		context = c;
		layoutInflater = LayoutInflater.from(context);
		
		btnTexts = new String[] {
	    		context.getString(R.string.btn_routes),
	    		context.getString(R.string.btn_record),
	    		context.getString(R.string.btn_settings),
	    		context.getString(R.string.btn_credits)
	    };
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return iconIDs.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return iconIDs[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// creates a new ImageView for each item referenced by the Adapter
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View gridView;
		if (convertView == null) {
			gridView = new View(context);
			gridView = layoutInflater.inflate(R.layout.gridlayout, null);
		}
		else {
			gridView = (View) convertView;
		}

		Button button = (Button) gridView.findViewById(R.id.button);
		button.setCompoundDrawablesWithIntrinsicBounds(0, iconIDs[position], 0, 0);
		button.setText(btnTexts[position]);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (position) {
				case 0:	// "My Routes"
					Intent intent_routes = new Intent(context, FileListActivity.class);
					intent_routes.putExtra("folder", "/tours/");
					context.startActivity(intent_routes);
					break;
					
				case 1:	// "Record new Route"
					Intent intent_rec = new Intent(context, EditTourActivity.class);
					((Activity)context).startActivityForResult(intent_rec, 123);
//					context.startActivityForResult(intent_rec);
					break;
					
				case 2:	// "Settings"
					Intent intent_prefs = new Intent(context, UserPreferences.class);
					context.startActivity(intent_prefs);
					break;
					
				case 3:	// "Cretids"
					// TODO: show WebView
					break;
					
				default:
					break;
				}
			}
		});

		return gridView;
	}

}