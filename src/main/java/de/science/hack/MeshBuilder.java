/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import toxi.geom.Vec3D;
import toxi.geom.mesh.TriangleMesh;

/**
 * This builder constructs the mesh for the wind data.
 *
 * @author Mario
 */
public class MeshBuilder {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THRIRD = 2;

    private Vec3D toVec(ModelPoint point) {
        return new Vec3D((float) point.getX(), (float) point.getY(), (float) point.getZ());
    }

    private Vec3D[] createTriangle(ModelPoint point1, ModelPoint point2, ModelPoint point3) {
        return new Vec3D[]{toVec(point1), toVec(point2), toVec(point3)};
    }

    /**
     * creates two triangle based on the two projections
     *
     * @param projections
     * @return
     */
    private List<Vec3D[]> createTriangles(LinkedList<Line> projections) {
        List<Vec3D[]> triangles = new ArrayList<>(2);

        if (!projections.isEmpty()) {
            Line first = projections.getFirst();
            Line last = projections.getLast();

            triangles.add(createTriangle(first.getPoint1(), first.getPoint2(), last.getPoint1()));
            triangles.add(createTriangle(first.getPoint2(), last.getPoint1(), last.getPoint2()));
        }
        return triangles;
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

    private void addFaces(TriangleMesh mesh, LinkedList<Line> tuple) {
        List<Vec3D[]> faces = createTriangles(tuple);
        for (Vec3D[] face : faces) {
            //add a face, which is a triangle
            mesh.addFace(face[FIRST], face[SECOND], face[THRIRD]);
        }
    }

    /**
     * Adds faces to the mesh along the longitudes
     *
     * @param projections
     * @param mesh
     */
    private void addLongitudeFaces(TriangleMesh mesh, List<Line> projections) {
        for (int i = 0, m = projections.size(); i < m; i++) {
            //contains two projection or the four corners of a rectangle which is used to construct two triangles
            LinkedList<Line> tuple = nextTuple(projections, i);
            addFaces(mesh, tuple);
        }
    }

    /**
     * Creates triangles along the latitudes. It assumes that both lists have
     * the same length
     *
     * @param mesh
     * @param previousProjections
     * @param projections
     */
    private void addLatitudeFaces(TriangleMesh mesh, List<Line> previousProjections, List<Line> projections) {

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

    /**
     * Add triangles on top. It assumes that both lists have the same length
     *
     * @param mesh
     * @param previousProjections
     * @param projections
     */
    private void addTopFaces(TriangleMesh mesh, List<Line> previousProjections, List<Line> projections) {

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

    /**
     * Creats a triangle mesh for the wind data.
     *
     * @param data as sorted map.
     * @return
     */
    public TriangleMesh build(SortedMap<Float, List<Line>> data) {

        TriangleMesh mesh = new TriangleMesh();
        if (!data.isEmpty()) {
            //at least the size of the wind data
            List<Line> previousProjections = null;
            for (Entry<Float, List<Line>> entry : data.entrySet()) {
                List<Line> currentProjections = entry.getValue();

                addLongitudeFaces(mesh, currentProjections);

                if (previousProjections != null) {
                    addLatitudeFaces(mesh, previousProjections, currentProjections);
                    addTopFaces(mesh, previousProjections, currentProjections);
                }
                previousProjections = currentProjections;
            }
            //close mesh between first and last
            Float firstLon = data.firstKey();
            Float lastLon = data.lastKey();
            if (firstLon != lastLon) {
                addLatitudeFaces(mesh, data.get(firstLon), data.get(lastLon));
                addTopFaces(mesh, data.get(firstLon), data.get(lastLon));
            }
        }
        return mesh;
    }
}
