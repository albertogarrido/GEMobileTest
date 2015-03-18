package net.albertogarrido.gemobiletest.model.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.albertogarrido.gemobiletest.model.deserializers.SuggestionDeserializer;
import net.albertogarrido.gemobiletest.model.entities.Suggestion;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class SuggestionRestClient {

    private static final String API_URL = "http://api.goeuro.com/api/v2/position";
    private SuggestionAPI suggestionAPI;

    public SuggestionRestClient() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Suggestion.class, new SuggestionDeserializer());

        Gson gson = gsonBuilder.create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        suggestionAPI = restAdapter.create(SuggestionAPI.class);
    }

    public SuggestionAPI getSuggestionAPI() {
        return suggestionAPI;
    }

}
