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
        TriangleMesh earth = (TriangleMesh)read(file);
        float maxbefore = earth.getBoundingBox().getMax().x;
        return earth.getScaled((float)CoordinatesConverter.RADIUS/maxbefore);
    }

    Mesh3D read(String fileName) {
        return reader.loadBinary(fileName, STLReader.TRIANGLEMESH);
    }
}
