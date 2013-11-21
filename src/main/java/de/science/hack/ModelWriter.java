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
import java.util.logging.Level;
import java.util.logging.Logger;
import toxi.geom.mesh.TriangleMesh;

/**
 * Writes the mesh to a file.
 * @author Mario
 */
public class ModelWriter {

    public void write(File file, TriangleMesh mesh) {
        OutputStream stream;
        try {
            stream = new FileOutputStream(file);
            mesh.saveAsSTL(stream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
