import io.qameta.allure.junit4.DisplayName;
import scooter.CreateCourier;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class CreateCourierTest extends BaseTest{
    CreateCourier newCourier;

    String loginCourier = "Courier178"; //логин создаваемого курьера
    String password = "123"; //пароль создаваемого курьера
    String firstName = "Ivan";


    @Test
    @DisplayName("createCourierTest")
    public void createCourierTest() {
        newCourier = new CreateCourier(loginCourier, password, firstName);
        Response response = Steps.createCourier(newCourier);
        response.then()
                .assertThat().statusCode(equalTo(201))
                .assertThat().body("ok", is(true));
        Steps.deleteCourier(loginCourier, password);
    }

    @Test
    @DisplayName("createSameCourierTest")
    public void createSameCourierTest() {
        newCourier = new CreateCourier(loginCourier, password, firstName);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(201));
        newCourier = new CreateCourier(loginCourier, password, firstName);
        Response responseThatCourierIsNotCreate = Steps.createCourier(newCourier);
        responseThatCourierIsNotCreate.then().assertThat().statusCode(equalTo(409));
        Steps.deleteCourier(loginCourier, password);
    }

    @Test
    @DisplayName("createCourierWithoutLoginTest")
    public void createCourierWithoutLoginTest() {
        newCourier = new CreateCourier(null, password, firstName);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("createCourierWithoutPasswordTest")
    public void createCourierWithoutPasswordTest() {
        newCourier = new CreateCourier(loginCourier, null, firstName);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("createCourierWithoutLoginAndPasswordTest")
    public void createCourierWithoutLoginAndPasswordTest() {
        CreateCourier newCourier = new CreateCourier("", "", firstName);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(400));
    }

}