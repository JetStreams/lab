/*
 * 
 */
package de.science.hack;

import java.io.File;
import org.junit.Before;
import org.junit.Test;

import toxi.geom.mesh.Mesh3D;
import static org.junit.Assert.*;

/**
 *
 * @author Mario
 */
public class JetStreamModelWriterTest {
    
    private JetStreamModelWriter classUnderTest;
    
    private ModelReader reader;

    @Before
    public void setUp() {
        classUnderTest = new JetStreamModelWriter();
        reader = new ModelReader();
    }

    @Test
    public void testWrite() {
        
        String input = getClass().getResource("wind.txt").getFile();
        String path = getClass().getResource(".").getFile();
        File file = new File(path, "jetstreams.stl");
        
        classUnderTest.write(input, file.getPath());

        assertTrue(file.exists());
        
        Mesh3D exported = reader.read(file.getPath());
        assertNotNull(exported);
    }

}
