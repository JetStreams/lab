/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack.model;

import javax.vecmath.Point3d;

/**
 * A point in the model world.<br/>
 * Represents coordinates in ECEF, howeever the origin is not within the sphere
 * but in the lower left corner of a cube around the sphere.
 * 
 * It is not really necessary but makes it clearer to separate real world and
 * model coordinates.
 * 
 * @author Mario
 */
public class ModelPoint extends Point3d{

    public ModelPoint(double x, double y, double z) {
        super(x, y ,z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
