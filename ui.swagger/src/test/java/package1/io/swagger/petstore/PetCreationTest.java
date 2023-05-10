package package1.io.swagger.petstore;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetCreationTest {

    String Base_EndPoint="https://petstore.swagger.io/v2";

    String Pay_Load = "{\n" +
            "  \"id\": 90210,\n" +
            "  \"category\": {\n" +
            "    \"id\": 10,\n" +
            "    \"name\": \"Labrador\"\n" +
            "  },\n" +
            "  \"name\": \"Tommy\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";

    @Test
    public void testCreateNewPet() { //POST
        given().log().headers()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body(Pay_Load)
                .when()
                .post(Base_EndPoint + "/pet")
                .then().log().all()
                .statusCode(200)
                .body(
                        "id",equalTo(90210),
                        "name",equalTo("Tommy"),
                        "category.id",equalTo(10),
                        "category.name",equalTo("Labrador")
                );
    }

    @Test
    public void testGetPetDetails() { //GET
        given().log().headers()
                .header("accept","application/json")
                .when()
                .get(Base_EndPoint + "/pet/90210")
                .then().log().all()
                .statusCode(200)
                .body(
                        "id",equalTo(90210),
                        "name",equalTo("Tommy"),
                        "category.id",equalTo(10),
                        "category.name",equalTo("Labrador")
                );
    }

    @Test
    public void testPutPetDetails() { //PUT
        JSONObject request=new JSONObject();

        request.put("id","30289");
        request.put("name","Shepard");

        System.out.println(request.toJSONString());

        given().log().headers()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body(request.toJSONString())
                .when()
                .put(Base_EndPoint + "/user/90210")
                .then().log().all()
                .statusCode(200);

    }



}
