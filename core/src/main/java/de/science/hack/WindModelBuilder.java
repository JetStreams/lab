/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import de.science.hack.meshbuilding.MeshBuilder;
import java.io.File;
import toxi.geom.mesh.TriangleMesh;

/**
 * Simple class which reads the wind data from an input file and builds the mesh.
 * @author Mario
 */
public class WindModelBuilder {
    
    private WindDataReader dataReader;
    private MeshBuilder meshBuilder;
    
    public WindModelBuilder() {
        dataReader = new WindDataReader();
        meshBuilder = new MeshBuilder();
    }
    
    public TriangleMesh build(File inputFile){
        return build(inputFile.getPath());
    }
    
    public TriangleMesh build(String inputFile){
        return meshBuilder.build(dataReader.read(inputFile));
    }
}
