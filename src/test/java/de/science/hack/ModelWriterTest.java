/*
 * 
 */
package de.science.hack;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import toxi.geom.mesh.Mesh3D;

/**
 *
 * @author Mario
 */
public class ModelWriterTest {
    
    private ModelWriter classUnderTest;
    
    @Before
    public void setUp() {
        classUnderTest = new ModelWriter();
    }

    /**
     * Test of write method, of class ModelWriter.
     */
    @Test
    public void testWrite() {
        
        ModelReader reader = new ModelReader();
        Mesh3D source = reader.readEarth();
        String path = getClass().getResource(".").getFile();
        File file = new File(path, "out.stl");
        classUnderTest.write(file, source);
        assertTrue(file.exists());
        
        Mesh3D exported = reader.read(file.getPath());
        assertNotNull(exported);
        assertEquals(source.getFaces().size(), exported.getFaces().size());
    }
}