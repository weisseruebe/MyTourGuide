package swe.semesterarbeit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import swe.model.Tour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FileListAdapter extends ArrayAdapter<File> {
	Context context;
	int layoutID;
	File[] files;
	
	public FileListAdapter(Context c, int textViewResourceId, File[] files) {
		super(c, textViewResourceId, files);
		context = c;
		layoutID = textViewResourceId;
		this.files = files;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(layoutID, parent, false);
		
		TextView text1 = (TextView) view.findViewById(R.id.text1);
		text1.setText(files[position].getName());
		TextView text2 = (TextView) view.findViewById(R.id.text2);
		text2.setText(files[position].getPath());
		
		try {
			Tour tour = Tour.load(new FileInputStream(files[position].getPath()));
			if (tour.name.length()>0){
				text1.setText(tour.name);
			}
			if (tour.description.length()>0){
				text2.setText(tour.description);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		view.setOnClickListener(new OnListItemClickListener(position));
		view.setOnLongClickListener(new OnListItemLongClickListener(position));
		
		return view;
	}
	
	
	
	private class OnListItemClickListener implements OnClickListener {
		File file;
		public OnListItemClickListener(int position) {
			this.file = files[position];
		}
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(context, RecordRouteMapActivity.class);
			
			try {
				FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
				Tour tour = Tour.load(fileInputStream);
				intent.putExtra("tour", tour);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
