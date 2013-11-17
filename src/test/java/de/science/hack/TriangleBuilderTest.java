/*
 * 
 */
package de.science.hack;

import java.util.List;
import java.util.SortedMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import toxi.geom.Triangle3D;

/**
 *
 * @author Mario
 */
public class TriangleBuilderTest {
    
    private SortedMap<Float,List<PointProjection>> data;
    
    private TriangleBuilder classUnderTest;
    
    
    @Before
    public void setUp() {
        WindDataReader reader = new WindDataReader();
        String name = getClass().getResource("short.txt").getFile();
//        stlCoordinates = reader.read(name);
        
        classUnderTest = new TriangleBuilder();
    }

    /**
     * Test of build method, of class MeshConverter.
     */
    @Test
    @Ignore
    public void testBuild() {
        List<Triangle3D> result = classUnderTest.build(data);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertFalse(result.size() == data.size());
    }
}