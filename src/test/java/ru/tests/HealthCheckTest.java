package ru.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.enums.HttpStatusCodes;

import static ru.enums.Endpoints.HEALTHCHECK_ENDPOINT;

public class HealthCheckTest {

    @DisplayName("HealthCheck")
    @Test
    public void test_healthCheck() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(HEALTHCHECK_ENDPOINT);

        Assertions.assertEquals(HttpStatusCodes.CREATED.getCode(), response.statusCode());
    }

}
