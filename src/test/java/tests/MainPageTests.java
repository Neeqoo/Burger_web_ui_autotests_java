package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import resources.objects.MainPage;

import resources.tools.ServerUrls;
import resources.tools.WebDriverFactory;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.equalTo;

@DisplayName("Checking the constructor (main page)")
public class MainPageTests {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverFactory browser;
    String browserName;


    @Before
    @Step("Launching the browser, preparing test data")
    public void begin() {
        browser = new WebDriverFactory();
        driver = browser.startBrowser(ServerUrls.MAIN_PAGE);
        browserName = browser.getBrowserName();
        mainPage = new MainPage(driver);
    }

    @After
    @Step("Closing the browser")
    public void tearDown() {
        browser.stopBrowser();
    }

    @Test
    @Step("Clicking on the Buns tab")
    @DisplayName("Checking the operation of the Buns tab in the ingredients section")
    @Description("Checking the operation of the 'Buns' tab in the 'Ingredients' section. We expect that the tab is working successfully.")
    public void checkScrollToBunsTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        mainPage.scrollToElementAndWait(mainPage.getBunsTypes());

        MatcherAssert.assertThat(
                "Списка 'Булки' не видно на странице",
                driver.findElement(mainPage.getBunsTypes()).isDisplayed(),
                equalTo(true)
        );
    }

    @Test
    @Step("Clicking on the Sauces tab")
    @DisplayName("Checking the operation of the Sauces tab in the ingredients section")
    @Description("Checking the operation of the Sauces tab in the ingredients section. We expect that the tab is working successfully.")
    public void checkScrollToSaucesTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainPage.clickSaucesButton();
        mainPage.scrollToElementAndWait(mainPage.getSaucesTypes());

        MatcherAssert.assertThat(
                "Списка 'Соусы' не видно на странице",
                driver.findElement(mainPage.getSaucesTypes()).isDisplayed(),
                equalTo(true)
        );
    }

    @Test
    @Step("Clicking on the Toppings tab")
    @DisplayName("Checking the operation of the Toppings tab in the ingredients section")
    @Description("Checking the operation of the Toppings tab in the ingredients section. We expect that the tab is working successfully.")
    public void checkScrollToFillingsTest() {
        Allure.parameter("Browser", browserName + "  ");

        mainPage.clickFillingsButton();
        mainPage.scrollToElementAndWait(mainPage.getFillingsTypes());

        MatcherAssert.assertThat(
                "Списка 'Начинки' не видно на странице",
                driver.findElement(mainPage.getFillingsTypes()).isDisplayed(),
                equalTo(true)
        );
    }

}
