/*
 * Represents coordinates in ECEF, howeever the origin is not within the sphere
 * but in the lower left corner of a cube around the sphere.
 */
package de.science.hack;

/**
 *
 * @author Mario
 */
public class Stl {
    private double x;
    private double y;
    private double z;

    public Stl(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
