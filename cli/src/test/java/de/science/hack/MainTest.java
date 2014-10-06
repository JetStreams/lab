/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.io.File;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toxi.geom.mesh.Mesh3D;
import toxi.geom.mesh.TriangleMesh;

import static org.junit.Assert.*;
/**
 *
 * @author Mario
 */
public class MainTest {
    
    private MeshReader reader;
    
    private File dataFile;
    
    private File resultFile;
    
    @Before
    public void setUp() {
        reader = new MeshReader();
        
        dataFile = new File(getClass().getResource("wind.txt").getFile());
        resultFile = new File(getClass().getResource(".").getPath(), "jetstreams.stl");
    }
    
    @After
    public void shutDown() {
        FileUtils.deleteQuietly(resultFile);
    }

    @Test
    public void testWriteFull() throws ParseException {
        
        TriangleMesh meshBefore = reader.readGlobe(GlobeType.Full);
        
        String [] arguments = new String[] {"-d", dataFile.getParent(), "-o", resultFile.getPath()};
        int expVertices = processAndVerify(arguments);
        
        assertFalse(expVertices == meshBefore.getNumVertices());
        assertEquals(255779, expVertices);
    }
    
    @Test
    public void testWriteWired() throws ParseException {
        
        TriangleMesh meshBefore = reader.readGlobe(GlobeType.Wire);
        
        String [] arguments = new String[] {"-d", dataFile.getParent(), "-o", resultFile.getPath(), "-t", "w"};
        int expVertices = processAndVerify(arguments);
        
        assertFalse(expVertices == meshBefore.getNumVertices());
        assertEquals(290199, expVertices);
    }

    private int processAndVerify(String[] arguments) throws ParseException {
        Main.main(arguments);
        assertTrue(resultFile.exists());
        Mesh3D exported = reader.read(resultFile.getPath());
        assertNotNull(exported);
        int expVertices = exported.getNumVertices();
        return expVertices;
    }

}
