package ru.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.enums.HttpStatusCodes;

import static io.restassured.RestAssured.given;
import static ru.enums.Endpoints.BOOKING_ENDPOINT;
import static ru.steps.AuthSteps.createToken;

public class DeleteBookingTest extends BaseAPITest {

    private static final String token =
            createToken().jsonPath().getString("token");

    @Test
    public void test_deleteBooking() {

        String BOOKINGID = "57";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete(BOOKING_ENDPOINT + BOOKINGID)
                .then()
                .extract().response();

        Assertions.assertEquals(HttpStatusCodes.CREATED.getCode(), response.statusCode());
    }

}
