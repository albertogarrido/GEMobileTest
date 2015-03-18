package net.albertogarrido.gemobiletest.presenters;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.text.Editable;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import net.albertogarrido.gemobiletest.R;
import net.albertogarrido.gemobiletest.model.entities.GeoPoint;
import net.albertogarrido.gemobiletest.views.TravelSearchView;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TravelSearchPresenter implements ITravelSearchPresenter {

    private TravelSearchView travelSearchView;


    public TravelSearchPresenter(TravelSearchView travelSearchView) {
        this.travelSearchView = travelSearchView;

        initDate();
    }

    private void initDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        formatChoosenDate(year, month, day);
    }

    @Override
    public void getLocation(GoogleApiClient googleApiClient) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        travelSearchView.showCurrentLocationHint(getCurrentLocationCity(location));

        GeoPoint geoPoint = new GeoPoint();
        geoPoint.setLatitude(location.getLatitude());
        geoPoint.setLongitude(location.getLongitude());

        travelSearchView.setAdapters(geoPoint);
    }

    private String getCurrentLocationCity(Location loc) {
        String city = "";
        try {
            Geocoder gcd = new Geocoder(travelSearchView.getContext(), Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(loc.getLatitude() ,loc.getLongitude(), 1);
            if (addresses.size() > 0) city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return city;
        }
    }

    @Override
    public void validateFieldsComplete(Editable textFrom, Editable textTo) {
        if (textFrom != null && textTo != null) {
            if (!textFrom.toString().isEmpty() && !textTo.toString().isEmpty()) {
                travelSearchView.showSearchButton();
            }
        }
    }

    @Override
    public void search() {
        Toast toast = Toast.makeText(travelSearchView.getContext(), travelSearchView.getContext().getString(R.string.searchButtonMessage), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public void formatChoosenDate(int year, int month, int day) {
        travelSearchView.showChoosenDate( year, new DateFormatSymbols().getShortMonths()[month],  day);
    }

    //TODO Should go in utilities
    @Override
    public int getMonthNumberFromString(String monthString) {
        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM").parse(monthString);
        } catch (ParseException e) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }


}