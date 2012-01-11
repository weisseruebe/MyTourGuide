package swe.semesterarbeit;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RouteListAdapter extends ArrayAdapter<File> {
	Context context;
	int layoutID;
	File[] myRoutes;
	
	public RouteListAdapter(Context c, int textViewResourceId, File[] routes) {
		super(c, textViewResourceId, routes);
		context = c;
		layoutID = textViewResourceId;
		myRoutes = routes;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(layoutID, parent, false);
		
		TextView text1 = (TextView) view.findViewById(R.id.text1);
		text1.setText(myRoutes[position].getName());
		TextView text2 = (TextView) view.findViewById(R.id.text2);
		text2.setText(myRoutes[position].getPath());
		
		view.setOnClickListener(new OnListItemClickListener(position));
		view.setOnLongClickListener(new OnListItemLongClickListener(position));
		
		return view;
	}
	
	
	
	private class OnListItemClickListener implements OnClickListener {
		File file;
		public OnListItemClickListener(int position) {
			this.file = myRoutes[position];
		}
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(context, RouteMapActivity.class);
			context.startActivity(intent);
		}
	}
	
	private class OnListItemLongClickListener implements OnLongClickListener {
		int pos;
		public OnListItemLongClickListener(int position) {
			this.pos = position;
		}
		@Override
		public boolean onLongClick(View v) {
			// TODO: show Dialog?
			return true;
		}
	}
	
}
