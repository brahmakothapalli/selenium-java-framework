package utils.dataProvider;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

public class JsonDataProvider {

    @DataProvider(name="jsonDataProvider")
    public static Object[][] getTestData(Method method) throws IOException, ParseException {

        String testName = method.getName();

        Test testAnnotation = method.getAnnotation(Test.class);

        String fileName = testAnnotation.description();

        FileReader fileReader = new FileReader("src/main/resources/"+fileName+".json");

        JSONParser jsonParser = new JSONParser();

        // parsing to an object
        Object javaObject = jsonParser.parse(fileReader);

        // converting the whole file java object to json object
        JSONObject jsonObject = (JSONObject) javaObject;

        // now get the test data object of the test case, which is Array
        JSONArray jsonArray = (JSONArray) jsonObject.get(testName);
        int testDataSetsCount =jsonArray.size();

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
