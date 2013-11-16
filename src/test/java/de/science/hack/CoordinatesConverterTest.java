/*
 * 
 */
package de.science.hack;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mario
 */
public class CoordinatesConverterTest {
   
    private static final double DIFF = 0.1;

    @Test
    public void testToSlf() {
        LatLonAlt lla = new LatLonAlt(0, 0, 0);
        Slf result = CoordinatesConverter.toSlf(lla);
        assertNotNull(result);
        assertEquals(12756274.0, result.getX(), DIFF);
        assertEquals(6378137.0, result.getY(), DIFF);
        assertEquals(6378137.0, result.getZ(), DIFF);
        
        lla = new LatLonAlt(0, -180, 0);
        result = CoordinatesConverter.toSlf(lla);
        assertNotNull(result);
        assertEquals(0.0, result.getX(), DIFF);
        assertEquals(6378137.0, result.getY(), DIFF);
        assertEquals(6378137.0, result.getZ(), DIFF);
    }
}