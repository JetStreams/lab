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
import toxi.geom.mesh.TriangleMesh;

/**
 * Add triangles on top.
 * @author Mario
 */
class TopFaceBuilder extends AbstractFaceBuilder{
   
    /**
     * Add triangles on top. It assumes that both lists have the same length
     *
     * @param mesh
     * @param previousProjections
     * @param projections
     */
    @Deprecated
    void build(TriangleMesh mesh, List<Line> previousProjections, List<Line> projections) {

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
