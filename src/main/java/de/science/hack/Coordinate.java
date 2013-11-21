/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import javax.vecmath.Point3d;

/**
 * Represents longitude, latitude and altitude in real world.
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
    
    public void setAlt(double alt){
        z = alt;
    }
}
