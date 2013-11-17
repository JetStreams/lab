/*
 * 
 */
package de.science.hack;

import java.io.File;
import java.util.List;
import java.util.SortedMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import toxi.geom.AABB;
import toxi.geom.mesh.TriangleMesh;

/**
 *
 * @author Mario
 */
public class MeshBuilderTest {
    
    private ModelWriter writer;
    
    private SortedMap<Float,List<PointProjection>> data;
    
    private MeshBuilder classUnderTest;
    
    
    @Before
    public void setUp() {
        WindDataReader reader = new WindDataReader();
        String name = getClass().getResource("wind.txt").getFile();
        data = reader.read(name);
        classUnderTest = new MeshBuilder();
        
        writer = new ModelWriter();
    }

    /**
     * Test of build method, of class MeshConverter.
     */
    @Test
    public void testBuild() {
        TriangleMesh result = classUnderTest.build(data);
        assertNotNull(result);
        assertFalse(result.getFaces().isEmpty());
        AABB box = result.getBoundingBox();
        assertFalse(box.getMax().x == 0);
        
        String path = getClass().getResource(".").getFile();
        File file = new File(path, "wind-mesh.stl");
        
        writer.write(file, result);
        assertTrue(file.exists());
    }
}