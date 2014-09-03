/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.model;

/**
 * WGS84 ellipsoid
 * 
 * @author Mario
 */
public enum WGS84 {

    /*
     * Ellipsoid Radius
     */
    RADIUS(6378137);

    private final double value;

    private WGS84(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    
}
