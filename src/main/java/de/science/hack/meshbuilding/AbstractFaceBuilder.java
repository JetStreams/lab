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
import toxi.geom.Vec3D;
import toxi.geom.mesh.TriangleMesh;

/**
 *
 * @author Mario
 */
abstract class AbstractFaceBuilder {
    protected static final int FIRST = 0;
    protected static final int SECOND = 1;
    protected static final int THRIRD = 2;

    protected Vec3D[] createTriangle(ModelPoint point1, ModelPoint point2, ModelPoint point3) {
        return new Vec3D[]{toVec(point1), toVec(point2), toVec(point3)};
    }

    protected Vec3D toVec(ModelPoint point) {
        return new Vec3D((float) point.getX(), (float) point.getY(), (float) point.getZ());
    }

    protected void addFaces(TriangleMesh mesh, LinkedList<Line> tuple) {
        List<Vec3D[]> faces = createTriangles(tuple);
        for (Vec3D[] face : faces) {
            //add a face, which is a triangle
            mesh.addFace(face[FIRST], face[SECOND], face[THRIRD]);
        }
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
    
    abstract TriangleMesh build();
}
