package swe.semesterarbeit;

import swe.model.Poi;
import swe.model.Tour;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class TourOverlay extends ItemizedOverlay<OverlayItem> {

	Tour tour = new Tour();
	
	public TourOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
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
}
