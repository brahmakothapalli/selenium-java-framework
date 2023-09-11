package helper;

import org.testng.asserts.SoftAssert;

public class SoftAssertionHelper {

    //Object shouldn't private and static
    private static SoftAssert softAssert = null;

    //make constructor private so that classes will not be abel create object
    private SoftAssertionHelper(){

    }

    //Provide global access to the other classes
    public static SoftAssert getSoftAssertObject(){

        if(softAssert == null){

            softAssert = new SoftAssert();
        }

        return softAssert;
    }

    public static void setSoftAssertToNull(){

        softAssert = null;
    }

}
