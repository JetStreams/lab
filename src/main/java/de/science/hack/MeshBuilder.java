/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import toxi.geom.Vec3D;
import toxi.geom.mesh.TriangleMesh;

/**
 * This builder constructs the mesh for the wind data.
 * @author Mario
 */
public class MeshBuilder {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THRIRD = 2;

    private Vec3D toVec(ModellPoint point) {
        return new Vec3D((float) point.getX(), (float) point.getY(), (float) point.getZ());
    }

    private Vec3D[] createTriangle(ModellPoint point1, ModellPoint point2, ModellPoint point3) {
        return new Vec3D[]{toVec(point1), toVec(point2), toVec(point3)};
    }

    /**
     * creates two triangle based on the two projections
     * @param projections
     * @return 
     */
    private List<Vec3D[]> creatTriangles(LinkedList<PointProjection> projections) {
        List<Vec3D[]> triangles = new ArrayList<>(2);

        if (!projections.isEmpty()) {
            PointProjection first = projections.getFirst();
            PointProjection last = projections.getLast();

            triangles.add(createTriangle(first.getGroundPoint(), first.getDataPoint(), last.getGroundPoint()));
            triangles.add(createTriangle(first.getDataPoint(), last.getGroundPoint(), last.getDataPoint()));
        }
        return triangles;
    }

    //the next two projections
    private LinkedList<PointProjection> nextTuple(List<PointProjection> projections, int current) {
        LinkedList<PointProjection> tuple = new LinkedList<>();
        if (current < projections.size() - 1) {
            tuple.add(projections.get(current));
            tuple.add(projections.get(current + 1));
        }
        return tuple;
    }

    /**
     * Creats a triangle mesh for the wind data.
     * @param data as sorted map.
     * @return 
     */
    public TriangleMesh build(SortedMap<Float, List<PointProjection>> data) {
        
        TriangleMesh mesh = new TriangleMesh();
        //at least the size of the wind data
        for (Entry<Float, List<PointProjection>> entry : data.entrySet()) {
            List<PointProjection> projections = entry.getValue();
            for (int i = 0, m = projections.size(); i < m; i++) {
                //contains two projection or the four corners of a rectangle which is used to construct two triangles
                LinkedList<PointProjection> tuple = nextTuple(projections, i);
                List<Vec3D[]> faces = creatTriangles(tuple);
                for (Vec3D[] face : faces) {
                    //add a face, which is a triangle
                    mesh.addFace(face[FIRST], face[SECOND], face[THRIRD]);
                }
            }
        }
        return mesh;
    }
}
