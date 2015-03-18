package net.albertogarrido.gemobiletest.model.entities;

import java.io.Serializable;

public class Suggestion implements Serializable {

    private Integer id;
    private boolean coreCountry;
    private String country;
    private String countryCode;
    private double distance;
    private String fullName;
    private GeoPoint geoPosition;
    private String iataAirportCode;
    private boolean inEurope;
    private String key;
    private Integer locationId;
    private String name;
    private String type;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getCoreCountry() {
        return this.coreCountry;
    }

    public void setCoreCountry(boolean coreCountry) {
        this.coreCountry = coreCountry;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public GeoPoint getGeoPosition() {

        return this.geoPosition;
    }

    public void setGeoPosition(GeoPoint geoPosition) {
        this.geoPosition = geoPosition;
    }

    public String getIataAirportCode() {
        return this.iataAirportCode;
    }

    public void setIataAirportCode(String iataAirportCode) {
        this.iataAirportCode = iataAirportCode;
    }

    public boolean getInEurope() {
        return this.inEurope;
    }

    public void setInEurope(boolean inEurope) {
        this.inEurope = inEurope;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return fullName;
    }
}
