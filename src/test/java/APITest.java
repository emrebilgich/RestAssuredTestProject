import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APITest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void testGETRequest() {
        long startTime = System.currentTimeMillis();

        given()
                .baseUri(BASE_URL)
        .when()
                .get("/posts/1")
        .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", notNullValue())
                .time(lessThan(1000L)); // 1 saniyenin altında mı?

        long endTime = System.currentTimeMillis();
        System.out.println("GET isteği süresi: " + (endTime - startTime) + " ms");
    }

    @Test
    public void testPOSTRequest() {
        String requestBody = "{\n" +
                "  \"title\": \"Test başlığı\",\n" +
                "  \"body\": \"Bu bir test içeriğidir.\",\n" +
                "  \"userId\": 10\n" +
                "}";

        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body("title", equalTo("Test başlığı"))
                .body("body", equalTo("Bu bir test içeriğidir."))
                .body("userId", equalTo(10))
                .body("id", notNullValue())
                .time(lessThan(1000L));
    }
}