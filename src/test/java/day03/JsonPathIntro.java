package day03;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class JsonPathIntro {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.93.146.76:8000";
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Extracting data out of Spartan Json Object")
    @Test
    public void test1SpartanPayload(){

        // send a request to get 1 spartan
        // by providing path params with valid id
        // save it into Response object
        // NEW : create an object with type JsonPath
        // by calling the method jsonPath() on response object
        // extract id , name , gender , phone
        // and save it into variable of correct type

        Response response = given()
                                    .pathParam("id", 100).
                            when()
                                    .get("/spartans/{id}")
                                    .prettyPeek()
                                    ;
        // response.prettyPrint();

        // JsonPath is used to navigate through the json payload
        // and extract the value according to the valid "jsonpath" provided
        JsonPath jp = response.jsonPath();
        int myID = jp.getInt("id");
        String myName = jp.getString("name");
        String myGender = jp.getString("gender");
        long myPhone = jp.getLong("phone");


        System.out.println("myId " + myID);
        System.out.println("myName " + myName);
        System.out.println("myGender " + myGender);
        System.out.println("myPhone " + myPhone);
    }

    @DisplayName("Extracting data from Json Array Response")
    @Test
    public  void getAllSpartanData(){


        // Response response = get("/spartan");
        // JsonPath jp = response.jsonPath();


        JsonPath jp = get("/spartans").jsonPath();

        // get the first json object name field
        System.out.println("jp.getString(\"name[0]\") = " + jp.getString("name[0]"));

        System.out.println("jp.getString(\"phone[0]\") = " + jp.getString("phone[0]"));


        // get the 7th json object gender field from json array
        System.out.println("jp.getString(\"gender[6]\") = "
                + jp.getString("gender[6]"));

        // getting all the name fields from jsonArray Response
        // and storing as a list
        List<String> allNames = jp.getList("name") ;
        System.out.println("allNames " + allNames );

        // getting all the phone fields from jsonArray Response
        // and storing as a list
        List<Long> allPhones = jp.getList("phone");
        System.out.println("allPhones " + allPhones);

    }

    // send request to this request url
    // http://100.26.101.158:8000/api/spartans/search?nameContains=de&gender=Male
    // get the name of the first guy in the result
    // get the phone of 3th guy in the result
    // get all names, all phones save it as list
    // save the value of field called empty under payable in the response
    // print it out

    @DisplayName("Testing /spartans/search and extracting data ")
    @Test
    public void testSearch(){

        JsonPath jp = given()
                            .queryParam("nameContains", "de")
                            .queryParam("gender", "Male").
                      when()
                            .get("/spartans/search")
                            .jsonPath();

        System.out.println("First guy name: " +
                jp.getString("content[0].name") );

        System.out.println("Third guy phone number: " +
                jp.getString("content[2].phone") );

        System.out.println("allNames: " + jp.getList("content.name") );
        System.out.println("allPhones: " + jp.getList("content.phone") );

        System.out.println("Value of field empty: " +
                jp.getBoolean("pageable.sort.empty"));
    }
}































