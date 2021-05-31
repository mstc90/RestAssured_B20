package day04;

import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class SpartanUpdatingTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://54.90.101.103:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }

    @DisplayName("Testing PUT /api/spartans/{id} with String body")
    @Test
    public void testUpdatingSingleSpartanWithStringBody() {

        String updateStrPayload = "    {\n" +
                "        \"name\": \"B20 Vola\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 9876543210\n" +
                "    }";

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(updateStrPayload).
                when()
                .put("/spartans/{id}").
                then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                // This is how we can check a header exists by checking the value is not null
                // using notNullValue() matcher
                .header("Date", is(notNullValue()))
        ;
    }

        @DisplayName("Testing PATCH /api/spartans/{id} with String body")
        @Test
        public void testPartialUpdatingSingleSpartanWithStringBody(){

            // update the name to B20 Patched
            // {"name" : "B20 Patched"}
            String patchBody = "{\"name\" : \"B20 Patched\"}";
            given()
                    .auth().basic("admin","admin")
                    .log().all()
                    .contentType(ContentType.JSON)
                    .pathParam("id",1)
                    .body(patchBody).
            when()
                    .patch("/spartans/{id}").
            then()
                    .log().all()
                    .assertThat()
                    .statusCode( is(204))
                    .body( emptyString() )

            ;
        }
    @DisplayName("Testing Delete /api/spartans/{id}")
    @Test
    public void testDeletingSingleSpartan(){

        given()
                .log().all()
                .auth().basic("admin","admin")
                .pathParam("id",534).
        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204) )
                .body( emptyString() )  ;
    }


    }





























