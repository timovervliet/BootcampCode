package api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRespondParamTest {
    private static String season = "2017";

    @Test
    public void testPostResponse(){
        given()
                .pathParam("season",season)
                .when().post("http://ergast.com/api/f1/{season}/last/results.xml")
                .then().statusCode(200);
    }
}
