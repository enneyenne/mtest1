package ru.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.models.AuthData;
import ru.tests.BaseAPITest;

import static io.restassured.RestAssured.given;
import static ru.enums.Endpoints.AUTH_ENDPOINT;

public class AuthSteps extends BaseAPITest {

    @Step("PositiveAuth")
    public static Response createToken() {

        AuthData authData = new AuthData("admin", "password123");

        return given()
                .contentType(ContentType.JSON)
                .body(authData)
                .when()
                .post(AUTH_ENDPOINT)
                .then()
                .extract()
                .response();

    }
}
