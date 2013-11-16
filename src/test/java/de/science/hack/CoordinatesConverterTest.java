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
    public void testToStl() {
        StlPoint point = CoordinatesConverter.toStl(new Coordinate(0, 0, 0));
        assertNotNull(point);
        assertEquals(12756274.0, point.getX(), DIFF);
        assertEquals(6378137.0, point.getY(), DIFF);
        assertEquals(6378137.0, point.getZ(), DIFF);
        
        point = CoordinatesConverter.toStl(new Coordinate(-180, 0, 0));
        assertNotNull(point);
        assertEquals(0.0, point.getX(), DIFF);
        assertEquals(6378137.0, point.getY(), DIFF);
        assertEquals(6378137.0, point.getZ(), DIFF);
    }
}