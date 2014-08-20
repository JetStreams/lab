/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import toxi.geom.mesh.TriangleMesh;

/**
 * Writes the mesh to a file.
 * @author Mario
 */
final class FileModelWriter {
    
    private static final Logger LOG = LoggerFactory.getLogger(FileModelWriter.class);
    
    void write(String fileName, TriangleMesh mesh) {
        write(new File(fileName), mesh);
    }

    void write(File file, TriangleMesh mesh) {
        try {
            OutputStream stream = new FileOutputStream(file);
            mesh.saveAsSTL(stream);
        } catch (FileNotFoundException ex) {
            LOG.warn(ex.getMessage(), ex);
        }
    }
}
