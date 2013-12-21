/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.RecursiveTask;
import toxi.geom.Vec3D;

/**
 * This Task constructs the mesh for one set of wind data. It splits the
 * construction into other sub tasks.
 *
 * @author Mario
 */
class FacesBuilderTask extends RecursiveTask<List<Vec3D[]>> {
    
    /**
     * the maximum gap btween two longitudes
     */
    private static int MAX_GAP = 2;
    private static int MAX_LON = 360;
    
    private SortedMap<Float, List<Line>> data;
    private LongitudeFaceBuilderTask longitudeFaceBuilder;
    private LatitudeFaceBuilderTask latitudeFaceBuilder;
    private TopFaceBuilderTask topFaceBuilder;
    private List<Vec3D[]> faces;

    FacesBuilderTask(SortedMap<Float, List<Line>> data) {
        this.data = data;

        faces = new ArrayList<>();

        longitudeFaceBuilder = new LongitudeFaceBuilderTask();
        topFaceBuilder = new TopFaceBuilderTask();
        latitudeFaceBuilder = new LatitudeFaceBuilderTask();
    }

    @Override
    protected List<Vec3D[]> compute() {


        List<Line> previousLines = null;
        for (Map.Entry<Float, List<Line>> entry : data.entrySet()) {
            List<Line> currentLines = entry.getValue();

            longitudeFaceBuilder.setWorkUnits(currentLines);

            if (previousLines != null) {
                latitudeFaceBuilder.setWorkUnits(previousLines, currentLines);
                topFaceBuilder.setWorkUnits(previousLines, currentLines);
                
                longitudeFaceBuilder.fork();
                addFaces(latitudeFaceBuilder.compute());
                addFaces(topFaceBuilder.compute());
                addFaces(longitudeFaceBuilder.join());
            }else {
                addFaces(longitudeFaceBuilder.compute());
            }

            previousLines = currentLines;
        }

        closeMesh();

        return faces;
    }

    /**
     * close mesh between first and last when the gap is not more then max
     *
     */
    private void closeMesh() {

        Float firstLon = data.firstKey();
        Float lastLon = data.lastKey();
        
        float endGap = MAX_LON - lastLon;
        float gap = firstLon + endGap;
        
        if (gap > 0 && gap <= MAX_GAP) {
            List<Line> firstLine = data.get(firstLon);
            List<Line> lastLine = data.get(lastLon);
            latitudeFaceBuilder.setWorkUnits(firstLine, lastLine);
            topFaceBuilder.setWorkUnits(firstLine, lastLine);

            latitudeFaceBuilder.fork();
            addFaces(topFaceBuilder.compute());
            addFaces(latitudeFaceBuilder.join());
            
        }
    }

    private void addFaces(List<Vec3D[]> faces) {
        this.faces.addAll(faces);
    }
}
