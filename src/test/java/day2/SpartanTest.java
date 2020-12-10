package day2;

import io.restassured.response.Response;

import static io.restassured.RestAssured.get;

public class SpartanTest {

    Response response = get("http://3.93.146.76:8000/api/hello");
}
