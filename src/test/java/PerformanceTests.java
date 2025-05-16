import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PerformanceTests {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testResponseTimeUnder1000ms() {
        long responseTime = 
            given()
            .when()
                .get("/posts/1")
            .time();

        System.out.println("Yanıt süresi: " + responseTime + " ms");
        assert responseTime < 1000 : "Yanıt süresi 1000ms'den büyük!";
    }

    @Test
    public void testMultipleRequestsPerformance() {
        for (int i = 1; i <= 10; i++) {
            long time = 
                given()
                .when()
                    .get("/posts/" + i)
                .time();

            System.out.println("Request " + i + " - Süre: " + time + " ms");
            assert time < 1000;
        }
    }
}