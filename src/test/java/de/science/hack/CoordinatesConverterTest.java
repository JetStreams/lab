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
        ModellPoint point = CoordinatesConverter.toModel(new Coordinate(0, 0, 0));
        assertNotNull(point);
        assertEquals(12756274.0, point.getX(), DIFF);
        assertEquals(CoordinatesConverter.RADIUS, point.getY(), DIFF);
        assertEquals(CoordinatesConverter.RADIUS, point.getZ(), DIFF);
        
        point = CoordinatesConverter.toModel(new Coordinate(-180, 0, 0));
        assertNotNull(point);
        assertEquals(0.0, point.getX(), DIFF);
        assertEquals(CoordinatesConverter.RADIUS, point.getY(), DIFF);
        assertEquals(CoordinatesConverter.RADIUS, point.getZ(), DIFF);
    }
}