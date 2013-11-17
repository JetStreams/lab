/*
 * 
 */
package de.science.hack;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class holds the Projection for each longitude and latitude.
 * @author Mario
 */
public class WindData {
    
    private Map<Float,List<PointProjection>> data = new TreeMap<>();
    
    public void put(float lon, List<PointProjection> projections){
        data.put(lon, projections);
    }
    
    public List<PointProjection> getProjections(float lon){
        return data.get(lon);
    }
    
    public Set<Float> getKeys(){
        return data.keySet();
    }
}
