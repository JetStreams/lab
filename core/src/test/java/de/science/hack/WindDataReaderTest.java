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
    public void testReadFromFile() {
        String name = getClass().getResource("unit/north.txt").getFile();
        SortedMap<Float, List<Line>> result = classUnderTest.read(name);
        verifyResult(result);
    }

    /**
     * Test of read method, of class WindDataReader.
     */
    @Test
    public void testReadFromBytes() {
        String content = "0,69,-2.35675048828125\n"
                + "0,67.5,-2.00323486328125\n"
                + "0,66,-3.88800048828125";
        SortedMap<Float, List<Line>> result = classUnderTest.read(content.getBytes());
        verifyResult(result);
    }

    void verifyResult(SortedMap<Float, List<Line>> result) {
        assertNotNull(result);
        Set<Float> keys = result.keySet();
        assertFalse(keys.isEmpty());

        Float key = -100f;
        for (Map.Entry<Float, List<Line>> entry : result.entrySet()) {

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
