/*
 * 
 */
package de.science.hack;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

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
    @Ignore
    public void testRead() {
        String name = getClass().getResource("wind.txt").getFile();
        List<Stl> result = classUnderTest.read(name);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(6480, result.size());
    }
}