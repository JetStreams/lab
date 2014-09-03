/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import static java.util.stream.Collectors.groupingBy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.science.hack.model.Coordinate;
import de.science.hack.model.Line;

import static de.science.hack.model.CoordinatesConverter.toModel;
import static de.science.hack.CsvReaderFactory.create;

/**
 * Reads the wind data from a CSV based file. The values in the file must be as
 * as a tripple of a longitude, latitude and wind speed per line separated by a
 * comma.
 * <br/>
 * So the actual data structure is not the pressure per coordinate, it is wind
 * speed per coordinate.
 *
 * @author Mario
 */
public class WindDataReader {

    private static final Logger LOG = LoggerFactory.getLogger(WindDataReader.class);

    /* factor to exxagerate the value for the wind speed */
    private static final int FAC = 40000; //might be passed as a parameter

    private List<String[]> readStrings(String name) {
        List<String[]> content = new ArrayList<>();
        try {
            content = readStrings(create(name));
        } catch (FileNotFoundException ex) {
            LOG.warn(ex.getLocalizedMessage(), ex);
        }
        return content;
    }

    private List<String[]> readStrings(CSVReader reader) {
        List<String[]> content = new ArrayList<>();
        try {
            content = reader.readAll();
        } catch (IOException ex) {
            LOG.warn(ex.getLocalizedMessage(), ex);
        }
        return content;
    }

    //read and group coordinates by longitude
    private Map<Double, List<Coordinate>> groupCoordinates(List<String[]> content) {
        return content.stream().map(cnt -> createCoordinate(cnt)).collect(groupingBy(Coordinate::getLon));
    }

    private static Coordinate createCoordinate(String[] cnt) throws NumberFormatException {
        //we assume that the array length is 3
        return new Coordinate(parseDouble(cnt[0]), parseDouble(cnt[1]), abs(parseDouble(cnt[2])) * FAC);
    }

    /**
     * Create the projections.
     *
     * @param coordinates collection of coordinates
     * @return a sortend map of lines, starting from ground till the altitude.
     */
    private SortedMap<Float, List<Line>> createProjections(Map<Double, List<Coordinate>> coordinates) {
        SortedMap<Float, List<Line>> data = new TreeMap<>();

        coordinates.entrySet().forEach(entry -> {
            Double key = entry.getKey();
            List<Line> projections = new ArrayList<>();
            entry.getValue().stream().forEach((coord) -> {

                Coordinate groundCoord = (Coordinate) coord.clone();
                groundCoord.setAlt(0.0);

                //create the line from surface to data point
                projections.add(new Line(toModel(groundCoord), toModel(coord)));

            });
            data.put(key.floatValue(), projections);
        });

        return data;
    }

    /**
     * This method reads the coordinates and wind speed from the file and
     * returns a {@link SortedMap} of all projections.<br/>
     * Projections means a thought line from the ground coordinate (altitude 0)
     * until the wind speed.
     *
     * @param fileName the file name
     * @return sorted map where the key is the longitude and the value is a list
     * of projections per latitude
     */
    public SortedMap<Float, List<Line>> read(String fileName) {

        return createProjections(groupCoordinates(readStrings(fileName)));
    }

    /**
     * This method reads the coordinates and wind speed from the file and
     * returns a {@link SortedMap} of all projections.<br/>
     * Projections means a thought line from the ground coordinate (altitude 0)
     * until the wind speed.
     *
     * @param bytes bytes to read from
     * @return sorted map where the key is the longitude and the value is a list
     * of projections per latitude
     */
    public SortedMap<Float, List<Line>> read(byte[] bytes) {
        
        CSVReader reader = create(bytes);
        return createProjections(groupCoordinates(readStrings(reader)));
    }
}
