package Utils.DataProvider;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;

public class JsonDataProvider {

    @DataProvider(name="jsonDataProvider")
    public static Object[][] getTestData() throws IOException, ParseException {

        FileReader fileReader = new FileReader("src/main/resources/googleTestData.json");

        JSONParser jsonParser = new JSONParser();

        // parsing to an object
        Object javaObject = jsonParser.parse(fileReader);

        // converting the whole file java object to json object
        JSONObject jsonObject = (JSONObject) javaObject;

        // now get the test data object of the test case, which is Array
        JSONArray jsonArray = (JSONArray) jsonObject.get("test1");
        int testDataSetsCount =jsonArray.size();

        int enabledSets = 0;
        for(Object set: jsonArray){
            // converting each data set to json object
            JSONObject setObj = (JSONObject) set;
            if(setObj.get("runMode").equals("Y")){
                enabledSets+=1;
            }
        }

        Object[][] testData = new Object[enabledSets][1];
        int n = 0;
        for(Object sets: jsonArray){
            // converting each data set to json object
            JSONObject testDataSet = (JSONObject) sets;
            if(testDataSet.get("runMode").equals("Y")){
                testData[n][0] = testDataSet;
                n+=1;
            }
        }
        return testData;
    }
}
