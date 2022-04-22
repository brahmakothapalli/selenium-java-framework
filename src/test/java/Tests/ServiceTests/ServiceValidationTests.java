package Tests.ServiceTests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;



public class ServiceValidationTests {

    @BeforeMethod
    public void beforeMethod() {

//        baseURI = "https://snap-naga.qa.nasinsurance.com/submission/broker_portal/policy?brokeId=24067";
//        baseURI = "https://www.royalsundaram.in";
        baseURI = "https://snap-naga.qa.nasinsurance.com/";
        RestAssured.registerParser("text/plain", Parser.JSON);

    }

    @Test
    public void testLoginWithJwt(){

        String jwt ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzE4MDUzNzcsImV4cCI6MTYzMTgwODk3NywiZW50aXRsZW1lbnRzIjpbNCw1LDYsNyw4LDksMTAsMTEsMTIsMTMsMTQsMTUsMTYsMTcsMTgsMTksMjAsMjEsMjIsMjMsMjQsMjUsMjYsMjcsMjgsMjksMzAsMzEsMzIsMzMsMzQsMzUsMzYsMzcsMzgsMzksNDAsNDEsNDIsNDMsNDQsNDUsNDYsNDcsNDgsNDksNTAsNTEsNTIsNTMsNTQsNTUsNTYsNTcsNTgsNTksNjAsNjEsNjIsNjMsNjQsNjUsNjYsNjcsNjgsNjksNzAsNzEsNzIsNzMsNzQsNzUsNzYsNzcsNzgsNzksODAsODEsODIsODMsODQsODUsODYsODcsODgsODksOTMsOTQsOTUsOTYsOTgsOTksMTAwLDEwMSwxMDIsMTAzLDEwNCwxMDUsMTA2LDEwNywxMDgsMTA5LDExMCwxMTEsMTEyLDExMywxMTQsMTE1LDExNiwxMTcsMTE4LDExOSwxMjAsMTIxLDEyMiwxMjMsMTI0LDEyNSwxMjYsMTI3LDEyOCwxMjksMTMwLDEzMiwxMzMsMTM0LDEzNywxMzgsMTM5LDE0MCwxNDEsMTQyLDE0MywxNDQsMTQ1LDE0NiwxNDcsMTQ4LDE0OSwxNTAsMTUxLDE1MiwxNTMsMTU0LDE1NSwxNTYsMTU3LDE1OCwxNTksMTYwLDE2MSwxNjIsMTYzLDE2NCwxNjUsMTY2LDE2NywxNjgsMTY5LDE3MCwxNzEsMTcyLDE3MywxNzQsMTc1LDE3NiwxNzcsMTc4LDE3OSwxODAsMTgxLDE4MiwxODMsMTg0LDE4NSwxODYsMTg3LDE4OCwxODksMTkwLDE5MSwxOTIsMTkzLDE5NCwxOTUsMTk2LDE5NywxOTgsMTk5LDIwMCwyMDEsMjAyLDIwNCwyMDUsMjA2LDIwNywyMDgsMjA5LDIxMCwyMTEsMjEyLDIxMywyMTQsMjE1LDIxNiwyMTcsMjE4LDIxOSwyMjAsMjIxLDIyMiwyMjMsMjI0LDIyNSwyMjYsMjI3LDIyOCwyMjksMjMwLDIzMSwyMzIsMjMzLDIzNCwyMzUsMjM2LDIzNywyMzgsMjM5LDI0MCwyNDEsMjQyLDI0MywyNDQsMjQ1LDI0NiwyNDcsMjQ4LDI0OSwyNTAsMjUxLDI1MiwyNTMsMjU0LDI1NSwyNTYsMjU3LDI1OCwyNTksMjYwLDI2MSwyNjIsMjYzLDI2NCwyNjUsMjY2LDI2NywyNjgsMjY5LDI3MCwyNzEsMjcyLDI3MywyNzQsMjc1LDI3NiwyNzcsMjc4XSwidXNlcl9pZCI6M30.SV-xQPlxWxasZgs7OOFnR-EV4LlmPJ6t74zuUZmkZp8";

        Map<String , String> headers = new HashMap<String, String>(){{
            put("Accept", "application/json");
            put("Authorization", jwt);
        }
        };

        given().headers(headers).
                when().get(baseURI).
                then().assertThat().
                statusCode(200);
    }

    @Test
    public void getLoginUserDetails() {

        given().header("userName", "corp").header("password", "Pass@123")
                .when().post("/loginUserDetails")
                .then().statusCode(200);
    }

    @Test
    public void getAuthorities() {

        Response response = given().header("userName", "corp").header("password", "Pass@123")
                .when().post("/loginUserDetails")
                .then().assertThat().statusCode(200)
                .and().extract().response();

        JsonPath jsonPath = response.jsonPath();

        System.out.println("Response: "+response.getBody().asString());

        //System.out.println(jsonPath.get("visibleBranches.XX").toString());

        //System.out.println(jsonPath.get("authorites[0].role.role").toString());

        List<String> roles = jsonPath.get("authorites[0].role.role");

        assertThat(roles, hasItem("ROLE_DIGITAL_SIGNATURE"));

        assertThat(roles, hasItems("ROLE_DIGITAL_SIGNATURE", "ROLE_TPPG_NEWUI"));


    }
}
