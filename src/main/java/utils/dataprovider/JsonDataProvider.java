package utils.dataprovider;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

public class JsonDataProvider {

    @DataProvider(name="jsonDataProvider")
    public static Object[][] getTestData(Method method) throws IOException, ParseException {

        String testName = method.getName();

        String testClassName = method.getAnnotation(Parameters.class).value()[0];

        String filePath = "src/main/resources/"+testClassName+".json";

        FileReader fileReader = new FileReader(filePath);

        JSONParser jsonParser = new JSONParser();

        // parsing to an object
        Object javaObject = jsonParser.parse(fileReader);

        // converting the whole file java object to json object
        JSONObject jsonObject = (JSONObject) javaObject;

        // now get the test data object of the test case, which is Array
        JSONArray jsonArray = (JSONArray) jsonObject.get(testName);
        int enabledSets = 0;
        for(Object dataSet: jsonArray){
            // converting each data set to json object
            JSONObject setObj = (JSONObject) dataSet;
            if(setObj.get("runMode").equals("Y")){
                enabledSets+=1;
            }
        }
        Object[][] testData = new Object[enabledSets][1];

        int n = 0;
        for(Object dataSet: jsonArray){
            JSONObject testDataSet = (JSONObject) dataSet;
            if(testDataSet.get("runMode").equals("Y")){
                testData[n][0] = testDataSet;
                n+=1;
            }
        }
        return testData;
    }
}
