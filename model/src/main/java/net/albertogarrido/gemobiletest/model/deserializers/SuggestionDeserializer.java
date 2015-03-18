package net.albertogarrido.gemobiletest.model.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.albertogarrido.gemobiletest.model.entities.GeoPoint;
import net.albertogarrido.gemobiletest.model.entities.Suggestion;

import java.lang.reflect.Type;

public class SuggestionDeserializer implements JsonDeserializer<Suggestion> {

    public Suggestion deserialize(JsonElement json, Type type, JsonDeserializationContext deserializeContext)
            throws JsonParseException {

            Suggestion suggestion = new Suggestion();

            JsonObject result = json.getAsJsonObject();
            suggestion.setId(!result.get("_id").isJsonNull() ? result.get("_id").getAsInt() : null);
            suggestion.setName(!result.get("name").isJsonNull() ? result.get("name").getAsString() : null);
            suggestion.setFullName(!result.get("fullName").isJsonNull() ? result.get("fullName").getAsString() : null);
            suggestion.setType(!result.get("type").isJsonNull() ? result.get("type").getAsString() : null);
            suggestion.setCountry(!result.get("country").isJsonNull() ? result.get("country").getAsString() : null);
            suggestion.setLocationId(!result.get("locationId").isJsonNull() ? result.get("locationId").getAsInt() : null);
            suggestion.setInEurope(!result.get("inEurope").isJsonNull() ? result.get("inEurope").getAsBoolean() : false);
            suggestion.setCountryCode(!result.get("countryCode").isJsonNull() ? result.get("countryCode").getAsString() : null);
            suggestion.setCoreCountry(!result.get("coreCountry").isJsonNull() ? result.get("coreCountry").getAsBoolean() : false);
            suggestion.setDistance(0.0); // calculated later

            JsonObject geoPositionJSONObject = !result.get("geo_position").isJsonNull() ? result.get("geo_position").getAsJsonObject() : null;

            GeoPoint geoPoint = new GeoPoint();
            geoPoint.setLatitude(!geoPositionJSONObject.get("latitude").isJsonNull() ? geoPositionJSONObject.get("latitude").getAsDouble() : null);
            geoPoint.setLongitude(!geoPositionJSONObject.get("longitude").isJsonNull() ? geoPositionJSONObject.get("longitude").getAsDouble() : null);

            suggestion.setGeoPosition(geoPoint);

        return suggestion;
    }
}
