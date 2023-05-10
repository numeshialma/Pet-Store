package package1.com.echo.postman;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuthTest {

    String Base_EndPoint="https://postman-echo.com/basic-auth";
    String userName= "package1/com/echo/postman";
    String password="password";

    @Test
    public void testBasicAuth() {
        given()
                .auth().basic(userName,password)
                .when()
                .get(Base_EndPoint)
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
}
