package utils.filereader;


import enums.ConstantVariable;
import utils.logging.Logger;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Properties;

public class ConfigurationReader {

    private static final Logger logger = Logger.getInstance();
    private static final Properties prop = new Properties();
    private static ConfigurationReader configurationReader=null;

    private ConfigurationReader() {
        logger.info("Loading the properties file");
        String filePath;
        if(isProd())
            filePath = ConstantVariable.PROD_CONFIG_PROP_FILEPATH;
        else
            filePath = ConstantVariable.QA_CONFIG_PROP_FILEPATH;
        try(InputStream inputStream = Files.newInputStream(new File(filePath).toPath())){
            prop.load(inputStream);
        }catch (Exception e){
            logger.error("Failed to load the config prop file");
        }
    }

    public static ConfigurationReader getInstance() {
        logger.info("Loading the configuration properties :: getConfigInstance :: ConfigurationReader");
        if(configurationReader == null){
            synchronized (ConfigurationReader.class) {
                configurationReader = new ConfigurationReader();
            }
        }
        return configurationReader;
    }

    public static String getProperty(String key){
        String envValue =System.getenv(key.toUpperCase());
        return envValue.isBlank() ? prop.getProperty(key) : envValue;
    }

    public static String getEnvironment(){
        String env = System.getenv("TEST_ENV");
        return Objects.isNull(env)? "prod" : env;
    }

    public static boolean isProd(){
        return getEnvironment().equalsIgnoreCase("prod");
    }
}
