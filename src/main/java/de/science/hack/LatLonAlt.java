/*
 * 
 */
package de.science.hack;

/**
 * Represents longitude, latitude and altitude
 * @author Mario
 */
public class LatLonAlt {
    private double lat;
    private double lon;
    private double alt;

    public LatLonAlt(double lat, double lon, double alt) {
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getAlt() {
        return alt;
    }
    
    
}
