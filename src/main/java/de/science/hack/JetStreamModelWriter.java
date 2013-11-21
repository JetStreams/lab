/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import toxi.geom.mesh.TriangleMesh;

/**
 * Creates the model of the globe with the wind data.
 *
 * @author Mario
 */
public class JetStreamModelWriter {

    private ModelReader modelReader;
    private ModelWriter writer;
    private List<TriangleMesh> windModels;

    public JetStreamModelWriter() {
        windModels = new ArrayList<>();
        modelReader = new ModelReader();
        writer = new ModelWriter();
    }

    public void addWindModel(TriangleMesh model) {
        windModels.add(model);
    }

    /**
     * Reads data from the given input file and writes it together with the
     * globe to the given output file.
     *
     * @param outputFile path to the output file
     */
    public void write(String outputFile) {

        TriangleMesh earth = modelReader.readEarth();
        float facEarth = earth.getBoundingBox().getMax().x;
        
        for (TriangleMesh wind : windModels) {
            float facWind = wind.getBoundingBox().getMax().x;
            wind = wind.getScaled((float) (facEarth / facWind));
            earth.addMesh(wind);
        }

        File file = new File(outputFile);
        writer.write(file, earth);
    }
}
