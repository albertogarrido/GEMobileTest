package net.albertogarrido.gemobiletest.views.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import net.albertogarrido.gemobiletest.model.entities.GeoPoint;

import java.util.ArrayList;

public class SuggestionAdapter<Suggestion>
        extends ArrayAdapter<Suggestion>
        implements Filterable {

    private ArrayList<Suggestion> suggestionList;
    private GeoPoint currentLocation;

    public SuggestionAdapter(Context context, int resource) {
        super(context, resource);
        suggestionList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return (suggestionList != null) ? suggestionList.size() : 0;
    }

    @Override
    public Suggestion getItem(int index) {
        return suggestionList.get(index);
    }

    @Override
    public Filter getFilter() {
//        SuggestionFilter suggestionFilter = new SuggestionFilter(this)
        return new SuggestionFilter(this, this.currentLocation);
    }

    public void setCurrentLocation(GeoPoint currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setSuggestionList(ArrayList<Suggestion> list) {
        this.suggestionList = list;
    }

    public void notifyChanges(boolean invalidated) {
        if (invalidated) {
            this.notifyDataSetInvalidated();
        } else {
            this.notifyDataSetChanged();
        }
    }
}