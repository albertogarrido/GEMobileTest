package net.albertogarrido.gemobiletest.model.entities.comparators;

import net.albertogarrido.gemobiletest.model.entities.Suggestion;

import java.util.Comparator;

public class SuggestionDistanceComparator implements Comparator<Suggestion> {

    @Override
    public int compare(Suggestion suggestion1, Suggestion suggestion2) {
        return Double.compare( suggestion1.getDistance(),  suggestion2.getDistance());
    }
}
