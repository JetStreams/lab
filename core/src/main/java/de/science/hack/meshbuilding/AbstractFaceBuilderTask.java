/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.Line;
import de.science.hack.ModelPoint;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import toxi.geom.Vec3D;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang.ArrayUtils.isEmpty;

/**
 * Parent task to create faces for the mesh.
 * @author Mario
 */
abstract class AbstractFaceBuilderTask extends RecursiveTask<List<Vec3D[]>> {
    protected static final int ZERO = 0;

    private List<Line>[] workUnits;

    void setWorkUnits(List<Line>... workUnits) {
        this.workUnits = workUnits;
    }
    
    @Override
    protected List<Vec3D[]> compute() {
        List<Vec3D[]> faces = emptyList();
        if(!isEmpty(workUnits)){
            faces = doWork(workUnits);
        }
        return faces;
    }
    
    protected abstract List<Vec3D[]> doWork(List<Line>... workUnits);

    protected Vec3D[] createTriangle(ModelPoint point1, ModelPoint point2, ModelPoint point3) {
        return new Vec3D[]{toVec(point1), toVec(point2), toVec(point3)};
    }

    protected Vec3D toVec(ModelPoint point) {
        return new Vec3D((float) point.getX(), (float) point.getY(), (float) point.getZ());
    }

    /**
     * creates two triangle based on the two projections
     *
     * @param projections
     * @return
     */
    protected List<Vec3D[]> createTriangles(LinkedList<Line> projections) {
        List<Vec3D[]> triangles = new ArrayList<>(2);
        if (!projections.isEmpty()) {
            Line first = projections.getFirst();
            Line last = projections.getLast();
            triangles.add(createTriangle(first.getPoint1(), first.getPoint2(), last.getPoint1()));
            triangles.add(createTriangle(first.getPoint2(), last.getPoint1(), last.getPoint2()));
        }
        return triangles;
    }
}
