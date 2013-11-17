/*
 * 
 */
package de.science.hack;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import static org.junit.Assert.*;
import toxi.geom.Vec2D;
import toxi.geom.Vec3D;
import toxi.geom.mesh.Face;
import toxi.geom.mesh.Mesh3D;
import toxi.geom.mesh.TriangleMesh;

/**
 *
 * @author Mario
 */
public class WindDataIntegrationTest {

    private WindDataReader windDataReader;
    private ModelWriter writer;
    private ModelReader modelReader;

    @Before
    public void setUp() {
        windDataReader = new WindDataReader();
        writer = new ModelWriter();
        modelReader = new ModelReader();
    }
    
   /*
    @Test
    public void testWindDataConversion() throws IOException {
    	 String name = getClass().getResource("wind.txt").getFile();
         List<StlCoordinate> windData = windDataReader.read(name);
         String path = getClass().getResource(".").getFile();
         File file = new File(path, "winddata-as-stlcordinates.csv");
         CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
         for(StlCoordinate coordinate: windData) {
        	 String xValue = ((Double) coordinate.getX()).toString();
        	 String yValue = ((Double) coordinate.getY()).toString();			
        	 String zValue = ((Double) coordinate.getZ()).toString();
        	 String[] line = {xValue, yValue, zValue};
        	 csvWriter.writeNext(line);
         }
         csvWriter.close();
  
    } */

    @Test
    public void testWindDataOutput() { 
   	String windFile = getClass().getResource("wind.txt").getFile();
   	List<ModellPoint> windData = windDataReader.readOld(windFile);

     Mesh3D mesh = modelReader.readEarth();
     assertNotNull(mesh);

     TriangleMesh newMesh = new TriangleMesh();
     newMesh.addMesh(mesh);
     //TriangleMesh offers easier access to the faces, 
     //unfortunately it is also significantly slower when adding faces

     for(int i = 0; i < newMesh.faces.size(); i++) {
    	 Face face = newMesh.faces.get(i);

    	 Vec3D cloudPoint = face.getCentroid();


    	 mesh.addFace(cloudPoint, face.a, face.b);
    	 mesh.addFace(cloudPoint, face.b, face.c);
    	 mesh.addFace(cloudPoint,face.c, face.a);
     }

     mesh.computeFaceNormals();

     String path = getClass().getResource(".").getFile();
     File outputFile = new File(path, "windy-earth.stl");
     TriangleMesh meshforWriting = new TriangleMesh();
     meshforWriting.addMesh(mesh);
     writer.write(outputFile, meshforWriting);
    }

}