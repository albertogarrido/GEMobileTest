package net.albertogarrido.gemobiletest.presenters;

import android.text.Editable;

import com.google.android.gms.common.api.GoogleApiClient;

public interface ITravelSearchPresenter {
    void getLocation(GoogleApiClient googleApiClient);
    void validateFieldsComplete(Editable textFrom, Editable textTo);
    void search();
    void formatChoosenDate(int year, int month, int day);
    int getMonthNumberFromString(String s);
}
