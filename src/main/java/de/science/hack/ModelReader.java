/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.io.InputStream;
import java.net.URL;
import toxi.geom.mesh.*;

/**
 * Reads the mesh from a file.
 * @author Mario
 */
public class ModelReader {
    private static final String EARTH = "earth.stl";
    
    private STLReader reader = new STLReader();
    
    /**
     * This method reads the globe model.
     * @return 
     */
    public TriangleMesh readEarth() {
        InputStream stream = getClass().getResourceAsStream(EARTH);
        return (TriangleMesh)reader.loadBinary(stream, EARTH, STLReader.TRIANGLEMESH);
    }

    Mesh3D read(String fileName) {
        return reader.loadBinary(fileName, STLReader.TRIANGLEMESH);
    }
}
