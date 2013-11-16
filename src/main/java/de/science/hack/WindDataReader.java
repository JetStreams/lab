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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public List<Stl> read(String name){
        List<Stl> data = new ArrayList<>();
        
        return data;
    }
}
