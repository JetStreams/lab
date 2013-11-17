/*
 * 
 */
package de.science.hack;

import java.io.File;
import toxi.geom.mesh.TriangleMesh;

/**
 * Creates the model of the globe with the wind data. 
 * @author Mario
 */
public class JetStreamModelWriter {
    
    private WindDataReader windDataReader;
    private ModelReader modelReader;
    private MeshBuilder builder;
    private ModelWriter writer;
    
    public JetStreamModelWriter(){
        windDataReader = new WindDataReader();
        modelReader = new ModelReader();
        builder = new MeshBuilder();
        writer = new ModelWriter();
    }
    
    
    public void write(String inputFileName, String outputFileName){
        
        TriangleMesh earth = modelReader.readEarth();
        TriangleMesh wind = builder.build(windDataReader.read(inputFileName));
        
        float facEarth = earth.getBoundingBox().getMax().x;
        float facWind = wind.getBoundingBox().getMax().x;
        
        wind = wind.getScaled((float)(facEarth/facWind));
        earth.addMesh(wind);
        
        File file = new File(outputFileName);
        writer.write(file, earth);
    }
}
