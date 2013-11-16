/*
 * 
 */
package de.science.hack;

/**
 * Represents longitude, latitude and altitude
 * @author Mario
 */
public class LonLatAltCoordinate {
    private double lat;
    private double lon;
    private double alt;

    public LonLatAltCoordinate(double lon, double lat, double alt) {
        this.lon = lon;
        this.lat = lat;
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
