/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.Line;
import static java.lang.Math.abs;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import toxi.geom.mesh.TriangleMesh;

/**
 * This Task constructs the mesh for one set of wind data. It splits the
 * construction into other sub tasks.
 *
 * @author Mario
 */
class MeshBuilderTask{

    /**
     * the maximum gap btween two longitudes
     */
    private static int MAX_GAP = 2;
    
    private SortedMap<Float, List<Line>> data;
    private LongitudeFaceBuilderTask longitudeFaceBuilder;
    private LatitudeFaceBuilderTask latitudeFaceBuilder;
    private TopFaceBuilderTask topFaceBuilder;
    private TriangleMesh mesh;
    
    MeshBuilderTask(SortedMap<Float, List<Line>> data) {
        this.data = data;
        
        mesh = new TriangleMesh();
        
        longitudeFaceBuilder = new LongitudeFaceBuilderTask(mesh);
        topFaceBuilder = new TopFaceBuilderTask(mesh);
        latitudeFaceBuilder = new LatitudeFaceBuilderTask(mesh);
    }

    TriangleMesh compute() {


        List<Line> previousLines = null;
        for (Map.Entry<Float, List<Line>> entry : data.entrySet()) {
            List<Line> currentLines = entry.getValue();

            longitudeFaceBuilder.setWorkUnits(currentLines);
            longitudeFaceBuilder.compute();

            if (previousLines != null) {
                latitudeFaceBuilder.setWorkUnits(previousLines, currentLines);
                topFaceBuilder.setWorkUnits(previousLines, currentLines);

                latitudeFaceBuilder.compute();
                topFaceBuilder.compute();
            }

            previousLines = currentLines;
        }
        
        closeMesh();

        return mesh;
    }

    /**
     * close mesh between first and last when the gap is not more then max
     *
     */
    private void closeMesh() {

        Float firstLon = data.firstKey();
        Float lastLon = data.lastKey();
        float gap = abs(lastLon - firstLon);
        if (gap > 0 && gap <= MAX_GAP) {
            List<Line> firstLine = data.get(firstLon);
            List<Line> lastLine = data.get(lastLon);
            latitudeFaceBuilder.setWorkUnits(firstLine, lastLine);
            topFaceBuilder.setWorkUnits(firstLine, lastLine);
            latitudeFaceBuilder.compute();
            topFaceBuilder.compute();
        }
    }
}
