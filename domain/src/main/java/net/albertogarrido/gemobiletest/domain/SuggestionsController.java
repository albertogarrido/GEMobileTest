package net.albertogarrido.gemobiletest.domain;

import net.albertogarrido.gemobiletest.model.entities.GeoPoint;
import net.albertogarrido.gemobiletest.model.entities.Suggestion;
import net.albertogarrido.gemobiletest.model.entities.comparators.SuggestionDistanceComparator;
import net.albertogarrido.gemobiletest.model.rest.SuggestionRestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class SuggestionsController implements ISuggestionsController{

    @Override
    public ArrayList<Suggestion> getSuggestions(String term, GeoPoint currentLocation) {
        SuggestionRestClient rc = new SuggestionRestClient();
        ArrayList<Suggestion> suggestionsList = rc.getSuggestionAPI().getSuggestion(Locale.getDefault().getLanguage() , term);

        for(Suggestion suggestion : suggestionsList){
            suggestion.setDistance(getCoordsDistance(suggestion.getGeoPosition(), currentLocation));
        }
        Collections.sort(suggestionsList, new SuggestionDistanceComparator());
        return suggestionsList;
    }

    private double getCoordsDistance(GeoPoint pointA, GeoPoint pointB){
        double powA = Math.pow(pointA.getLatitude() - pointB.getLatitude(), 2.0);
        double powB = Math.pow(pointA.getLongitude() - pointB.getLongitude(), 2.0);
        double distance = Math.sqrt( powA + powB );
        return distance;
    }


}
