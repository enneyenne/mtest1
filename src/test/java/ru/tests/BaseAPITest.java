package ru.tests;

import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import ru.enums.HttpStatusCodes;
import ru.models.AuthData;
import ru.utils.AppConfigs;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.enums.Endpoints.AUTH_ENDPOINT;
import static ru.enums.Endpoints.HEALTHCHECK_ENDPOINT;

@Epic("Базовые тесты")
@DisplayName("BaseAPITest")
public class BaseAPITest {

    private static final AppConfigs cfg = ConfigFactory.create(AppConfigs.class);
    protected String token;

    @BeforeAll
    public static void test_setUp() {
        RestAssured.baseURI = cfg.host();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @DisplayName("HealthCheck")
    @Test
    public void test_healthCheck() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(HEALTHCHECK_ENDPOINT);

        assertEquals(201, HttpStatusCodes.CREATED.getCode());
    }

    @DisplayName("PositiveAuth")
    @BeforeEach
    @Test
    public void test_positiveAuth() {
        AuthData authData = new AuthData("admin", "password123");

        String token = given()
                .contentType(ContentType.JSON)
                .body(authData)
                .post(AUTH_ENDPOINT)
                .then()
                .extract().jsonPath().getString("token");

        assertEquals(200, HttpStatusCodes.OK.getCode());
        Assertions.assertNotNull(token, "token is empty");

        this.token = token;
    }

    @DisplayName("NegativeAuth")
    @Test
    public void test_negativeAuth() {
        AuthData authData = new AuthData("admin", "password321");

        String token = given()
                .contentType(ContentType.JSON)
                .body(authData)
                .post(AUTH_ENDPOINT)
                .then()
                .statusCode(HttpStatusCodes.OK.getCode())
                .extract().jsonPath().getString("reason");

        assertEquals(token, "Bad credentials");
    }
}
