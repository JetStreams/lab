/*
 * 
 */
package de.science.hack;

/**
 * Represent a a radial vector from the sphere's surface to the wind data point.
 * @author Mario
 */
public class StlPointProjection {
    
    private StlPoint groundPoint;
    
    private StlPoint dataPoint;

    public StlPointProjection(StlPoint groundPoint, StlPoint dataPoint) {
        this.groundPoint = groundPoint;
        this.dataPoint = dataPoint;
    }

    public StlPoint getGroundPoint() {
        return groundPoint;
    }

    public StlPoint getDataPoint() {
        return dataPoint;
    }
    
}
