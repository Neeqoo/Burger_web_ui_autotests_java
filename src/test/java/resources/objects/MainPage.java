package resources.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final By constructorButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Конструктор']");
    private final By orderFeedButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Лента заказов']");
    private final By profileButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Личный Кабинет']");
    private final By bunsButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Булки']");
    private final By saucesButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Соусы']");
    private final By fillingsButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Начинки']");
    private final By bunsTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Булки']");
    private final By saucesTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Соусы']");
    private final By fillingsTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Начинки']");
    private final By authButton = By.xpath(".//div[starts-with(@class,'BurgerConstructor_basket__container')]/button");
    private final By header = By.xpath(".//main//h1[text()='Соберите бургер']");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div/div[starts-with(@class, 'Modal_modal_overlay')]");

    public By getConstructorButton() {
        return constructorButton;
    }

    public By getOrderFeedButton() {
        return orderFeedButton;
    }

    public By getProfileButton() {
        return profileButton;
    }

    public By getBunsButton() {
        return bunsButton;
    }

    public By getSaucesButton() {
        return saucesButton;
    }

    public By getFillingsButton() {
        return fillingsButton;
    }

    public By getBunsTypes() {
        return bunsTypes;
    }

    public By getSaucesTypes() {
        return saucesTypes;
    }

    public By getFillingsTypes() {
        return fillingsTypes;
    }

    public By getAuthButton() {
        return authButton;
    }

    public By getHeader() {
        return header;
    }

    public By getModalOverlay() {
        return modalOverlay;
    }

    private WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Click auth button")
    public void clickAuthButton() {
        waitButtonIsClickable();
        webDriver.findElement(getAuthButton()).click();
    }

    @Step("Wait button is clickable")
    public void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(getModalOverlay())));
    }

    @Step("Wait header is visible")
    public void waitHeaderIsVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(getHeader()));
    }

    @Step("Scroll to element and wait")
    public void scrollToElementAndWait(By elementLocator) {
        WebElement element = new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

        new Actions(webDriver)
                .moveToElement(element)
                .perform();

        new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Click link to profile")
    public void clickLinkToProfile() {
        waitButtonIsClickable();
        webDriver.findElement(getProfileButton()).click();
    }

    @Step("Get auth button text")
    public String getAuthButtonText() {
        return webDriver.findElement(getAuthButton()).getText();
    }

    @Step("Click buns button")
    public void clickBunsButton() {
        waitButtonIsClickable();
        webDriver.findElement(getBunsButton()).click();
        scrollToElementAndWait(getBunsTypes());
    }

    @Step("Click sauces button")
    public void clickSaucesButton() {
        waitButtonIsClickable();
        webDriver.findElement(getSaucesButton()).click();
        scrollToElementAndWait(getSaucesTypes());
    }

    @Step("Click fillings button")
    public void clickFillingsButton() {
        waitButtonIsClickable();
        webDriver.findElement(getFillingsButton()).click();
        scrollToElementAndWait(getFillingsTypes());
    }


}
