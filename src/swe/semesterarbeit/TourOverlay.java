package swe.semesterarbeit;

import swe.model.Poi;
import swe.model.Tour;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class TourOverlay extends ItemizedOverlay<OverlayItem> {

	Tour tour = new Tour();
	private Context context;
	
	public TourOverlay(Context c, Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		this.context = c;
	}

	private OverlayItem poiToItem(Poi poi){
		GeoPoint gp = new GeoPoint(poi.lat, poi.lon);
		return new OverlayItem(gp, poi.name, "Poi");
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return poiToItem(tour.poi.get(i));
	}

	@Override
	public int size() {
		return tour.poi.size();
	}

	public void addItem(Poi poi){
		tour.poi.add(poi);
		populate();
	}

	public void setTour(Tour tour) {
		this.tour = tour;
		populate();
	}
	
	protected boolean onTap(int index) {
		Poi item = tour.poi.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(item.name);
		dialog.setMessage("Poi");
		dialog.show();
		return true;
	}
}
