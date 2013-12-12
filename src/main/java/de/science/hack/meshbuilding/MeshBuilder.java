/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.Line;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
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
            LongitudeFaceBuilder longitudeFaceBuilder = new LongitudeFaceBuilder(data);
            LatitudeFaceBuilder latitudeFaceBuilder = new LatitudeFaceBuilder(data);
            TopFaceBuilder topFaceBuilder = new TopFaceBuilder(data);
            
            mesh.addMesh(longitudeFaceBuilder.build());
            mesh.addMesh(latitudeFaceBuilder.build());
            mesh.addMesh(topFaceBuilder.build());
        }
        return mesh;
    }
}
