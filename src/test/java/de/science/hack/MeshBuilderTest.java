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
public class MeshBuilderTest {
    
    private List<StlPoint> stlCoordinates;
    
    private MeshBuilder classUnderTest;
    
    
    @Before
    public void setUp() {
        WindDataReader reader = new WindDataReader();
        String name = getClass().getResource("short.txt").getFile();
//        stlCoordinates = reader.read(name);
        
        classUnderTest = new MeshBuilder();
    }

    /**
     * Test of build method, of class MeshConverter.
     */
    @Test
    @Ignore
    public void testBuild() {
        List<Triangle3D> result = classUnderTest.build(stlCoordinates);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertFalse(result.size() == stlCoordinates.size());
    }
}