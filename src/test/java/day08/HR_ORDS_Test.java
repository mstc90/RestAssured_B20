package day08;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import testbase.HR_ORDS_TestBase;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_Test extends HR_ORDS_TestBase {


    //http://54.90.101.103:1000/ords/hr/countries/AR
    @DisplayName("Test GET /countries/{country_id} to POJO")
    @Test
    public void testCountryResponseToPOJO(){

        //Response response =  get("/countries/{country_id}", "AR").prettyPeek();
        Response response = given()
                .pathParam("country_id","AR").
                        when()
                .get("/countries/{country_id}").prettyPeek();
        Country ar = response.as(Country.class) ;
        System.out.println("Argentina = " + ar);

        Country ar1 = response.jsonPath().getObject("",Country.class);
        System.out.println("Argentina with jsonPath = " + ar1);


    }

    @DisplayName("Test GET /countries to List of POJO")
    @Test
    public void testAllCountriesResponseToListOfPOJO(){

        Response response = get("/countries").prettyPeek() ;

        List<Country> countryList = response.jsonPath().getList("items", Country.class) ;
        countryList.forEach(System.out::println);




    }


}



















