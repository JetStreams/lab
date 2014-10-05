/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

/**
 * The model type to be used.
 * @author Mario
 */
public enum GlobeType {
    
    Full("full_globe.stl"), Wire("wire_globe.stl");

    private GlobeType(String modelName) {
        this.modelName = modelName;
    }
    
    private final String modelName;

    public String getModelName() {
        return modelName;
    }
    
}
