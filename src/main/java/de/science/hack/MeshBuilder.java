/*
 * 
 */
package de.science.hack;

import java.util.ArrayList;
import java.util.List;
import toxi.geom.Triangle3D;
import toxi.geom.Vec3D;

/**
 *
 * @author Mario
 */
public class MeshBuilder {

    private Triangle3D createTriangle(StlPoint coord1, StlPoint coord2, StlPoint coord3) {
        return new Triangle3D(toVec(coord1), toVec(coord2), toVec(coord3));
    }

    private StlPoint[] getNextQuarter(List<StlPoint> stlCoordinates, int current) {
        StlPoint[] tripple = new StlPoint[4];
        for (int i = current; i < stlCoordinates.size() && i < current + 4; i++) {
            tripple[i - current] = stlCoordinates.get(i);
        }
        return tripple;
    }

    private Vec3D toVec(StlPoint coord) {
        return new Vec3D((float) coord.getX(), (float) coord.getY(), (float) coord.getZ());
    }
    

    public List<Triangle3D> build(List<StlPoint> stlCoordinates) {
        List<Triangle3D> triangles = new ArrayList<>();
        
        for (int i = 0; i < stlCoordinates.size() - 1;) {
            //get an array of 4 coordinates
            StlPoint[] quarter = getNextQuarter(stlCoordinates, i);
            triangles.add(createTriangle(quarter[0], quarter[1], quarter[2]));
            triangles.add(createTriangle(quarter[1], quarter[2], quarter[3]));
            i = i + 3;
        }
        return triangles;
    }
}
