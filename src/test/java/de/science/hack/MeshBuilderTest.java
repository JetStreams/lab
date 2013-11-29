/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

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
    
    private MeshBuilder classUnderTest;
    
    @Before
    public void setUp() {
        classUnderTest = new MeshBuilder();
    }
    
    private SortedMap<Float,List<Line>> readData(String res) {
        WindDataReader reader = new WindDataReader();
        String name = getClass().getResource(res).getFile();
        return reader.read(name);
    }

    /**
     * Test of build method, of class MeshConverter.
     */
    @Test
    public void testBuildNorth() {
        TriangleMesh result = classUnderTest.build(readData("unit/north.txt"));
        assertNotNull(result);
        assertFalse(result.getFaces().isEmpty());
        AABB box = result.getBoundingBox();
        assertFalse(box.getMax().x == 0);
    }
    
    /**
     * Test of build method, of class MeshConverter.
     */
    @Test
    public void testBuildSouth() {
        TriangleMesh result = classUnderTest.build(readData("unit/south.txt"));
        assertNotNull(result);
        assertFalse(result.getFaces().isEmpty());
        AABB box = result.getBoundingBox();
        assertFalse(box.getMax().x == 0);
    }
}