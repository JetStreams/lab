/*
 * 
 */
package de.science.hack;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import toxi.geom.mesh.Mesh3D;
import toxi.geom.mesh.TriangleMesh;

/**
 *
 * @author Mario
 */
public class WindDataIntegrationTest {

    private WindDataReader reader;
    private ModelWriter writer;
    private ModelReader modelReader;

    @Before
    public void setUp() {
        reader = new WindDataReader();
        writer = new ModelWriter();
        modelReader = new ModelReader();
    }

    @Test
    public void testWindDataOutput() {
        TriangleMesh out = new TriangleMesh();
        String path = getClass().getResource(".").getFile();
        File file = new File(path, "wind.stl");
        writer.write(file, out);
        assertTrue(file.exists());
        
        Mesh3D exported = modelReader.read(file.getPath());
        assertNotNull(exported);
    }
}