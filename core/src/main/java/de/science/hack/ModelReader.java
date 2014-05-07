/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.io.InputStream;
import toxi.geom.mesh.*;

/**
 * Reads the mesh from a file.
 * @author Mario
 */
public class ModelReader {
    
    /**
     * reduces the scaling factor so that the jet stream on the model
     * don't dissapear
     */
    private static final int REDUCE = 1000;
    
    private static final String EARTH = "earth.stl";
    
    private final STLReader reader = new STLReader();
    
    /**
     * This method reads the globe model.
     * @return 
     */
    public TriangleMesh readEarth() {
        InputStream stream = getClass().getResourceAsStream(EARTH);
        TriangleMesh earth = (TriangleMesh)reader.loadBinary(stream, EARTH, STLReader.TRIANGLEMESH);
        float facEarth = earth.getBoundingBox().getMax().x;
        float scaling = (float)CoordinatesConverter.RADIUS/facEarth;
        return earth.getScaled(scaling-REDUCE);
    }

    Mesh3D read(String fileName) {
        return reader.loadBinary(fileName, STLReader.TRIANGLEMESH);
    }
}
