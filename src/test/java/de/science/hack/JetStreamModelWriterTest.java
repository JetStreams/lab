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
