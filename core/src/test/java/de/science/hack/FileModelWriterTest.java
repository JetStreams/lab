/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import toxi.geom.mesh.Mesh3D;
import toxi.geom.mesh.TriangleMesh;

/**
 *
 * @author Mario
 */
public class FileModelWriterTest {
    
    private FileModelWriter classUnderTest;
    
    private MeshReader reader;
    
    private Mesh3D source;
    
    private File resultFile;
    
    @Before
    public void setUp() {
        classUnderTest = new FileModelWriter();
        reader = new MeshReader();
        source = reader.readGlobe(GlobeType.Full);
        String path = getClass().getResource(".").getFile();
        resultFile = new File(path, "out.stl");
    }
    
    @After
    public void shutDown() {
        if(resultFile.exists()){
            resultFile.delete();
        }
    }

    /**
     * Test of write method, of class ModelWriter.
     */
    @Test
    public void testWrite() {
        
        TriangleMesh out = new TriangleMesh();
        out.addMesh(source);
        
        
        classUnderTest.write(resultFile, out);
        assertTrue(resultFile.exists());
        
        Mesh3D exported = reader.read(resultFile.getPath());
        assertNotNull(exported);
        assertEquals(source.getFaces().size(), exported.getFaces().size());
    }
}