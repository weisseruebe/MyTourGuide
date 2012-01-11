package swe.semesterarbeit;

import Model.Poi;
import Model.Tour;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;


public class RecordRouteMapActivity extends MapActivity {
	MapView mapView;
	private TourOverlay tourOverlay;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recordroutemap_activity);
		
		initMap();
		initLM();
		addOverlay(); 
		addTestData();
	}
	
	private void initLM() {
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
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
		tourOverlay.addItem(new Poi((int)(location.getLatitude()*1E6), (int)(location.getLongitude()*1E6), "Rec"));
		mapView.getController().animateTo(gp);	
		mapView.invalidate();
		//saveTour();
	}

	private void saveTour() {
		try {
			tourOverlay.tour.save(getFilesDir().getAbsolutePath()+"/tour.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadTour(){
		try {
			tourOverlay.setTour(Tour.load(getFilesDir().getAbsolutePath()+"/tour.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void addOverlay() {
		Drawable defaultMarker = this.getResources().getDrawable(R.drawable.placemark_circle);
		tourOverlay = new TourOverlay(defaultMarker);
		mapView.getOverlays().add(tourOverlay);
	}
	
	protected void addTestData() {
//		tourOverlay.addItem(new Poi((int)(52*1E6), (int)(13*1E6),"P"));
		loadTour();
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
}
