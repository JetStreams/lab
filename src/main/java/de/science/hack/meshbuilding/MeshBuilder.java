/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.Line;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;
import toxi.geom.mesh.TriangleMesh;

/**
 * This builder constructs the mesh for the wind data.
 *
 * @author Mario
 */
public class MeshBuilder {

    /**
     * Creats a triangle mesh for the wind data.
     *
     * @param data as sorted map.
     * @return
     */
    public TriangleMesh build(SortedMap<Float, List<Line>> data) {

        TriangleMesh mesh = new TriangleMesh();
        if (!data.isEmpty()) {
            long start = System.currentTimeMillis();
            
            MeshBuilderTask task = new MeshBuilderTask(data);
            mesh = task.compute();

            long end = System.currentTimeMillis();
            Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("constructed mesh in %s ms", end - start));
        }
        return mesh;
    }
}
