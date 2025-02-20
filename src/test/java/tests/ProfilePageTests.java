package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import net.andreinc.mockneat.MockNeat;
import resources.objects.AuthPage;
import resources.objects.MainPage;
import resources.objects.ProfilePage;
import resources.objects.UserAPI;
import resources.tools.ServerUrls;
import resources.tools.WebDriverFactory;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static net.andreinc.mockneat.types.enums.NameType.FIRST_NAME;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Checking the user's personal account")
public class ProfilePageTests {

    private WebDriver driver;
    private AuthPage authMethods;
    private MainPage mainMethods;
    private ProfilePage profileMethods;
    private String name;
    private String email;
    private String password;
    MockNeat random;
    private WebDriverFactory browser;
    String browserName;

    @Before
    @Step("Launching the browser, preparing test data")
    public void begin() {
        browser = new WebDriverFactory();
        driver = browser.startBrowser(ServerUrls.MAIN_PAGE);

        browserName = browser.getBrowserName();

        random = MockNeat.threadLocal();
        authMethods = new AuthPage(driver);
        mainMethods = new MainPage(driver);
        profileMethods = new ProfilePage(driver);

        email = random.emails().get();
        password = random.passwords().get();
        name = random.names().type(FIRST_NAME).get();

        new UserAPI().createUser(name, email, password);
    }

    @After
    @Step("Closing the browser, clearing test data")
    public void tearDown() {
        browser.stopBrowser();
        new UserAPI().deleteUser(email, password);
    }

    @Step("Authorization")
    private void authUser() {
        authMethods.setEmail(email);
        authMethods.setPassword(password);
        authMethods.clickAuthButton();
        authMethods.waitFormSubmitted();
    }

    @Step("Open a personal account")
    private void openPersonalAccount() {
        driver.get(ServerUrls.LOGIN_PAGE);
        authMethods.waitAuthFormVisible();
        authUser();
        mainMethods.clickLinkToProfile();
        profileMethods.waitAuthFormVisible();
    }

    @Test
    @DisplayName("Checking the click-through to the 'Personal Account'")
    @Description("Checking the click-through to the 'Personal Account'. We expect that the transition was completed successfully.")
    public void checkLinkToProfileTest() {
        Allure.parameter("Browser", browserName + "  ");

        openPersonalAccount();

        MatcherAssert.assertThat(
                "Некорректный URL страницы Личного кабинета",
                driver.getCurrentUrl(),
                containsString("/account/profile")
        );
    }

    @Test
    @DisplayName("Checking the transition from the personal account by clicking on the 'Constructor'")
    @Description("Checking the transition from the personal account by clicking on the 'Constructor'. We expect that the transition was completed successfully.")
    public void checkLinkToConstructorTest() {
        Allure.parameter("Browser", browserName + "  ");

        openPersonalAccount();
        profileMethods.clickConstructorButton();
        mainMethods.waitHeaderIsVisible();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainMethods.getAuthButtonText(),
                equalTo("Оформить заказ")
        );
    }

    @Test
    @DisplayName("Checking the transition from the personal account by clicking on the logo Stellar Burgers")
    @Description("Checking the transition from the personal account by clicking on the logo Stellar Burgers. We expect that the transition was completed successfully.")
    public void checkLinkOnLogoTest() {
        Allure.parameter("Browser", browserName + "  ");

        openPersonalAccount();
        profileMethods.clickLinkOnLogo();
        mainMethods.waitHeaderIsVisible();

        MatcherAssert.assertThat(
                "Текст на кнопке 'Войти в аккаунт' должен поменяться на 'Оформить заказ'",
                mainMethods.getAuthButtonText(),
                equalTo("Оформить заказ")
        );
    }

    @Test
    @DisplayName("Checking the exit from the personal account by clicking on the 'Log out' button")
    @Description("Checking the exit from the personal account by clicking on the 'Log out' button. We expect that the transition was completed successfully.")
    public void checkLinkLogOutTest() {
        Allure.parameter("Browser", browserName + "  ");

        openPersonalAccount();
        profileMethods.clickLogoutButton();
        authMethods.waitAuthFormVisible();

        MatcherAssert.assertThat(
                "Некорректный URL страницы Авторизации",
                driver.getCurrentUrl(),
                containsString("/login")
        );
    }

}
