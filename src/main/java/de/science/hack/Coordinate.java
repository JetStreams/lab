/*
 * 
 */
package de.science.hack;

import javax.vecmath.Point3d;

/**
 * Represents longitude, latitude and altitude
 * @author Mario
 */
public class Coordinate extends Point3d{

    public Coordinate(double lon, double lat, double alt) {
        super(lon, lat ,alt);
    }

    public double getLat() {
        return y;
    }

    public double getLon() {
        return x;
    }

    public double getAlt() {
        return z;
    }
    
    
}
