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

import org.junit.Ignore;

import toxi.geom.Triangle3D;
import toxi.geom.mesh.Mesh3D;
import toxi.geom.mesh.TriangleMesh;

/**
 *
 * @author Mario
 */
public class TriangleBuilderTest {
    
    private SortedMap<Float,List<PointProjection>> data;
    
    private TriangleBuilder classUnderTest;
    
    private ModelWriter writer;
    
    @Before
    public void setUp() {
        WindDataReader reader = new WindDataReader();
        String name = getClass().getResource("wind.txt").getFile();
        data = reader.read(name);
        classUnderTest = new TriangleBuilder();
        
        writer = new ModelWriter();
    }

    /**
     * Test of build method, of class MeshConverter.
     */
    @Test
    public void testBuild() {
        List<Triangle3D> result = classUnderTest.build(data);
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        //assertEquals(24, result.size());
        
        Mesh3D mesh = new TriangleMesh();
        for(Triangle3D triangle: result) {
        	mesh.addFace(triangle.a, triangle.b, triangle.c);
        }
        
        mesh.computeFaceNormals();

        String path = getClass().getResource(".").getFile();
        File outputFile = new File(path, "wind-triangles.stl");
        TriangleMesh meshforWriting = new TriangleMesh();
        meshforWriting.addMesh(mesh);
        writer.write(outputFile, meshforWriting);
    }
}