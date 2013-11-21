/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.net.URL;
import toxi.geom.mesh.*;

/**
 * Reads the mesh from a file.
 * @author Mario
 */
public class ModelReader {
    
    private STLReader reader = new STLReader();
    
    /**
     * This method reads the globe model.
     * @return 
     */
    public TriangleMesh readEarth() {
        URL resource = getClass().getResource("earth.stl");
        String file = resource.getFile();
        return (TriangleMesh)read(file);
    }

    Mesh3D read(String fileName) {
        return reader.loadBinary(fileName, STLReader.TRIANGLEMESH);
    }
}
