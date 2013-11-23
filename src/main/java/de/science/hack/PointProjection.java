/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

/**
 * Represent a a radial line between the sphere's surface to the wind data point.
 * @author Mario
 */
public class PointProjection {
    
    private ModelPoint groundPoint;
    
    private ModelPoint dataPoint;

    public PointProjection(ModelPoint groundPoint, ModelPoint dataPoint) {
        this.groundPoint = groundPoint;
        this.dataPoint = dataPoint;
    }

    /**
     * Get the point on the surface.
     * @return 
     */
    public ModelPoint getGroundPoint() {
        return groundPoint;
    }

    /**
     * Get the actual data point.
     * @return 
     */
    public ModelPoint getDataPoint() {
        return dataPoint;
    }
    
}
