/*
 * 
 */
package de.science.hack;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mario
 */
public class WindDataReaderTest {
    
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
        String name = getClass().getResource("short.txt").getFile();
        SortedMap<Float,List<PointProjection>> result = classUnderTest.read(name);
        assertNotNull(result);
        Set<Float> keys = result.keySet();
        assertFalse(keys.isEmpty());
        assertEquals(2, keys.size());
        
        for (Map.Entry<Float,List<PointProjection>> entry : result.entrySet()) {
            Float key = entry.getKey();
            assertNotNull(key);
            List<PointProjection> list = entry.getValue();
            assertFalse(list.isEmpty());
            PointProjection projection = list.get(0);
            assertNotNull(projection);
            assertTrue(projection.getGroundPoint().getX() != 0.0);
        }
    }
}