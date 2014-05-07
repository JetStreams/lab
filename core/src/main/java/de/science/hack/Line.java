/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

/**
 * Represents a line between between two point in the wind data model.
 * @author Mario
 */
public class Line {
    
    private final ModelPoint point1;
    
    private final ModelPoint point2;

    /**
     * Constructor
     * @param point1 the start point (typically a point on the surface)
     * @param point2 the end point (typically this is the data point)
     */
    public Line(ModelPoint point1, ModelPoint point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    /**
     * Get the point on the surface.
     * @return 
     */
    public ModelPoint getPoint1() {
        return point1;
    }

    /**
     * Get the actual data point.
     * @return 
     */
    public ModelPoint getPoint2() {
        return point2;
    }
    
}
