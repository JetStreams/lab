/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mario
 */
public class GlobeTypeTest {
   

    /**
     * Test of getKey method, of class GlobeType.
     */
    @Test
    public void testGetKey() {
        Optional<GlobeType> result = GlobeType.getByKey(null);
        assertFalse(result.isPresent());
        result = GlobeType.getByKey(" ");
        assertFalse(result.isPresent());
        result = GlobeType.getByKey(GlobeType.Full.getKey());
        assertTrue(result.isPresent());
        assertEquals(GlobeType.Full, result.get());
    }
    
}
