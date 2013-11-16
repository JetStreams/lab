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
    
    public Mesh3D readEarth() {
        URL resource = getClass().getResource("earth.stl");
        String file = resource.getFile();
        return read(file);
    }

    private Mesh3D read(String fileName) {
        return reader.loadBinary(fileName, STLReader.TRIANGLEMESH);
    }
}
