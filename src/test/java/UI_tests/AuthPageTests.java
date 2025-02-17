package UI_tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import objects.methods.*;
import objects.resource.BrowserSelection;
import objects.resource.ServerUrls;
import objects.resource.WebDriverFactory;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import net.andreinc.mockneat.MockNeat;

import static net.andreinc.mockneat.types.enums.NameType.FIRST_NAME;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("User authorization with")
public class AuthPageTests {

    private WebDriver driver;
    private WebDriverFactory webDriverFactory;
    private AuthPageMethods authMethods;
    private MainPageMethods mainMethods;
    private RegistrationPageMethods regMethods;
    private PasswordPageMethods pwdMethods;
    private String email;
    private String password;
    private String name;
    MockNeat random;
    String browserName;

    @Before
    @Step("Launching the browser, preparing test data")
    public void begin() {
        random = MockNeat.threadLocal();
        browserName = BrowserSelection.getBrowserName();
        webDriverFactory = new WebDriverFactory();
        driver = WebDriverFactory.createDriver(BrowserSelection.getBrowserName());
        driver.get(ServerUrls.MAIN_PAGE);

        authMethods = new AuthPageMethods(driver);
        mainMethods = new MainPageMethods(driver);
        regMethods = new RegistrationPageMethods(driver);
        pwdMethods = new PasswordPageMethods(driver);

        email = random.emails().get();
        password = random.passwords().get();
        name = random.names().type(FIRST_NAME).get();

        new UserAPI().createUser(name, email, password);
    }

    @After
    @Step("Closing the browser")
    public void tearDown() {
        driver.quit();
        new UserAPI().deleteUser(email, password);
    }

    @Step("User authorization")
    private void authUser() {
        authMethods.setEmail(email);
        authMethods.setPassword(password);
        authMethods.clickAuthButton();
        authMethods.waitFormSubmitted();
    }

    @Test
    @DisplayName("Log in by clicking on the 'Log in account' button on the main page")
    public void authFromMainPageButtonTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainMethods.clickAuthButton();
        authMethods.waitAuthFormVisible();
        authUser();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainMethods.getAuthButtonText(),
                equalTo("Оформить заказ")
        );


    }

    @Test
    @DisplayName("Login by clicking on the 'Personal Account' button in the page header")
    public void authFromProfileButtonTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainMethods.clickLinkToProfile();
        authMethods.waitAuthFormVisible();
        authUser();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен измениться на 'Оформить заказ'",
                mainMethods.getAuthButtonText(),
                equalTo("Оформить заказ")
        );

    }

    @Test
    @DisplayName("Log in using the password recovery form")
    public void authLinkFromForgotPasswordFormTest() {
        Allure.parameter("Browser", browserName + "  ");

        driver.get(ServerUrls.FORGOT_PASSWORD);
        regMethods.clickAuthLink();
        authMethods.waitAuthFormVisible();
        authUser();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainMethods.getAuthButtonText(),
                equalTo("Оформить заказ")
        );

    }


}
