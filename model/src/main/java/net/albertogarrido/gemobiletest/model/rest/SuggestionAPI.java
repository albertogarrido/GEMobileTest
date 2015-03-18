package net.albertogarrido.gemobiletest.model.rest;

import net.albertogarrido.gemobiletest.model.entities.Suggestion;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;

public interface SuggestionAPI {
        @GET("/suggest/{locale}/{term}")
        public ArrayList<Suggestion> getSuggestion(@Path("locale") String locale, @Path("term") String term);
}
