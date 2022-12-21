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
    public Object[][] dataProvider(){

    }

    public void readTestData() throws IOException, ParseException {

        FileReader fileReader = new FileReader("src/main/resources/googleTestData.json");

        JSONParser jsonParser = new JSONParser();

        // parsing to an object
        Object javaObject = jsonParser.parse(fileReader);

        // converting the java object to json object
        JSONObject jsonObject = (JSONObject) javaObject;

        // now get the test data object of the test case, which is Array
        JSONArray jsonArray = (JSONArray) jsonObject.get("test1");

        int enabledSets = 0;

        // read the data

        // parse it

        // put it in the array

    }
}
