package UI_tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import objects.locators.MainPageLocators;
import objects.methods.MainPageMethods;
import objects.resource.BrowserSelection;
import objects.resource.ServerUrls;
import objects.resource.WebDriverFactory;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.equalTo;

@DisplayName("Checking the constructor (main page)")
public class MainPageTests {

    private WebDriver driver;
    private MainPageMethods mainMethods;
    private MainPageLocators mainLocators;
    private WebDriverFactory webDriverFactory;
    String browserName;

    @Before
    @Step("Launching the browser, preparing test data")
    public void begin() {
        browserName = BrowserSelection.getBrowserName();
        webDriverFactory = new WebDriverFactory();
        driver = WebDriverFactory.createDriver(BrowserSelection.getBrowserName());
        driver.get(ServerUrls.MAIN_PAGE);
        mainMethods = new MainPageMethods(driver);
        mainLocators = new MainPageLocators();
    }

    @After
    @Step("Closing the browser")
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Step("Clicking on the Buns tab")
    @DisplayName("Checking the operation of the Buns tab in the ingredients section")
    public void checkScrollToBunsTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainMethods.clickFillingsButton();
        mainMethods.clickBunsButton();
        mainMethods.scrollToElementAndWait(mainLocators.getBunsTypes());

        MatcherAssert.assertThat(
                "Списка 'Булки' не видно на странице",
                driver.findElement(mainLocators.getBunsTypes()).isDisplayed(),
                equalTo(true)
        );
    }

    @Test
    @Step("Clicking on the Sauces tab")
    @DisplayName("Checking the operation of the Sauces tab in the ingredients section")
    public void checkScrollToSaucesTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainMethods.clickSaucesButton();
        mainMethods.scrollToElementAndWait(mainLocators.getSaucesTypes());

        MatcherAssert.assertThat(
                "Списка 'Соусы' не видно на странице",
                driver.findElement(mainLocators.getSaucesTypes()).isDisplayed(),
                equalTo(true)
        );
    }

    @Test
    @Step("Clicking on the Toppings tab")
    @DisplayName("Checking the operation of the Toppings tab in the ingredients section")
    public void checkScrollToFillingsTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainMethods.clickFillingsButton();
        mainMethods.scrollToElementAndWait(mainLocators.getFillingsTypes());

        MatcherAssert.assertThat(
                "Списка 'Начинки' не видно на странице",
                driver.findElement(mainLocators.getFillingsTypes()).isDisplayed(),
                equalTo(true)
        );
    }

}
