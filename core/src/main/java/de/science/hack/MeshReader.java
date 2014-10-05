/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import de.science.hack.model.WGS84;
import java.io.InputStream;
import toxi.geom.mesh.*;

/**
 * Reads the mesh from a file.
 *
 * @author Mario
 */
public class MeshReader {

    /**
     * reduces the scaling factor so that the jet stream on the model don't
     * dissapear
     */
    private static final int REDUCE = 1000;

    private final STLReader reader = new STLReader();

    /**
     * This method reads the globe model.
     *
     * @param type the {@link GlobeType} to use.
     * @return a {@link TriangleMesh}
     */
    public TriangleMesh readGlobe(GlobeType type) {
        String modelName = type.getModelName();
        InputStream stream = getClass().getResourceAsStream(modelName);
        TriangleMesh earth = (TriangleMesh) reader.loadBinary(stream, modelName, STLReader.TRIANGLEMESH);
        float facEarth = earth.getBoundingBox().getMax().x;
        float scaling = (float) WGS84.RADIUS.getValue() / facEarth;
        return earth.getScaled(scaling - REDUCE);
    }

    /**
     * Reads a method using the given file name.
     * @param fileName
     * @return 
     */
    Mesh3D read(String fileName) {
        return reader.loadBinary(fileName, STLReader.TRIANGLEMESH);
    }
}
