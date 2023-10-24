package scooter;

import com.github.javafaker.Faker;

public class CreateCourier {

    private String login;
    private String password;
    private String firstName;
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateCourier() {
        Faker faker = new Faker();

        this.login = faker.name().username();
        this.password = faker.internet().password();
        this.firstName = faker.name().name();
    }

    //public CreateCourier() {
   // }

}