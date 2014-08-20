/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.model.Line;
import static de.science.hack.meshbuilding.AbstractFaceBuilderTask.ZERO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import toxi.geom.Vec3D;

/**
 * Creates faces along the longitudes.
 *
 * @author Mario
 */
class LongitudeFaceBuilderTask extends AbstractFaceBuilderTask {

    @Override
    protected List<Vec3D[]> doWork(List<Line>... workUnits) {
        return addFaces(workUnits[ZERO]);
    }

    /**
     * Adds faces along the longitudes.
     *
     * @param lines
     * @param mesh
     */
    private List<Vec3D[]> addFaces(List<Line> lines) {
        List<Vec3D[]> faces = new ArrayList<>();

        for (int i = 0, m = lines.size(); i < m; i++) {
            //contains two projection or the four corners of a rectangle which is used to construct two triangles
            LinkedList<Line> tuple = nextTuple(lines, i);
            faces.addAll(createTriangles(tuple));
        }

        return faces;
    }

    //the next two projections
    private LinkedList<Line> nextTuple(List<Line> projections, int current) {
        LinkedList<Line> tuple = new LinkedList<>();
        if (current < projections.size() - 1) {
            tuple.add(projections.get(current));
            tuple.add(projections.get(current + 1));
        }
        return tuple;
    }
}
