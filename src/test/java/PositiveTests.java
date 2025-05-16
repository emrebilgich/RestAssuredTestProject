import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PositiveTests {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetPostById() {
        given()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", equalTo(1))
            .body("title", not(isEmptyOrNullString()))
            .time(lessThan(1000L));
    }

    @Test
    public void testCreateNewPost() {
        String requestBody = """
            {
              "title": "Yeni Başlık",
              "body": "Bu test POST isteğidir.",
              "userId": 5
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("Yeni Başlık"))
            .body("body", containsString("test"))
            .body("userId", equalTo(5))
            .body("id", notNullValue());
    }
}