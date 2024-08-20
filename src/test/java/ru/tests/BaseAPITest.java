package ru.tests;

import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import ru.utils.AppConfigs;

@Epic("Базовые тесты")
@DisplayName("BaseAPITest")
public class BaseAPITest {

    private static final AppConfigs cfg = ConfigFactory.create(AppConfigs.class);

    @BeforeAll
    public static void test_setUp() {
        RestAssured.baseURI = cfg.host();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

}
