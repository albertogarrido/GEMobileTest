package net.albertogarrido.gemobiletest.views;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import net.albertogarrido.gemobiletest.R;
import net.albertogarrido.gemobiletest.model.entities.GeoPoint;
import net.albertogarrido.gemobiletest.presenters.ITravelSearchPresenter;
import net.albertogarrido.gemobiletest.presenters.TravelSearchPresenter;
import net.albertogarrido.gemobiletest.views.adapters.SuggestionAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class TravelSearchActivity
        extends FragmentActivity
        implements TravelSearchView,
        AdapterView.OnItemClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        DatePickerDialog.OnDateSetListener{

    private ITravelSearchPresenter travelSearchPresenter;
    private SuggestionAdapter suggestionAdapter;

    private GoogleApiClient googleApiClient;

    @InjectView(R.id.tvFrom) AutoCompleteTextView tvFrom;
    @InjectView(R.id.tvTo) AutoCompleteTextView tvTo;
    @InjectView(R.id.searchButton) Button searchButton;
    @InjectView(R.id.dateChooser) LinearLayout dateChooser;
    @InjectView(R.id.dateDay) TextView dateDay;
    @InjectView(R.id.dateMonth) TextView dateMonth;
    @InjectView(R.id.dateYear) TextView dateYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_search);

        ButterKnife.inject(this);

        tvFrom.setOnItemClickListener(this);
        tvTo.setOnItemClickListener(this);

        Log.d("ACTIVITY MAIN ", "onCreate");

        travelSearchPresenter = new TravelSearchPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient = new GoogleApiClient.Builder(this)
                                .addApi(LocationServices.API)
                                .addConnectionCallbacks(this)
                                .addOnConnectionFailedListener(this)
                                .build();
    }

    @Override
    public void onResume() {
        super.onResume();
        googleApiClient.connect();
    }

    @Override
    public void onPause() {
        googleApiClient.disconnect();
        super.onPause();
    }

    @Override
    public void showSearchButton() {
        searchButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCurrentLocationHint(String currentLocationName) {
        tvFrom.setHint(getResources().getString(R.string.hintFrom) + " " + currentLocationName);
        tvTo.setHint(getResources().getString(R.string.hintTo));
    }


    @Override
    public void showChoosenDate(int year, String month, int day) {
            dateDay.setText(day < 10 ? "0"+day : Integer.toString(day));
            dateMonth.setText(month);
            dateYear.setText(Integer.toString(year));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setAdapters(GeoPoint location) {
        suggestionAdapter = new SuggestionAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        suggestionAdapter.setCurrentLocation(location);

        tvFrom.setAdapter(suggestionAdapter);
        tvTo.setAdapter(suggestionAdapter);
    }



    //LISTENERS
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        travelSearchPresenter.validateFieldsComplete(tvFrom.getText(), tvTo.getText());
        dismissKeyboard();
    }

    private void dismissKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @OnClick(R.id.searchButton)
    public void searchTravel() {
        travelSearchPresenter.search();
    }

    @OnClick(R.id.dateChooser)
    public void showDateChooser() {
        DialogFragment dateChooserFragment = new DateChooserFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("year", new Integer(dateYear.getText().toString()));
        int month = travelSearchPresenter.getMonthNumberFromString(dateMonth.getText().toString());
        bundle.putInt("month", month);
        bundle.putInt("day", new Integer(dateDay.getText().toString()));
        dateChooserFragment.setArguments(bundle);
        dateChooserFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (view.isShown()) {
            travelSearchPresenter.formatChoosenDate(year, month, day);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("buttonVisibility", searchButton.getVisibility());
        savedInstanceState.putString("fromText", tvFrom.getText() != null ? tvFrom.getText().toString() : "");
        savedInstanceState.putString("toText", tvTo.getText() != null ? tvTo.getText().toString() : "");
        savedInstanceState.putString("day", dateDay.getText() != null ? dateDay.getText().toString() : "");
        savedInstanceState.putString("month", dateMonth.getText() != null ? dateMonth.getText().toString() : "");
        savedInstanceState.putString("year", dateYear.getText() != null ? dateYear.getText().toString() : "");

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int buttonVisibility = savedInstanceState.getInt("buttonVisibility");
        String fromText = savedInstanceState.getString("fromText");
        String toText = savedInstanceState.getString("toText");
        String day = savedInstanceState.getString("day");
        String month = savedInstanceState.getString("month");
        String year = savedInstanceState.getString("year");

        switch(buttonVisibility){
            case View.VISIBLE:
                searchButton.setVisibility(View.VISIBLE);
                break;
            case View.INVISIBLE:
                searchButton.setVisibility(View.INVISIBLE);
                break;

        }
        tvFrom.setText(fromText);
        tvTo.setText(toText);
        dateDay.setText(day);
        dateMonth.setText(month);
        dateYear.setText(year);

    }

    // LOCATION METHODS
    @Override
    public void onConnected(Bundle bundle) {
        travelSearchPresenter.getLocation(googleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        //TODO
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //TODO
    }


}
