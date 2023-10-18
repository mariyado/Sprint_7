import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class GetOrderTest extends BaseTest{

    @Test
    @DisplayName("getOrderListTest")
    public void getOrderListTest() {
        given()
                .header("Content-type", "application/json")
                .when()
                .get(Constants.ORDER_CREATE)
                .then()
                .statusCode(200)
                .assertThat()
                .body("orders",  not(empty()));
    }
}