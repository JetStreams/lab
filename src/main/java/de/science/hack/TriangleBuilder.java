/*
 * 
 */
package de.science.hack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import toxi.geom.Triangle3D;
import toxi.geom.Vec3D;

/**
 *
 * @author Mario
 */
public class TriangleBuilder {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THRIRD = 2;

    private Vec3D toVec(ModellPoint point) {
        return new Vec3D((float) point.getX(), (float) point.getY(), (float) point.getZ());
    }

    private Triangle3D createTriangle(ModellPoint point1, ModellPoint point2, ModellPoint point3) {
        return new Triangle3D(toVec(point1), toVec(point2), toVec(point3));
    }

    private List<Triangle3D> creatTriangles(LinkedList<PointProjection> projections) {
        List<Triangle3D> triangles = new ArrayList<>(2);

        if (!projections.isEmpty()) {
            PointProjection first = projections.getFirst();
            PointProjection last = projections.getLast();

            triangles.add(createTriangle(first.getGroundPoint(), first.getDataPoint(), last.getGroundPoint()));
            triangles.add(createTriangle(first.getDataPoint(), last.getGroundPoint(), last.getDataPoint()));
        }
        return triangles;
    }

    private LinkedList<PointProjection> nextTuple(List<PointProjection> projections, int current) {
        LinkedList<PointProjection> tuple = new LinkedList<>();
        if (current < projections.size() - 1) {
            tuple.add(projections.get(current));
            tuple.add(projections.get(current + 1));
        }
        return tuple;
    }

    public List<Triangle3D> build(SortedMap<Float, List<PointProjection>> data) {
        //at least the size of the wind data
        List<Triangle3D> triangles = new ArrayList<>(data.size());
        for (Entry<Float, List<PointProjection>> entry : data.entrySet()) {
            List<PointProjection> projections = entry.getValue();
            for (int i = 0, m = projections.size(); i < m; i++) {
                LinkedList<PointProjection> tuple = nextTuple(projections, i);
                triangles.addAll(creatTriangles(tuple));
            }
        }
        return triangles;
    }
}
