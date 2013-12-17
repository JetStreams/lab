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
import java.util.concurrent.RecursiveTask;
import toxi.geom.mesh.TriangleMesh;

/**
 * This Task constructs the mesh for one set of wind data. It splits the 
 * construction into other sub tasks.
 * 
 * @author Mario
 */
class MeshBuilderTask extends RecursiveTask<TriangleMesh> {
    
    private SortedMap<Float, List<Line>> data;

    MeshBuilderTask(SortedMap<Float, List<Line>> data) {
        this.data = data;
    }
    
    @Override
    protected TriangleMesh compute() {

        LongitudeFaceBuilderTask longitudeFaceBuilder = new LongitudeFaceBuilderTask(data);
        LatitudeFaceBuilderTask latitudeFaceBuilder = new LatitudeFaceBuilderTask(data);
        TopFaceBuilderTask topFaceBuilder = new TopFaceBuilderTask(data);

        longitudeFaceBuilder.fork();
        latitudeFaceBuilder.fork();
        topFaceBuilder.fork();
        
        TriangleMesh mesh = new TriangleMesh();
        mesh.addMesh(longitudeFaceBuilder.compute());
        mesh.addMesh(latitudeFaceBuilder.compute());
        mesh.addMesh(topFaceBuilder.join());

        return mesh;
    }
}
