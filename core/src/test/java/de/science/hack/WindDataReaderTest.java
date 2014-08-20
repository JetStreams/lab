/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import de.science.hack.model.Line;
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
        String name = getClass().getResource("unit/north.txt").getFile();
        SortedMap<Float,List<Line>> result = classUnderTest.read(name);
        assertNotNull(result);
        Set<Float> keys = result.keySet();
        assertFalse(keys.isEmpty());
        assertEquals(2, keys.size());
        
        Float key = -100f;
        for (Map.Entry<Float,List<Line>> entry : result.entrySet()) {
            
            Float currentKey = entry.getKey();
            assertNotNull(currentKey);
            assertTrue("expected increasing key", key < currentKey);
            key = currentKey;
            
            List<Line> list = entry.getValue();
            assertFalse(list.isEmpty());
            Line projection = list.get(0);
            assertNotNull(projection);
            assertTrue(projection.getPoint1().getX() != 0.0);
        }
    }
}