package net.albertogarrido.gemobiletest.views.adapters;

import android.widget.Filter;

import net.albertogarrido.gemobiletest.domain.ISuggestionsController;
import net.albertogarrido.gemobiletest.domain.SuggestionsController;
import net.albertogarrido.gemobiletest.model.entities.GeoPoint;
import net.albertogarrido.gemobiletest.model.entities.Suggestion;

import java.util.ArrayList;

public class SuggestionFilter extends Filter {

    private final SuggestionAdapter adapter;
    private ISuggestionsController suggestionsController;
    private GeoPoint currentLocation;

    public SuggestionFilter(SuggestionAdapter adapter, GeoPoint currentLocation) {
        super();
        this.adapter = adapter;
        this.currentLocation = currentLocation;
    }

    @Override
    protected FilterResults performFiltering(CharSequence term) {
        FilterResults filterResults = new FilterResults();
        if(term != null) {

            suggestionsController = new SuggestionsController();
            ArrayList<Suggestion> resultList = (ArrayList<Suggestion>) suggestionsController.getSuggestions(term.toString(), currentLocation);

            filterResults.values = resultList;
            filterResults.count = (resultList != null) ? resultList.size() : 0;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.setSuggestionList((ArrayList<Suggestion>) results.values);

        adapter.notifyChanges(!(results != null && results.count > 0));
    }


}
