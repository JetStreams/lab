/*
 * 
 */
package de.science.hack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import toxi.geom.*;
import toxi.geom.mesh.*;

/**
 *
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
