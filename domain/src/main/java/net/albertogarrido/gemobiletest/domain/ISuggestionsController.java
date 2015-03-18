package net.albertogarrido.gemobiletest.domain;

import net.albertogarrido.gemobiletest.model.entities.GeoPoint;
import net.albertogarrido.gemobiletest.model.entities.Suggestion;

import java.util.List;

public interface ISuggestionsController {

    List<Suggestion> getSuggestions(String term, GeoPoint currentLocation);

}
