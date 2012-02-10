package swe.semesterarbeit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class SimpleGridAdapter extends BaseAdapter {
	private Context context;
	private String[] btnTexts;
	private Button[] buttons;
	
    private final Integer[] iconIDs = {
    		R.drawable.ic_launcher,
    		R.drawable.ic_launcher,
    		R.drawable.ic_launcher,
    		R.drawable.ic_launcher
    };
    
	public SimpleGridAdapter(Context c) {
		context = c;
		
		btnTexts = new String[] {
	    		context.getString(R.string.btn_routes),
	    		context.getString(R.string.btn_record),
	    		context.getString(R.string.btn_settings),
	    		context.getString(R.string.btn_credits)
	    };
		
		
		Button button1 = new Button(context);	
		button1.setCompoundDrawablesWithIntrinsicBounds(0, iconIDs[0], 0, 0);
		button1.setText(btnTexts[0]);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_routes = new Intent(context, FileListActivity.class);
				intent_routes.putExtra("folder", "/tours/");
				context.startActivity(intent_routes);
			}
		});
		
		Button button2 = new Button(context);
		button2.setCompoundDrawablesWithIntrinsicBounds(0, iconIDs[1], 0, 0);
		button2.setText(btnTexts[1]);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_rec = new Intent(context, EditTourActivity.class);
				((Activity)context).startActivityForResult(intent_rec, MainActivity.REQUESTTOUR);
			}
		});

		Button button3 = new Button(context);
		button3.setCompoundDrawablesWithIntrinsicBounds(0, iconIDs[2], 0, 0);
		button3.setText(btnTexts[2]);
		//Listener
		
		Button button4 = new Button(context);
		button4.setCompoundDrawablesWithIntrinsicBounds(0, iconIDs[3], 0, 0);
		button4.setText(btnTexts[3]);
		//Listener
		
		buttons = new Button[]{button1,button2,button3,button4};
	}

	@Override
	public int getCount() {
		return buttons.length;
	}

	@Override
	public Object getItem(int position) {
		return iconIDs[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		return buttons[position];
	}

}