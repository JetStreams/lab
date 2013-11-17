/*
 * 
 */
package de.science.hack;

import static ch.lambdaj.Lambda.by;
import static ch.lambdaj.Lambda.group;
import static ch.lambdaj.Lambda.on;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Math.abs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.com.bytecode.opencsv.CSVReader;
import ch.lambdaj.group.Group;

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

    private Group<Coordinate> readCoordinates(String name) {
        List<Coordinate> coordinates = new ArrayList<>();
        List<String[]> content = readString(name);
        for (String[] cnt : content) {
            //we assume that the array length is 3
            double lon = parseDouble(cnt[0]);
            double lat = parseDouble(cnt[1]);
            double alt = abs(parseDouble(cnt[2]));
            coordinates.add(new Coordinate(lon, lat, alt));
        }
        return group(coordinates, by(on(Coordinate.class).getLon()));
    }

    public SortedMap<Float,List<PointProjection>> read(String name) {
        SortedMap<Float,List<PointProjection>> data = new TreeMap<>();
        Group<Coordinate> group = readCoordinates(name);
        Set<String> keys = group.keySet();
        for (String key : keys) {
            List<PointProjection> projections = new ArrayList<>();
            List<Coordinate> coordinates = group.find(key);
            for (Coordinate coord : coordinates) {
                Coordinate groundCoord = (Coordinate)coord.clone();
                groundCoord.setAlt(0.0);
                
                ModellPoint groundPoint = CoordinatesConverter.toModel(groundCoord);
                ModellPoint dataPoint = CoordinatesConverter.toModel(coord);
                PointProjection projection = new PointProjection(groundPoint, dataPoint);
                projections.add(projection);
            }
            data.put(parseFloat(key), projections);
        }

        return data;
    }
}
