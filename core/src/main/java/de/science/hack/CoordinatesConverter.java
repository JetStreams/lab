/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

/**
 * Converter for lat, long & altitude into to spherical coordinates.<br/>
 * We don't need eccentricity since our earth model is simply a sphere and not a 
 * geoid.
 *
 * @author Mario
 */
public class CoordinatesConverter {

    /*
     * WGS84 ellipsoid constants Radius
     */
    static final double RADIUS = 6378137;

    private CoordinatesConverter() {
    }

    public static ModelPoint toModel(Coordinate lla) {
        double lat = Math.toRadians(lla.getLat());
        double lon = Math.toRadians(lla.getLon());
        double alt = lla.getAlt();

        double x = RADIUS * Math.cos(lat) * Math.cos(lon);
        double y = RADIUS * Math.cos(lat) * Math.sin(lon);
        double z = (RADIUS + alt) * Math.sin(lat);
        
        return new ModelPoint(x, y, z);
    }
}
