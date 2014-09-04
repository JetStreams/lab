/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.meshbuilding;

import de.science.hack.model.Line;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.ForkJoinPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import toxi.geom.Vec3D;
import toxi.geom.mesh.TriangleMesh;
import toxi.geom.mesh.WETriangleMesh;

/**
 * This builder constructs the mesh for the wind data.
 *
 * @author Mario
 */
public class MeshBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(MeshBuilder.class);

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THRIRD = 2;
    private final ForkJoinPool pool;

    public MeshBuilder() {
        pool = new ForkJoinPool();
    }

    /**
     * Creats a triangle mesh for the wind data.
     *
     * @param data as sorted map.
     * @return
     */
    public TriangleMesh build(SortedMap<Float, List<Line>> data) {

        TriangleMesh mesh = new WETriangleMesh();
        if (!data.isEmpty()) {
            long start = System.currentTimeMillis();

            addFaces(mesh, pool.invoke(new FacesBuilderTask(data)));

            long end = System.currentTimeMillis();
            LOG.info("constructed mesh in {} ms", end - start);
        }
        return mesh;
    }

    private void addFaces(TriangleMesh mesh, List<Vec3D[]> faces) {
        faces.stream().forEach((face) -> {
            mesh.addFace(face[FIRST], face[SECOND], face[THRIRD]);
        });
    }
}
