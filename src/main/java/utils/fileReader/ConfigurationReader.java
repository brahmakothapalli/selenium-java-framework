package utils.fileReader;


import enums.ConstantVariable;
import utils.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static final Logger logger = Logger.getInstance();
    private static Properties prop;

    private ConfigurationReader(){}

    public static Properties getConfigInstance() {
        logger.info("Loading the configuration properties :: getConfigInstance :: ConfigurationReader");
        File file = new File(ConstantVariable.CONFIG_PROP_FILEPATH);
        if(prop == null){
            prop = new Properties();
            try (FileInputStream fis = new FileInputStream(file)) {
                prop.load(fis);
            } catch (IOException e) {
                logger.error("Failed to load the configuration properties :: getConfigInstance :: ConfigurationReader " + e.getMessage());
            }
        }
        return prop;
    }
}
