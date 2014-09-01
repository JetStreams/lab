/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.jetstream.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import toxi.geom.mesh.TriangleMesh;

/**
 * This class save the result from the previous calculation during the session.
 * 
 * @author Mario
 */
@Component
@Scope("session")
public class ResultCache {
    
    private TriangleMesh mesh;

    public TriangleMesh getMesh() {
        return mesh;
    }

    public void setMesh(TriangleMesh mesh) {
        this.mesh = mesh;
    }
    
    
}
