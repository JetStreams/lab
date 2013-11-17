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
    
    /**
     * Reads data from the given input file and writes it together with the globe to the
     * given output file.
     * 
     * @param inputFile path to the input file
     * @param outputFile path to the output file
     */
    public void write(String inputFile, String outputFile){
        
        TriangleMesh earth = modelReader.readEarth();
        TriangleMesh wind = builder.build(windDataReader.read(inputFile));
        
        float facEarth = earth.getBoundingBox().getMax().x;
        float facWind = wind.getBoundingBox().getMax().x;
        
        wind = wind.getScaled((float)(facEarth/facWind));
        earth.addMesh(wind);
        
        File file = new File(outputFile);
        writer.write(file, earth);
    }
}
