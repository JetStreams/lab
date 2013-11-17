/*
 * 
 */
package de.science.hack;

import java.net.URL;
import toxi.geom.mesh.*;
/**
 *
 * @author Mario
 */
public class ModelReader {
    
    private STLReader reader = new STLReader();
    
    public TriangleMesh readEarth() {
        URL resource = getClass().getResource("earth.stl");
        String file = resource.getFile();
        return (TriangleMesh)read(file);
    }

    Mesh3D read(String fileName) {
        return reader.loadBinary(fileName, STLReader.TRIANGLEMESH);
    }
}
