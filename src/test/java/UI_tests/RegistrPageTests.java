package UI_tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import net.andreinc.mockneat.MockNeat;
import objects.methods.*;
import objects.resource.BrowserSelection;
import objects.resource.ServerUrls;
import objects.resource.WebDriverFactory;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static net.andreinc.mockneat.types.enums.NameType.FIRST_NAME;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Registering a new user")
public class RegistrPageTests {

    private WebDriver driver;
    private RegistrationPageMethods regMethods;
    private String email;
    private String password;
    private String name;
    MockNeat random;
    private WebDriverFactory webDriverFactory;
    String browserName;

    @Before
    @Step("Launching the browser, preparing test data")
    public void begin() {
        random = MockNeat.threadLocal();
        browserName = BrowserSelection.getBrowserName();
        webDriverFactory = new WebDriverFactory();
        driver = WebDriverFactory.createDriver(BrowserSelection.getBrowserName());
        driver.get(ServerUrls.REGISTER_PAGE);
        regMethods = new RegistrationPageMethods(driver);

        email = random.emails().get();
        password = random.passwords().get();
        name = random.names().type(FIRST_NAME).get();
    }

    @After
    @Step("Closing the browser, clearing test data")
    public void tearDown() {
        driver.quit();
        new UserAPI().deleteUser(email, password);
    }

    @Test
    @DisplayName("Registering a new user with valid data")
    public void registerNewUserTest() {
        Allure.parameter("Browser", browserName + "  ");

        regMethods.setName(name);
        regMethods.setEmail(email);
        regMethods.setPassword(password);
        regMethods.clickRegisterButton();
        regMethods.waitFormSubmitted("Вход");
    }

    @Test
    @DisplayName("Register a new user with a short password (4 characters)")
    public void registerNewUserIncorrectPasswordTest() {
        Allure.parameter("Browser", browserName + "  ");

        regMethods.setName(name);
        regMethods.setEmail(email);
        regMethods.setPassword("1234");
        regMethods.clickRegisterButton();
        regMethods.waitErrorIsVisible();

        MatcherAssert.assertThat(
                "Некорректное сообщение об ошибке",
                regMethods.getErrorMessage(),
                equalTo("Некорректный пароль")
        );
    }

}
