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
import toxi.geom.mesh.TriangleMesh;

/**
 * Creates triangles along the latitudes.
 *
 * @author Mario
 */
class LatitudeFaceBuilderTask extends AbstractFaceBuilderTask {

    LatitudeFaceBuilderTask(TriangleMesh mesh) {
        super(mesh);
    }
    
    protected void compute() {
        addFaces(workUnits[0], workUnits[1]);
    }

    /**
     * Creates triangles along the latitudes. It assumes that both lists have
     * the same length
     *
     * @param mesh
     * @param previousProjections
     * @param projections
     */
    void addFaces(List<Line> previousProjections, List<Line> projections) {

        Iterator<Line> itPrevious = previousProjections.iterator();
        Iterator<Line> itCurrent = projections.iterator();
        while (itPrevious.hasNext() && itCurrent.hasNext()) {
            Line projPrev = itPrevious.next();
            Line projCurrent = itCurrent.next();
            LinkedList<Line> tuple = new LinkedList<>();
            tuple.add(projPrev);
            tuple.add(projCurrent);
            addFaces(tuple);
        }
    }
}
