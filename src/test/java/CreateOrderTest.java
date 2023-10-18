import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import scooter.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTest{

    private final String[] color;
    Order order;
    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] parameters() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK", "GRAY"}},
                {new String[]{""}}
        };
    }

    @Test
    @DisplayName("makeOrderTest")
    public void makeOrderTest() {
        order = new Order("Ольга", "Смирнова", "Ул. Филатова 10", "Пионерская", "+79185555555", 1, "2023-10-28", "нет", color);
        Response response = Steps.newOrder(order);
        response.then()
                .assertThat().statusCode(equalTo(201)).body("track", notNullValue());
    }

}