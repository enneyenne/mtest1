package ru.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static ru.enums.Endpoints.BOOKING_ENDPOINT;

public class DeleteBookingTest extends BaseAPITest {

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

        Assertions.assertEquals(201, response.statusCode());
    }

}
