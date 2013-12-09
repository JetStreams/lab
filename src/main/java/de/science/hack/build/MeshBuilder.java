/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.build;

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
    
    private LatitudeFaceBuilder latitudeFaceBuilder;
    private LongitudeFaceBuilder longitudeFaceBuilder;
    private TopFaceBuilder topFaceBuilder;


    public MeshBuilder() {
        latitudeFaceBuilder = new LatitudeFaceBuilder();
        longitudeFaceBuilder = new LongitudeFaceBuilder();
        topFaceBuilder = new TopFaceBuilder();
    }

    /**
     * Creats a triangle mesh for the wind data.
     *
     * @param data as sorted map.
     * @return
     */
    public TriangleMesh build(SortedMap<Float, List<Line>> data) {

        TriangleMesh mesh = new TriangleMesh();
        if (!data.isEmpty()) {
            //at least the size of the wind data
            List<Line> previousLines = null;
            for (Entry<Float, List<Line>> entry : data.entrySet()) {
                List<Line> currentLines = entry.getValue();

                longitudeFaceBuilder.build(mesh, currentLines);

                if (previousLines != null) {
                    latitudeFaceBuilder.build(mesh, previousLines, currentLines);
                    topFaceBuilder.build(mesh, previousLines, currentLines);
                }
                previousLines = currentLines;
            }
            
            //close mesh between first and last
            Float firstLon = data.firstKey();
            Float lastLon = data.lastKey();
            if (firstLon != lastLon) {
                latitudeFaceBuilder.build(mesh, data.get(firstLon), data.get(lastLon));
                topFaceBuilder.build(mesh, data.get(firstLon), data.get(lastLon));
            }
        }
        return mesh;
    }
}
