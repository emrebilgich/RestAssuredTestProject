package com.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NegativeTests {

    @Test
    public void testInvalidUserRegistration() {
        // Base URI ayarlanıyor
        RestAssured.baseURI = "https://reqres.in";

        // Eksik body: sadece email var ama password yok
        String invalidRequestBody = """
            {
                "email": "eve.holt@reqres.in"
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(invalidRequestBody)
        .when()
            .post("/api/register")
        .then()
            .statusCode(401); // Beklenen: 400 Bad Request
    }
}
