package ru.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static ru.enums.Endpoints.BOOKING_ENDPOINT;

public class GetBookingIdsTest extends BaseAPITest {

    @Test
    public void test_getAllBookingIds() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(BOOKING_ENDPOINT)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @DisplayName("BookingIdsWithNameFilter")
    @ParameterizedTest
    @CsvFileSource(resources = "/guestsNames.csv")
    public void test_getBookingIdsWithNameFilter(String firstName, String lastName) {
        Response response = given()
                .contentType(ContentType.JSON)
                .param("firstname", firstName)
                .param("lastname", lastName)
                .when()
                .get(BOOKING_ENDPOINT)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @DisplayName("BookingIdsWithDateFilter")
    @ParameterizedTest
    @CsvSource({"2014-03-13, 2020-08-19", "2020-08-19, 2024-08-19"})
    public void test_getBookingIdsWithDateFilter(String checkIn, String checkOut) {
        Response response = given()
                .contentType(ContentType.JSON)
                .param("checkin", checkIn)
                .param("checkout", checkOut)
                .when()
                .get(BOOKING_ENDPOINT)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

}
