package com.integrityvision.concatenatedchallenge.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utility class to load properties
 */
public class PropertiesLoader {
    private static final Map<String, Properties> propertiesMap = new HashMap<>();

    public static String getProperty(String from, String name){
        if(!propertiesMap.containsKey(from)){
            propertiesMap.put(from, load(from));
        }

        return propertiesMap.get(from).getProperty(name);
    }

    private static Properties load(String path){
        InputStream PROPERTIES = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        Properties prop = new Properties();

        try {
            prop.load(PROPERTIES);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }
}
