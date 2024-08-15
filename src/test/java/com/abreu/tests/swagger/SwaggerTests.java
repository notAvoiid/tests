package com.abreu.tests.swagger;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SwaggerTests {

    @LocalServerPort
    private int port;

    @Test
    void shouldDisplaySwaggerUiPage() {

        var content = given()
                .basePath("/swagger-ui/index.html")
                .port(port)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertTrue(content.contains("Swagger UI"));
    }

}
