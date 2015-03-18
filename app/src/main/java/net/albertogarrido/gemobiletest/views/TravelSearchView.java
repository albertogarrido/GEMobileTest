package net.albertogarrido.gemobiletest.views;

import net.albertogarrido.gemobiletest.model.entities.GeoPoint;

public interface TravelSearchView extends GEView {

    void showSearchButton();
    void showCurrentLocationHint(String currentLocationName);
    void setAdapters(GeoPoint location);
    void showChoosenDate(int year, String month, int day);

}
