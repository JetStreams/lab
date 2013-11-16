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
    public void testToEcef() {
        LatLonAlt lla = new LatLonAlt(0, 0, 0);
        Ecef result = CoordinatesConverter.toEcef(lla);
        assertNotNull(result);
        assertEquals(6378137.0, result.getX(), DIFF);
        assertEquals(0.0, result.getY(), DIFF);
        assertEquals(0.0, result.getZ(), DIFF);
    }
}