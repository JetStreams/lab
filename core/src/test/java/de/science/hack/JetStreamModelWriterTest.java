/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mario
 */
public class JetStreamModelWriterTest {
    
    private File resultFile;
    
    @Before
    public void setUp() {
        String path = getClass().getResource(".").getFile();
        resultFile = new File(path, "jet_out.stl");
    }
    
    @After
    public void shutDown() {
        FileUtils.deleteQuietly(resultFile);
    }


    /**
     * Test of write method, of class JetStreamModelWriter.
     */
    @Test
    public void testWriteFull() {
        JetStreamModelWriter classUnderTest = new JetStreamModelWriter();
        classUnderTest.write(resultFile.getPath());
        assertTrue(resultFile.exists());
    }
    
    @Test
    public void testWriteWire() {
        JetStreamModelWriter classUnderTest = new JetStreamModelWriter(GlobeType.Wire);
        classUnderTest.write(resultFile.getPath());
        assertTrue(resultFile.exists());
    }
    
}
