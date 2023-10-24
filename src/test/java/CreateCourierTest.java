import io.qameta.allure.junit4.DisplayName;
import scooter.CreateCourier;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class CreateCourierTest extends BaseTest{
    CreateCourier newCourier;

    String loginCourier;
    String password;
    String firstName;


    @Test
    @DisplayName("createCourierTest")
    public void createCourierTest() {
        newCourier = new CreateCourier();
        Response response = Steps.createCourier(newCourier);
        response.then()
                .assertThat().statusCode(equalTo(201))
                .assertThat().body("ok", is(true));

        loginCourier = newCourier.getLogin();
        password = newCourier.getPassword();

        Steps.deleteCourier(loginCourier, password);
    }

    @Test
    @DisplayName("createSameCourierTest")
    public void createSameCourierTest() {
        newCourier = new CreateCourier();
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(201));
        Response responseThatCourierIsNotCreate = Steps.createCourier(newCourier);
        responseThatCourierIsNotCreate.then().assertThat().statusCode(equalTo(409));

        loginCourier = newCourier.getLogin();
        password = newCourier.getPassword();
        Steps.deleteCourier(loginCourier, password);
    }

    @Test
    @DisplayName("createCourierWithoutLoginTest")
    public void createCourierWithoutLoginTest() {
        newCourier = new CreateCourier();
        newCourier.setLogin(null);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("createCourierWithoutPasswordTest")
    public void createCourierWithoutPasswordTest() {
        newCourier = new CreateCourier();
        newCourier.setPassword(null);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("createCourierWithoutLoginAndPasswordTest")
    public void createCourierWithoutLoginAndPasswordTest() {
        CreateCourier newCourier = new CreateCourier();
        newCourier.setPassword(null);
        newCourier.setLogin(null);
        Response response = Steps.createCourier(newCourier);
        response.then().assertThat().statusCode(equalTo(400));
    }

}