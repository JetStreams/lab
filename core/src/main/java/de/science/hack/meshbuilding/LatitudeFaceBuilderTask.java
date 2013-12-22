/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.Line;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import toxi.geom.Vec3D;

/**
 * Creates triangles along the latitudes.
 *
 * @author Mario
 */
class LatitudeFaceBuilderTask extends AbstractFaceBuilderTask {
    private static final int FIRST = 0;

    @Override
    protected List<Vec3D[]> compute() {
        return addFaces(workUnits[FIRST], workUnits[1]);
    }

    /**
     * Creates triangles along the latitudes. It assumes that both lists have
     * the same length
     *
     * @param mesh
     * @param previousProjections
     * @param projections
     */
    List<Vec3D[]> addFaces(List<Line> previousProjections, List<Line> projections) {

        List<Vec3D[]> faces = new ArrayList<>();
        
        int last = projections.size()-1;
        int indices [] = new int[]{FIRST, last};
        for(int i = FIRST; i < indices.length; i++){
            int index = indices[i];
            Line projPrev = previousProjections.get(index);
            Line projCurrent = projections.get(index);
            LinkedList<Line> tuple = createTuple(projPrev, projCurrent);
            faces.addAll(createTriangles(tuple));
        }
        
        return faces;
    }

    private LinkedList<Line> createTuple(Line projPrev, Line projCurrent) {
        LinkedList<Line> tuple = new LinkedList<>();
        tuple.add(projPrev);
        tuple.add(projCurrent);
        return tuple;
    }
}
