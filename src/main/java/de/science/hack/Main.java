/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

import java.io.File;
import java.util.Collection;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import toxi.geom.mesh.TriangleMesh;

import static org.apache.commons.io.FileUtils.listFiles;

/**
 * Entry point for the jetstreams models. <br/>
 * It accepts .txt or .csv files as a data input.
 *
 * @author Mario
 */
public class Main {
    private static final String I = "i";
    private static final String O = "o";
    private static final String IN = "input";
    private static final String OUT = "output";
    private static final String INPUT_DESC = "Folder with the wind data.";
    private static final String OUTPUT_DESC = "Full path of the output.";
    private static final String DEFAULT_OUT = "model.stl";

    private CommandLineParser parser;
    private Options options;
    private WindModelBuilder windModelBuilder;
    private JetStreamModelWriter jetStreamModelWriter;


    public Main() {
        parser = new GnuParser();
        
        Option optionInput = OptionBuilder.withArgName(IN).hasArg().withDescription(INPUT_DESC).isRequired(true).create(I);
        Option optionOutput = OptionBuilder.withArgName(OUT).hasArg().withDescription(OUTPUT_DESC).create(O);
        options = new Options();
        options.addOption(optionInput);
        options.addOption(optionOutput);
        
        windModelBuilder = new WindModelBuilder();
        jetStreamModelWriter = new JetStreamModelWriter();
    }

    /**
     * This method process the arguments.
     * @param args
     * @throws ParseException 
     */
    private void process(String [] args) throws ParseException {
        CommandLine commandLine = parser.parse(options, args);
        
        String[] extensions = new String[]{"txt", "csv"};
        File folder = new File(getInput(commandLine));
        Collection<File> inputFiles = listFiles(folder, extensions, false);
        for (File file : inputFiles) {
            TriangleMesh mesh = windModelBuilder.build(file);
            jetStreamModelWriter.addWindModel(mesh);
        }
        
        jetStreamModelWriter.write(getOutput(commandLine));
    }
    
    /**
     * This method returns the input folder from the command line.
     * @param commandLine
     * @return 
     */
    private String getInput(CommandLine commandLine){
        return commandLine.getOptionValue(I);
    }
    
    /**
     * This method returns the output file from the command line.
     * @param commandLine
     * @return 
     */
    private String getOutput(CommandLine commandLine){
        String val = commandLine.getOptionValue(O);
        return (val == null) ? getDefaultOut() : val;//return default if not provided
    }

    private String getDefaultOut() {
        File file = new File(System.getProperty("user.dir"), DEFAULT_OUT);
        return file.getPath();
    }
    
    public static void main(String[] args) throws ParseException {
        Main m = new Main();
        m.process(args);
    }
}
