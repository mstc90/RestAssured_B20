package day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class OpenMovieDB_Test {

    // http://www.omdbapi.com/?i=tt3896198&t=The Nun

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://www.omdbapi.com";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Test Search Movie or OpenMovieDB Test")
    @Test
    public void testMovie(){

        given()
                .queryParam("apiKey", "d75d8d5" )
                .queryParam("t", "The Orville").
        when()
                .get().prettyPeek(). // our request URL is already complete, do not need to add anything here
        then()
                .statusCode( is (200) )
                .contentType( ContentType.JSON )
                .body("Title", is ("The Orville"))
                .body("Ratings[0].Source", is("Internet Movie Database"))
                ;

    }


    @DisplayName("Getting the log of request and response")
    @Test
    public void testSendingRequestAndGEtTheLog(){

        given()
                .queryParam("apiKey","d75d8d5" )
                .queryParam("t","John Wick")
                // logging the request should be in given section
                .log().all().
//                .log().uri().
//                  .log().params().
        when()
                .get().
                then()
                // logging the response should be in then section
//                .log().all()
//                .log().status()
//                .log().body()
                .log().ifValidationFails()
                .statusCode(  is(200)  )
                .body("Plot", containsString("ex-hit-man") )
                // second Ratings source is Rotten Tomato
                .body("Ratings[1].Source",is("Rotten Tomatoes") )

        ;

    }
}







































