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
    
    private final ModelReader modelReader;
    private final ModelWriter writer;
    private final List<TriangleMesh> windModels;

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
        
        windModels.stream().forEach(w -> earth.addMesh(w));

        File file = new File(outputFile);
        writer.write(file, earth);
    }
}
