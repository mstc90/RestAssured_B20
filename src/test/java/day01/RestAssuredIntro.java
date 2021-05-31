package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class RestAssuredIntro {

    @DisplayName("Spartan / api/hello Endpoint Test")
    @Test
    public void  TestHello(){

        // This is the public ip I shared for spartan2
        // use it if you do not have your own
        // if you have your own, use your own IP
        //http://3.93.146.76:8000/api/hello


        Response response = get("http://3.93.146.76:8000/api/hello");
        // get status code from this Response object
        System.out.println("status code is: "+ response.statusCode());

        // assert the status code is 200
        assertThat( response.statusCode(), is(200));
        // how to pretty print entire response body
        // prettyPrint() -- print and return the body as String
        String payload = response.prettyPrint();
        // assertThat the body is Hello from Sparta

        assertThat(payload, is ("Hello from Sparta"));


        // get the header called ContentType from the response
        System.out.println( response.getHeader("Content-Type"));
        System.out.println( response.getContentType() );
        System.out.println( response.contentType() );

        // assert That Content-Type is text/plain;charset=UTF-8
        assertThat( response.contentType(), is("text/plain;charset=UTF-8") );

        // assert That Content-Type startWith next
        assertThat(response.contentType(), startsWith("text"));

        // Easy way to work with Content-Type without typing much
        // We can use ContentType Enum from RestAssured to easily get main part content-type
        // Content-Type.TEXT -->> text/plain as Enum
        // startWith accept a String object
        // so use toString method to turn Content-Type.TEXT to String, so we can use it with startWith
        assertThat(response.contentType(), startsWith(ContentType.TEXT.toString() ) );
        assertThat(response.contentType(), is(not(ContentType.JSON ) ) );

    }

    @DisplayName("Common Matchers for String")
    @Test
    public void testString(){

        String str = "Rest Assured is cool so far" ;
        // assert the str is "Rest Assured is cool so far"
        assertThat(str, is("Rest Assured is cool so far"));

        // assert the str is "Rest Assured IS COOL so far" in case insensitive manner
        assertThat(str, equalToIgnoringCase("Rest Assured IS COOL so far"));

        // assert the str startWith "Rest"
        assertThat(str, startsWith("Rest"));

        // assert the str endWith "so far"
        assertThat(str, endsWith("so far"));

        // assert the str contains "is cool"
        assertThat(str, containsString("is cool") );

        // assert the str contains "IS COOL" case insensitive manner
        assertThat(str, containsStringIgnoringCase("IS COOL") );
    }
}


