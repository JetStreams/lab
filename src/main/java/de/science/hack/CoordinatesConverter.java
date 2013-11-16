/*
 * 
 */
package de.science.hack;

/**
 * Converter for lat, long & altitude <-> ECEF.
 *
 * @author Mario
 */
public class CoordinatesConverter {

    /*
     * WGS84 ellipsoid constants Radius
     */
    private static final double a = 6378137;
    /*
     * eccentricity
     */
    private static final double e = 8.1819190842622e-2;
    private static final double asq = Math.pow(a, 2);
    private static final double esq = Math.pow(e, 2);

    private CoordinatesConverter() {
    }

    public static StlPoint toStl(Coordinate lla) {
        double lat = Math.toRadians(lla.getLat());
        double lon = Math.toRadians(lla.getLon());
        double alt = lla.getAlt();

        double N = a / Math.sqrt(1 - esq * Math.pow(Math.sin(lat), 2));

        double x = (N + alt) * Math.cos(lat) * Math.cos(lon);
        double y = (N + alt) * Math.cos(lat) * Math.sin(lon);
        double z = ((1 - esq) * N + alt) * Math.sin(lat);
        
        x = Math.round(a + x);
        y = Math.round(a + y);
        z = Math.round(a + z);
        
        return new StlPoint(x, y, z);
    }
    
}
