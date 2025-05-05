package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import net.andreinc.mockneat.MockNeat;
import resources.objects.*;
import resources.tools.ServerUrls;
import resources.tools.WebDriverFactory;
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
    private RegistrationPage regPage;
    private String email;
    private String password;
    private String name;
    MockNeat random;
    private WebDriverFactory browser;
    String browserName;

    @Before
    @Step("Launching the browser, preparing test data")
    public void begin() {
        browser = new WebDriverFactory();
        driver = browser.startBrowser(ServerUrls.REGISTER_PAGE);

        browserName = browser.getBrowserName();

        regPage = new RegistrationPage(driver);
        random = MockNeat.threadLocal();

        email = random.emails().get();
        password = random.passwords().get();
        name = random.names().type(FIRST_NAME).get();
    }

    @After
    @Step("Closing the browser, clearing test data")
    public void tearDown() {
        browser.stopBrowser();
        new UserAPI().deleteUser(email, password);
    }

    @Test
    @DisplayName("Registering a new user with valid data")
    @Description("Registering a new user with valid data.We are waiting for the successful completion of registration.")
    public void registerNewUserTest() {
        Allure.parameter("Browser", browserName + "  ");

        regPage.setName(name);
        regPage.setEmail(email);
        regPage.setPassword(password);
        regPage.clickRegisterButton();
        regPage.waitFormSubmitted("Вход");

        MatcherAssert.assertThat(
                "После регистрации ожидался переход на страницу входа с заголовком 'Вход'",
                driver.findElement(regPage.getTitle()).getText(),
                equalTo("Вход")
        );
    }

    @Test
    @DisplayName("Register a new user with a short password (4 characters)")
    @Description("Register a new user with a short password (4 characters). We are waiting for the registration to fail.")
    public void registerNewUserIncorrectPasswordTest() {
        Allure.parameter("Browser", browserName + "  ");

        regPage.setName(name);
        regPage.setEmail(email);
        regPage.setPassword("1234");
        regPage.clickRegisterButton();
        regPage.waitErrorIsVisible();

        MatcherAssert.assertThat(
                "Некорректное сообщение об ошибке",
                regPage.getErrorMessage(),
                equalTo("Некорректный пароль")
        );
    }

}
