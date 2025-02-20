package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import resources.objects.*;
import resources.tools.ServerUrls;
import resources.tools.WebDriverFactory;
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
    private WebDriverFactory browser;
    private AuthPage authPage;
    private MainPage mainPage;
    private RegistrationPage regPage;
    private String email;
    private String password;
    private String name;
    MockNeat random;
    String browserName;

    @Before
    @Step("Launching the browser, preparing test data")
    public void begin() {
        browser = new WebDriverFactory();
        driver = browser.startBrowser(ServerUrls.MAIN_PAGE);

        browserName = browser.getBrowserName();

        random = MockNeat.threadLocal();

        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        regPage = new RegistrationPage(driver);

        email = random.emails().get();
        password = random.passwords().get();
        name = random.names().type(FIRST_NAME).get();

        new UserAPI().createUser(name, email, password);
    }

    @After
    @Step("Closing the browser and delete test user")
    public void tearDown() {
        browser.stopBrowser();
        new UserAPI().deleteUser(email, password);
    }

    @Step("User authorization")
    private void authUser() {
        authPage.setEmail(email);
        authPage.setPassword(password);
        authPage.clickAuthButton();
        authPage.waitFormSubmitted();
    }

    @Test
    @DisplayName("Log in by clicking on the 'Log in account' button on the main page")
    @Description("Successful authorization via the 'login' button on the main page. We are waiting for the text on the 'login' button to change to the 'place an order' text")
    public void authFromMainPageButtonTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainPage.clickAuthButton();
        authPage.waitAuthFormVisible();
        authUser();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ")
        );


    }

    @Test
    @DisplayName("Login by clicking on the 'Personal Account' button in the page header")
    @Description("Login by clicking on the 'Personal Account' button in the page header. We are waiting for the text on the 'log in' button to change to the 'place an order' text")
    public void authFromProfileButtonTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainPage.clickLinkToProfile();
        authPage.waitAuthFormVisible();
        authUser();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен измениться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ")
        );

    }

    @Test
    @DisplayName("Log in using the password recovery form")
    @Description("Log in using the password recovery form. The text on the 'Log in account' button should change to 'Place an order'")
    public void authLinkFromForgotPasswordFormTest() {
        Allure.parameter("Browser", browserName + "  ");

        driver.get(ServerUrls.FORGOT_PASSWORD);
        regPage.clickAuthLink();
        authPage.waitAuthFormVisible();
        authUser();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ")
        );

    }

    @Test
    @DisplayName("Log in by clicking on the 'Log in account' in the registration form")
    @Description("Successful authorization via the 'login' button in the registration form. We are waiting for the text on the 'login' button to change to the 'place an order' text")
    public void authFromRegistrationPageButtonTest() {
        Allure.parameter("Browser", browserName + "  ");

        driver.get(ServerUrls.REGISTER_PAGE);
        regPage.clickAuthLink();
        authPage.waitAuthFormVisible();
        authUser();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainPage.getAuthButtonText(),
                equalTo("Оформить заказ")
        );


    }


}
