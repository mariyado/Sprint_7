import scooter.CreateCourier;
import scooter.DeleteCourier;
import scooter.LoginCourier;
import io.restassured.response.Response;
import scooter.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Steps {

    public static Response createCourier(CreateCourier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(Constants.COURIER_CREATE);
        return response;
    }

    public static void deleteCourier(String loginCourier, String password) {
        String courierId;

        LoginCourier loginData = new LoginCourier(loginCourier, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(loginData)
                        .when()
                        .post(Constants.COURIER_LOGIN);
        response.then().assertThat().statusCode(equalTo(200));
        courierId = response.jsonPath().getString("id");

        if (courierId != null) {
            DeleteCourier deleteCourier = new DeleteCourier();
            Response responseDelCourier = given()
                    .header("Content-type", "application/json")
                    .body(deleteCourier)
                    .when()
                    .delete(Constants.COURIER_DELETE + courierId);
        }
    }

    public static Response newOrder(Order order) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(Constants.ORDER_CREATE);
        return response;
    }

    public static Response loginCourier(LoginCourier loginData) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginData)
                .when()
                .post(Constants.COURIER_LOGIN);
        return response;
    }

}