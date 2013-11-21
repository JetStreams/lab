/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

/**
 * Converter for lat, long & altitude -> ECEF.
 *
 * @author Mario
 */
public class CoordinatesConverter {

    /*
     * WGS84 ellipsoid constants Radius
     */
    static final double RADIUS = 6378137;
    /*
     * eccentricity
     */
    private static final double e = 8.1819190842622e-2;
    private static final double esq = Math.pow(e, 2);

    private CoordinatesConverter() {
    }

    public static ModellPoint toModel(Coordinate lla) {
        double lat = Math.toRadians(lla.getLat());
        double lon = Math.toRadians(lla.getLon());
        double alt = lla.getAlt();

        double N = RADIUS / Math.sqrt(1 - esq * Math.pow(Math.sin(lat), 2));

        double x = (N + alt) * Math.cos(lat) * Math.cos(lon);
        double y = (N + alt) * Math.cos(lat) * Math.sin(lon);
        double z = ((1 - esq) * N + alt) * Math.sin(lat);
        
        x = Math.round(RADIUS + x);
        y = Math.round(RADIUS + y);
        z = Math.round(RADIUS + z);
        
        return new ModellPoint(x, y, z);
    }
    
}
