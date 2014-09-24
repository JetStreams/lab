/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
package de.science.hack;

/**
 * Command line arguments.
 *
 * @author Mario
 */
enum CliArg {

    F("f", "file", "File with the wind data."),
    D("d", "directory", "Directory with the wind data files."),
    O("o", "output", "Full path of the output (optional).");

    private CliArg(String shortKey, String longKey, String description) {
        this.shortKey = shortKey;
        this.longKey = longKey;
        this.description = description;
    }
    private final String description;
    private final String shortKey;
    private final String longKey;

    String getShortKey() {
        return shortKey;
    }

    String getLongKey() {
        return longKey;
    }

    String getDescription() {
        return description;
    }
}
