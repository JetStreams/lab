/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import toxi.geom.Vec3D;
import toxi.geom.mesh.Face;
import toxi.geom.mesh.Mesh3D;
import toxi.geom.mesh.TriangleMesh;

public class ModelManipulationTest {

    private ModelReader reader;
    private ModelWriter writer;

    @Before
    public void setUp() {
        reader = new ModelReader();
        writer = new ModelWriter();
    }

    @Test
    public void testAddAFewWindCoordinates() {
        Mesh3D mesh = reader.readEarth();
        Collection<Face> allFaces = mesh.getFaces();
        int oldCount = allFaces.size();

        TriangleMesh newMesh = new TriangleMesh();
        newMesh.addMesh(mesh);
        //TriangleMesh offers easier access to the faces, 
        //unfortunately it is also significantly slower when adding faces

        for (int i = 0; i < newMesh.faces.size(); i++) {
            Face face = newMesh.faces.get(i);

            Vec3D cloudPoint = face.getCentroid();
            cloudPoint.x += 7;
            cloudPoint.y += 7;
            cloudPoint.z += 7;
            mesh.addFace(cloudPoint, face.a, face.b);
            mesh.addFace(cloudPoint, face.b, face.c);
            mesh.addFace(cloudPoint, face.c, face.a);
        }

        mesh.computeFaceNormals();
        int newCount = mesh.getNumFaces();
        assertFalse(oldCount == newCount);

        String path = getClass().getResource(".").getFile();
        File file = new File(path, "manipulated.stl");
        TriangleMesh meshforWriting = new TriangleMesh();
        meshforWriting.addMesh(mesh);
        writer.write(file, meshforWriting);
    }
}
