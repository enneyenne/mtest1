package ru.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.models.AuthData;

import static io.restassured.RestAssured.given;

public class AuthTest {

    private static final String endpoint = "/auth";

    @BeforeAll
    public static void test_setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void test_auth() {
        AuthData authData = new AuthData("admin", "password123");

        String token = given()
                .contentType(ContentType.JSON)
                .body(authData)
                .post(endpoint)
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);
        System.out.println("\nauth successful, token is " + token);
    }

}
