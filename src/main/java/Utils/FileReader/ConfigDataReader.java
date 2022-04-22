package Utils.FileReader;


import Enums.ConstantVariable;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataReader {

    private static Logger logger = Logger.getLogger(ConfigDataReader.class);

    private static Properties prop;


    private ConfigDataReader(){

    }


    public static Properties ConfigPropInit() {

        logger.info("Loading the properties file in :: ConfigPropInit ");

        File file = new File(ConstantVariable.CONFIG_PROP_FILEPATH);

        if(prop == null){

            prop = new Properties();

            try (FileInputStream fis = new FileInputStream(file)) {

                prop.load(fis);

            } catch (IOException e) {

                logger.error("Failed to load the properties file in :: ConfigPropInit ");
            }
        }
        return prop;
    }
}
