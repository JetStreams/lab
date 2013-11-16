/*
 * 
 */
package de.science.hack;

import au.com.bytecode.opencsv.CSVReader;
import ch.lambdaj.group.Group;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;
import static java.lang.Math.abs;
import static ch.lambdaj.Lambda.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Mario
 */
public class WindDataReader {

    private CSVReader createReader(String name) throws FileNotFoundException {
        File file = new File(name);
        FileReader reader = new FileReader(file);
        return new CSVReader(reader, ',');
    }

    private List<String[]> readString(String name) {
        List<String[]> content = new ArrayList<>();
        try {
            CSVReader reader = createReader(name);
            content = reader.readAll();
        } catch (IOException ex) {
            Logger.getLogger(WindDataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

    private Group<LonLatAltCoordinate> readCoordinates(String name) {
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
        return group(coordinates, by(on(LonLatAltCoordinate.class).getLon()));
    }

    public Map<String, List<StlCoordinate>> read(String name) {
        Map<String, List<StlCoordinate>> map = new HashMap<>();
        Group<LonLatAltCoordinate> group = readCoordinates(name);
        Set<String> keys = group.keySet();
        for (String key : keys) {
            List<StlCoordinate> stlCoordinates = new ArrayList<>();
            List<LonLatAltCoordinate> coordinates = group.find(key);
            for (LonLatAltCoordinate coord : coordinates) {
                LonLatAltCoordinate groundCoord = new LonLatAltCoordinate(coord.getLon(), coord.getLat(), 0);
                StlCoordinate coordForGround = CoordinatesConverter.toStl(groundCoord);
                stlCoordinates.add(coordForGround);
                StlCoordinate coordForWind = CoordinatesConverter.toStl(coord);
                stlCoordinates.add(coordForWind);
            }
            map.put(key, stlCoordinates);
        }

        return map;
    }
}
