/*
 * 
 */
package de.science.hack;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import toxi.geom.Triangle3D;

/**
 *
 * @author Mario
 */
public class MeshConverterTest {
    
    private List<StlCoordinate> stlCoordinates;
    
    
    @Before
    public void setUp() {
        WindDataReader reader = new WindDataReader();
        String name = getClass().getResource("short.txt").getFile();
        stlCoordinates = reader.read(name);
    }

    /**
     * Test of toTriangle method, of class MeshConverter.
     */
    @Test
    @Ignore
    public void testToTriangle() {
        List<Triangle3D> result = MeshConverter.toTriangle(stlCoordinates);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertFalse(result.size() == stlCoordinates.size());
    }
}