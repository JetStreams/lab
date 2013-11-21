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
    
    private SortedMap<Float,List<PointProjection>> data;
    
    private MeshBuilder classUnderTest;
    
    
    @Before
    public void setUp() {
        WindDataReader reader = new WindDataReader();
        String name = getClass().getResource("unit/short.txt").getFile();
        data = reader.read(name);
        classUnderTest = new MeshBuilder();
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
    }
}