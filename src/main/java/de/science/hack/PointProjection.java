/*
 * 
 */
package de.science.hack;

/**
 * Represent a a radial vector from the sphere's surface to the wind data point.
 * @author Mario
 */
public class PointProjection {
    
    private ModellPoint groundPoint;
    
    private ModellPoint dataPoint;

    public PointProjection(ModellPoint groundPoint, ModellPoint dataPoint) {
        this.groundPoint = groundPoint;
        this.dataPoint = dataPoint;
    }

    public ModellPoint getGroundPoint() {
        return groundPoint;
    }

    public ModellPoint getDataPoint() {
        return dataPoint;
    }
    
}
