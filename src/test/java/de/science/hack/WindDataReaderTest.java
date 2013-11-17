/*
 * 
 */
package de.science.hack;

import java.util.List;
import java.util.Map;
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
        Map<String, List<ModellPoint>> result = classUnderTest.read(name);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        
        for (Map.Entry<String, List<ModellPoint>> entry : result.entrySet()) {
            String key = entry.getKey();
            assertNotNull(key);
            assertFalse(key.isEmpty());
            List<ModellPoint> list = entry.getValue();
            assertFalse(list.isEmpty());
            ModellPoint first = list.get(0);
            assertTrue(first.getX() != 0.0);
        }
    }
}