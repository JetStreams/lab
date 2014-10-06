/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import static java.util.Arrays.stream;
import java.util.Optional;
import static java.util.Optional.empty;
import org.apache.commons.lang.StringUtils;

/**
 * The model type to be used.
 * @author Mario
 */
public enum GlobeType {
    
    /** globe as a sphere **/
    Full("f", "full_globe.stl"), 
    /** wired frame, space between continents **/
    Wire("w","wire_globe.stl");

    private GlobeType(String key, String modelName) {
        this.key = key;
        this.modelName = modelName;
    }
    
    private String key;
    private final String modelName;

    public static Optional<GlobeType> getByKey(String key) {
        Optional<GlobeType> opt = empty();
        if(StringUtils.isNotBlank(key)){
            opt = stream(GlobeType.values()).filter(type -> type.getKey().equals(key)).findFirst();
        }
        return opt;
    }

    public String getKey() {
        return key;
    }
    
    public String getModelName() {
        return modelName;
    }
    
}
