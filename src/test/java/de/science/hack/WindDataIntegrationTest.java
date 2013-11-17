/*
 * 
 */
package de.science.hack;

import java.io.File;
import java.util.List;
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
    
    private SortedMap<Float,List<PointProjection>> windData;
    private ModelReader modelReader;
    private MeshBuilder builder;
    private ModelWriter writer;

    @Before
    public void setUp() {
        String name = getClass().getResource("wind.txt").getFile();
        WindDataReader windDataReader = new WindDataReader();
        windData = windDataReader.read(name);
        modelReader = new ModelReader();
        builder = new MeshBuilder();
        writer = new ModelWriter();
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
        TriangleMesh earth = modelReader.readEarth();
        TriangleMesh wind = builder.build(windData);
        
        float facEarth = earth.getBoundingBox().getMax().x;
        float facWind = wind.getBoundingBox().getMax().x;
        
        wind = wind.getScaled((float)(facEarth/facWind));
        earth.addMesh(wind);
       
        String path = getClass().getResource(".").getFile();
        File file = new File(path, "jetstreams.stl");
        writer.write(file, earth);
        assertTrue(file.exists());
        
        Mesh3D exported = modelReader.read(file.getPath());
        assertNotNull(exported);
    }

}
