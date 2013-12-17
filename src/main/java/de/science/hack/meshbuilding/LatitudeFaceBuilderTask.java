/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.Line;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import toxi.geom.mesh.TriangleMesh;

import static java.lang.Math.abs;

/**
 * Creates triangles along the latitudes.
 *
 * @author Mario
 */
class LatitudeFaceBuilderTask extends AbstractFaceBuilderTask {
    /**the maximum gap btween two longitudes*/
    private static int MAX_GAP = 1;

    private SortedMap<Float, List<Line>> data;

    LatitudeFaceBuilderTask(SortedMap<Float, List<Line>> data) {
        this.data = data;
    }

    @Override
    protected TriangleMesh compute() {
        TriangleMesh mesh = new TriangleMesh();

        Float firstLon = data.firstKey();
        Float lastLon = data.lastKey();
        float gap = abs(lastLon - firstLon);
        
        List<Line> previousLines = null;
        for (Map.Entry<Float, List<Line>> entry : data.entrySet()) {
            Float lon = entry.getKey();
            List<Line> currentLines = entry.getValue();

            if (previousLines != null) {
                addFaces(mesh, previousLines, currentLines);
            }
            previousLines = currentLines;
            
            //close mesh between first and last when the gap is not more then maximum
            if(gap <= MAX_GAP && lon.equals(lastLon)){
                addFaces(mesh, data.get(firstLon), data.get(lastLon));
            }
        }

        return mesh;
    }

    /**
     * Creates triangles along the latitudes. It assumes that both lists have
     * the same length
     *
     * @param mesh
     * @param previousProjections
     * @param projections
     */
    void addFaces(TriangleMesh mesh, List<Line> previousProjections, List<Line> projections) {

        Iterator<Line> itPrevious = previousProjections.iterator();
        Iterator<Line> itCurrent = projections.iterator();
        while (itPrevious.hasNext() && itCurrent.hasNext()) {
            Line projPrev = itPrevious.next();
            Line projCurrent = itCurrent.next();
            LinkedList<Line> tuple = new LinkedList<>();
            tuple.add(projPrev);
            tuple.add(projCurrent);
            addFaces(mesh, tuple);
        }
    }
}
