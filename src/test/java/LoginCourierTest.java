import com.github.javafaker.Faker;
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

    LoginCourier loginData;
    String loginCourier;
    String password;

    CreateCourier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI= BaseUri.URI;
        courier = new CreateCourier();
        loginCourier = courier.getLogin();
        password = courier.getPassword();
        Steps.createCourier(courier);
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
        Faker faker = new Faker();
        String newLogin = faker.name().username();
        String newPassword = faker.internet().password();
        loginData  = new LoginCourier(newLogin, newPassword);

        Response response = Steps.loginCourier(loginData);
        response.then()
                .assertThat().statusCode(equalTo(404));
    }


    @After
    public void deleteCourier() {
        Steps.deleteCourier(loginCourier, password);
    }

}