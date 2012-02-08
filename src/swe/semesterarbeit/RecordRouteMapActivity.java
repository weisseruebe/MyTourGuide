package swe.semesterarbeit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import swe.model.Poi;
import swe.model.Tour;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;


public class RecordRouteMapActivity extends MapActivity {
	private MapView mapView;
	private Button btnRecord;
	//Modelling: StereoType Overlay
	private TourOverlay tourOverlay;
	private TextView textView;
	private LocationManager lm;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recordroutemap_activity);
		textView = (TextView)findViewById(R.id.textViewTourName);
		//Modelling: StereoType Button
		btnRecord = (Button) findViewById(R.id.btnRecord);
		btnRecord.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				addPoiAtUserLocation();				
			}
		});
		//Modelling: StereoType MapView
		initMap();
		//Modelling: StereoType LocationService
		initLocationManager();
		//Modelling: StereoType Overlay mit Name!
		initRouteOverlay(); 
		
		Tour tour = (Tour) getIntent().getSerializableExtra("tour");
		if (tour!=null){
			tourOverlay.setTour(tour);
			textView.setText(tour.name);
		}
	}
	
	public void onDestroy(){
		super.onDestroy();

	}
	
	//Modelling: StereoType LocationService
	private void initLocationManager() {
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {}
			
			@Override
			public void onProviderEnabled(String provider) {}
			
			@Override
			public void onProviderDisabled(String provider) {}
			
			@Override
			public void onLocationChanged(Location location) {
				updateLocation(location);
			}
		};
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 100, ll);		
	}
	//Modelling: StereoType LocationService
	protected void updateLocation(Location location) {
		GeoPoint gp = new GeoPoint((int)(location.getLatitude()*1E6), (int)(location.getLongitude()*1E6));
		mapView.getController().animateTo(gp);	
		mapView.invalidate();
	}

	protected void addPoiAtUserLocation() {
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {
			Intent intent = new Intent(this, EditPoiActivity.class);
			intent.putExtra("lat", location.getLatitude());
			intent.putExtra("lon", location.getLongitude());
			this.startActivityForResult(intent,123);
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK){
			double lat = data.getDoubleExtra("lat", 0);
			double lon = data.getDoubleExtra("lon", 0);
			String name = data.getStringExtra("name");
			String desc = data.getStringExtra("desc");
			Poi poi = new Poi(lat, lon, name);
			poi.description = desc;
			tourOverlay.addItem(poi);
		}
	}
	
	private void saveTour(OutputStream stream) {
		try {
			tourOverlay.tour.save(stream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Modelling: StereoType Overlay mit Name
	protected void initRouteOverlay() {
		Drawable defaultMarker = this.getResources().getDrawable(R.drawable.placemark_circle);
		tourOverlay = new TourOverlay(this,defaultMarker);
		mapView.getOverlays().add(tourOverlay);
	}
	
	//Modelling: StereoType MapView
	protected void initMap() {
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		final MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(myLocationOverlay);
        myLocationOverlay.enableCompass();
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
                mapView.getController().animateTo(myLocationOverlay.getMyLocation());
            }
        });
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void onPause() {
		super.onPause();
		try {
			File file = new File(getFilesDir()+"/tours/"+tourOverlay.tour.name+".xml");
			if (!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			saveTour(fileOutputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
