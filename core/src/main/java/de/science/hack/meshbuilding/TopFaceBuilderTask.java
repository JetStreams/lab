/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.model.Line;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import toxi.geom.Vec3D;

/**
 * Creates triangles on top.
 *
 * @author Mario
 */
class TopFaceBuilderTask extends LatitudeFaceBuilderTask {

    /**
     * Creates triangles on top. We assume that both lists have the same length.
     *
     * @param mesh
     * @param previousProjections
     * @param projections
     */
    @Override
    List<Vec3D[]> addFaces(List<Line> previousProjections, List<Line> projections) {
        
        List<Vec3D[]> faces = new ArrayList<>();

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
            faces.addAll(createTriangles(tuple));
        }
        
        return faces;
    }
}
