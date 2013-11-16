/*
 * 
 */
package de.science.hack;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mario
 */
public class WindDataReaderTest {
    
    private static final double DIFF = 0.1;
    
    private WindDataReader classUnderTest;
    
    @Before
    public void setUp() {
        classUnderTest = new WindDataReader();
    }

    /**
     * Test of read method, of class WindDataReader.
     */
    @Test
    public void testRead() {
        String name = getClass().getResource("wind.txt").getFile();
        List<StlCoordinate> result = classUnderTest.read(name);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(6480, result.size());
        StlCoordinate first = result.get(0);
        assertEquals(8670555, first.getX(), DIFF);
    }
}