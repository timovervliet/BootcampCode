package api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class GetRespondLastResultTest {

    @Test
    public void getAndAssertResult(){
        given().when().get("http://ergast.com/api/f1/current/last/results.json").then().assertThat().statusCode(200);

        System.out.println(given().when().get("http://ergast.com/api/f1/current/last/results.json").body().prettyPrint());
    }
}
