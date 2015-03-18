package net.albertogarrido.gemobiletest.model.entities;

public class GeoPoint {
    private Double latitude;
    private Double longitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
