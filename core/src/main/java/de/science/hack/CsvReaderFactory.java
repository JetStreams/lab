/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Creates a new Instance of {@link CSVReader}.
 *
 * @author Mario
 */
final class CsvReaderFactory {

    private static final char SEP = ',';

    private CsvReaderFactory() {

    }

    static CSVReader create(String name) throws FileNotFoundException {
        File file = new File(name);
        FileReader reader = new FileReader(file);
        return new CSVReader(reader, SEP);
    }

    static CSVReader create(byte[] bytes) {
        InputStream is = new ByteArrayInputStream(bytes);
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(is));
        return new CSVReader(bfReader, SEP);
    }
}
