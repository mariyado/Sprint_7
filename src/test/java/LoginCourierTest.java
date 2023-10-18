import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import scooter.CreateCourier;
import scooter.LoginCourier;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class LoginCourierTest {

    CreateCourier newCourier;
    LoginCourier loginData;
    String loginCourier = "Courier178";
    String password = "123";
    String firstName = "Ivan";

    @Before
    public void setUp() {
        RestAssured.baseURI= BaseUri.URI;
        newCourier = new CreateCourier(loginCourier, password, firstName);
        Steps.createCourier(newCourier);
    }

    @Test
    @DisplayName("loginCourierWithCorrectDataTest")
    public void loginCourierTest() {

        loginData  = new LoginCourier(loginCourier, password);
        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(200));
    }

    @Test
    @DisplayName("loginCourierWithoutLoginTest")
    public void loginCourierWithoutLoginTest() {

        loginData  = new LoginCourier("", password);
        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("loginCourierWithoutPasswordTest")
    public void loginCourierWithoutPasswordTest() {

        LoginCourier loginData  = new LoginCourier(loginCourier, "");
        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(400));
    }

    @Test
    @DisplayName("loginCourierNotCorrectDataTest")
    public void loginCourierNotCorrectDataTest() {
        loginData  = new LoginCourier("jfgfgfjgh", "34545");

        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(404));
    }


    @After
    public void deleteCourier() {
        Steps.deleteCourier(loginCourier, password);
    }

}