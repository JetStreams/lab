/*
 * 
 */
package de.science.hack;

/**
 * Represent a a radial line between the sphere's surface to the wind data point.
 * @author Mario
 */
public class PointProjection {
    
    private ModellPoint groundPoint;
    
    private ModellPoint dataPoint;

    public PointProjection(ModellPoint groundPoint, ModellPoint dataPoint) {
        this.groundPoint = groundPoint;
        this.dataPoint = dataPoint;
    }

    /**
     * Get the point on the surface.
     * @return 
     */
    public ModellPoint getGroundPoint() {
        return groundPoint;
    }

    /**
     * Get the actual data point.
     * @return 
     */
    public ModellPoint getDataPoint() {
        return dataPoint;
    }
    
}
