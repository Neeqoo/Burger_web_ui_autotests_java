package resources.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {

    private final By header = By.tagName("h1");
    private final By inputsField = By.xpath(".//form[starts-with(@class, 'Auth_form')]//fieldset//div[@class='input__container']//input");
    private final By title = By.xpath(".//main//h2");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div/div[starts-with(@class, 'Modal_modal_overlay')]");
    private final By authButton = By.xpath(".//form[starts-with(@class, 'Auth_form')]/button");

    public By getHeader() {
        return header;
    }

    public By getInputsField() {
        return inputsField;
    }

    public By getTitle() {
        return title;
    }

    public By getModalOverlay() {
        return modalOverlay;
    }

    public By getAuthButton() {
        return authButton;
    }

    private WebDriver webDriver;

    public AuthPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Entering a value in the Email")
    public void setEmail(String email) {
        webDriver.findElements(getInputsField()).get(0).sendKeys(email);
    }

    @Step("Entering a value in the Password")
    public void setPassword(String password) {
        webDriver.findElements(getInputsField()).get(1).sendKeys(password);
    }

    @Step("Clicking on the authorization button")
    public void clickAuthButton() {
        waitButtonIsClickable();
        webDriver.findElement(getAuthButton()).click();
    }

    @Step("Wait button is clickable")
    private void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(getModalOverlay())));
    }

    @Step("Wait form submitted")
    public void waitFormSubmitted() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(getHeader()));
    }

    @Step("Wait auth form visible")
    public void waitAuthFormVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.textToBe(getTitle(), "Вход"));
    }

}
