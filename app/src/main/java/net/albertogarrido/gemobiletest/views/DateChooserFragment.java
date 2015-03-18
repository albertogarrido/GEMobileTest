package net.albertogarrido.gemobiletest.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

public class DateChooserFragment  extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        Bundle bundle = getArguments();

        int year =  c.get(Calendar.YEAR);
        if(bundle.getInt("year") > 0){
            year = bundle.getInt("year");
        }
        int month = c.get(Calendar.MONTH);
        if(bundle.getInt("month") > 0){
            month = bundle.getInt("month");
        }
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(bundle.getInt("day") > 0){
            day = bundle.getInt("day");
        }

        return new DatePickerDialog(getActivity(), (TravelSearchActivity) getActivity(), year, month, day);
    }
}