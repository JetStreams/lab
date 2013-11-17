/*
 * 
 */
package de.science.hack;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import toxi.geom.Vec2D;
import static java.lang.Double.parseDouble;
import static java.lang.Math.abs;

/**
 *
 * @author Mario
 */
public class WindDataReader {
    
    private CSVReader createReader(String name) throws FileNotFoundException{
        File file = new File(name);
        FileReader reader = new FileReader(file);
        return new CSVReader(reader, ',');
    }
    
    private List<String[]> readString(String name){
        List<String[]> content = new ArrayList<>();
        try {
            CSVReader reader = createReader(name);
            content = reader.readAll();
        } catch (IOException ex) {
            Logger.getLogger(WindDataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
    
    private List<LonLatAltCoordinate> readCoordinates(String name){
        List<LonLatAltCoordinate> coordinates = new ArrayList<>(); 
        List<String[]> content = readString(name);
        for (String[] cnt : content) {
            //we assume that the array length is 3
            double lon = parseDouble(cnt[0]);
            double lat = parseDouble(cnt[1]);
            double alt = abs(parseDouble(cnt[2]));
            LonLatAltCoordinate lla = new LonLatAltCoordinate(lon, lat, alt);
            coordinates.add(lla);
        }
        return coordinates;
    }
    
    public List<StlCoordinate> read(String name){
        List<StlCoordinate> coordinates = new ArrayList<>();
        List<LonLatAltCoordinate> realCoordinates = readCoordinates(name);
        for (LonLatAltCoordinate realCoord : realCoordinates) {
            LonLatAltCoordinate groundCoord = new LonLatAltCoordinate(realCoord.getLon(), realCoord.getLat(), realCoord.getAlt() * 40000);
            StlCoordinate coordForGround = CoordinatesConverter.toStl(groundCoord);
            coordinates.add(coordForGround);
            StlCoordinate coordForWind = CoordinatesConverter.toStl(realCoord);
            coordinates.add(coordForWind);
        }
        return coordinates;
    }
}
