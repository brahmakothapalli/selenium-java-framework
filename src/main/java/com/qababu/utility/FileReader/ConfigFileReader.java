package com.qababu.utility.FileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private static Properties prop;

    //Restricting other classes to create object
    private ConfigFileReader() {
    }

    //To create single object to Config Properties file through-out the project
    public static Properties getConfigPropObject() throws IOException {
        if (prop == null) {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(new File(".\\src\\main\\resources\\config.properties"));
            prop.load(fis);
            fis.close();
        }
        return prop;
    }
}
