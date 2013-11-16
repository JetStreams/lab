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

    private Triangle3D createTriangle(StlCoordinate coord1, StlCoordinate coord2, StlCoordinate coord3) {
        return new Triangle3D(toVec(coord1), toVec(coord2), toVec(coord3));
    }

    private StlCoordinate[] getNextQuarter(List<StlCoordinate> stlCoordinates, int current) {
        StlCoordinate[] tripple = new StlCoordinate[4];
        for (int i = current; i < stlCoordinates.size() && i < current + 4; i++) {
            tripple[i - current] = stlCoordinates.get(i);
        }
        return tripple;
    }

    private Vec3D toVec(StlCoordinate coord) {
        return new Vec3D((float) coord.getX(), (float) coord.getY(), (float) coord.getZ());
    }
    

    public List<Triangle3D> build(List<StlCoordinate> stlCoordinates) {
        List<Triangle3D> triangles = new ArrayList<>();
        
        for (int i = 0; i < stlCoordinates.size() - 1;) {
            //get an array of 4 coordinates
            StlCoordinate[] quarter = getNextQuarter(stlCoordinates, i);
            triangles.add(createTriangle(quarter[0], quarter[1], quarter[2]));
            triangles.add(createTriangle(quarter[1], quarter[2], quarter[3]));
            i = i + 3;
        }
        return triangles;
    }
}
