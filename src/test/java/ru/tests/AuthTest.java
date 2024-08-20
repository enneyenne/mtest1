package ru.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.enums.HttpStatusCodes;
import ru.steps.AuthSteps;

public class AuthTest extends AuthSteps {

    @Test
    public void test_positiveAuth() {

        createToken();
        String token = createToken().jsonPath().getString("token");

        Assertions.assertNotNull(token, "token is empty");
        Assertions.assertEquals(HttpStatusCodes.OK.getCode(), createToken().statusCode());

    }

}
