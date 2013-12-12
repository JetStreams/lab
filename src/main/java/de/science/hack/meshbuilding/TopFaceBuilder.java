/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.Line;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import toxi.geom.mesh.TriangleMesh;

/**
 * Add triangles on top.
 *
 * @author Mario
 */
class TopFaceBuilder extends AbstractFaceBuilder {

    private SortedMap<Float, List<Line>> data;

    TopFaceBuilder(SortedMap<Float, List<Line>> data) {
        this.data = data;
    }
    
    TriangleMesh build() {
        TriangleMesh mesh = new TriangleMesh();

        Float firstLon = data.firstKey();
        Float lastLon = data.lastKey();
        List<Line> previousLines = null;
        for (Map.Entry<Float, List<Line>> entry : data.entrySet()) {
            Float lon = entry.getKey();
            List<Line> currentLines = entry.getValue();

            if (previousLines != null) {
                addFaces(mesh, previousLines, currentLines);
            }
            previousLines = currentLines;
            
            //close mesh between first and last
            if(lon.equals(lastLon)){
                addFaces(mesh, data.get(firstLon), data.get(lastLon));
            }
            
        }

        return mesh;
    }

    /**
     * Add triangles on top. It assumes that both lists have the same length
     *
     * @param mesh
     * @param previousProjections
     * @param projections
     */
    private void addFaces(TriangleMesh mesh, List<Line> previousProjections, List<Line> projections) {

        for (int i = 0, m = projections.size() - 1; i < m;) {

            Line first = previousProjections.get(i);
            Line second = projections.get(i);
            i++;
            Line third = previousProjections.get(i);
            Line fourth = projections.get(i);

            LinkedList<Line> tuple = new LinkedList<>();
            //ues only the the upper points, since we want to create more or less horizontal triangles
            tuple.add(new Line(first.getPoint2(), second.getPoint2()));
            tuple.add(new Line(third.getPoint2(), fourth.getPoint2()));
            addFaces(mesh, tuple);
        }
    }
}
