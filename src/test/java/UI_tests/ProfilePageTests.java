package UI_tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import net.andreinc.mockneat.MockNeat;
import objects.methods.AuthPageMethods;
import objects.methods.MainPageMethods;
import objects.methods.ProfilePageMethods;
import objects.methods.UserAPI;
import objects.resource.BrowserSelection;
import objects.resource.ServerUrls;
import objects.resource.WebDriverFactory;
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
    private AuthPageMethods authMethods;
    private MainPageMethods mainMethods;
    private ProfilePageMethods profileMethods;
    private String name;
    private String email;
    private String password;
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
        driver.get(ServerUrls.MAIN_PAGE);

        authMethods = new AuthPageMethods(driver);
        mainMethods = new MainPageMethods(driver);
        profileMethods = new ProfilePageMethods(driver);

        email = random.emails().get();
        password = random.passwords().get();
        name = random.names().type(FIRST_NAME).get();

        new UserAPI().createUser(name, email, password);
    }

    @After
    @Step("Closing the browser, clearing test data")
    public void tearDown() {
        driver.quit();
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
