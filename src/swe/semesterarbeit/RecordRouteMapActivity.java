package swe.semesterarbeit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;


public class RecordRouteMapActivity extends MapActivity {
	MapView mapView;
	Button btnRecord;
	private TourOverlay tourOverlay;
	private LocationManager lm;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recordroutemap_activity);
		btnRecord = (Button) findViewById(R.id.btnRecord);
		btnRecord.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				addPoiAtUserLocation();				
			}
		});
		
		initMap();
		initLocationManager();
		initRouteOverlay(); 
		try {
			String tour = getIntent().getStringExtra("tour");
			if (tour!=null){
				FileInputStream fileInputStream = new FileInputStream(tour);
				loadTour(fileInputStream);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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

	protected void updateLocation(Location location) {
		GeoPoint gp = new GeoPoint((int)(location.getLatitude()*1E6), (int)(location.getLongitude()*1E6));
		mapView.getController().animateTo(gp);	
		mapView.invalidate();
	}

	protected void addPoiAtUserLocation() {
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {
			Poi poi  = new Poi((int) (location.getLatitude() * 1E6),(int) (location.getLongitude() * 1E6), "Rec");
			
			tourOverlay.addItem(poi);
			
			Intent intent = new Intent(this, EditPoiActivity.class);
			intent.putExtra("poi", poi);
			this.startActivity(intent);
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
	
	private void loadTour(InputStream stream){
		try {
			Tour tour = Tour.load(stream);
			tourOverlay.setTour(tour);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void initRouteOverlay() {
		Drawable defaultMarker = this.getResources().getDrawable(R.drawable.placemark_circle);
		tourOverlay = new TourOverlay(defaultMarker);
		mapView.getOverlays().add(tourOverlay);
	}
		
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
			File file = new File(getFilesDir()+"/tours/tour.xml");
			if (!file.exists()){
				new File(new File(getFilesDir()+"/tours").getAbsolutePath()).mkdirs();
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
