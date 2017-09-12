package com.integrityvision.concatenatedchallenge.input;

import com.integrityvision.concatenatedchallenge.model.AlphabeticListProvider;
import com.integrityvision.concatenatedchallenge.utilities.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class reads data from a file. Implements StringsLoader interface
 */
public class FileStringsLoader implements StringsLoader {
    private static final Logger log = LoggerFactory.getLogger(FileStringsLoader.class);

    /**
     * Opens and reads the content of file specified in input.properties folder.
     * Input file should be located in resources folder of the project
     *
     * @exception RuntimeException if failed to load an input file
     * @return Alphabetic List Provider. Each String is a one line of file
     */
    public AlphabeticListProvider load(){
        AlphabeticListProvider provider = new AlphabeticListProvider();

        String file = PropertiesLoader.getProperty("input.properties", "words_file");
        log.info("Loading a {} file", file);

        try(BufferedReader reader = new BufferedReader(
                                            new InputStreamReader(
                                                    Thread.currentThread().getContextClassLoader().getResourceAsStream(file)
                                            ))){
            String line;
            while((line = reader.readLine()) != null){
                provider.add(line);
            }

        } catch(IOException ioe){
            log.error("Failed to load input file", ioe);
            throw new RuntimeException("Failed to load the input file.", ioe);
        }

        return provider;
    }
}
