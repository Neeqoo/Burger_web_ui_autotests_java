package resources.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final By inputsField = By.xpath(".//form[starts-with(@class, 'Auth_form')]//fieldset//div[@class='input__container']//input");
    private final By registerButton = By.xpath(".//form[starts-with(@class, 'Auth_form')]/button");
    private final By errorMessages = By.xpath(".//form[starts-with(@class, 'Auth_form')]//fieldset//div[@class='input__container']//p[starts-with(@class,'input__error')]");
    private final By title = By.xpath(".//main//h2");
    private final By authLink = By.xpath(".//a[starts-with(@class,'Auth_link')]");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div[starts-with(@class, 'Modal_modal')]");

    public By getInputsField() {
        return inputsField;
    }

    public By getRegisterButton() {
        return registerButton;
    }

    public By getErrorMessages() {
        return errorMessages;
    }

    public By getTitle() {
        return title;
    }

    public By getAuthLink() {
        return authLink;
    }

    public By getModalOverlay() {
        return modalOverlay;
    }


    private final WebDriver webDriver;

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Set name")
    public void setName(String name) {
        webDriver.findElements(getInputsField()).get(0).sendKeys(name);
    }

    @Step("Set email")
    public void setEmail(String email) {
        webDriver.findElements(getInputsField()).get(1).sendKeys(email);
    }

    @Step("Set password")
    public void setPassword(String password) {
        webDriver.findElements(getInputsField()).get(2).sendKeys(password);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        waitButtonIsClickable();
        webDriver.findElement(getRegisterButton()).click();
    }

    @Step("Wait button is clickable")
    private void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(getModalOverlay())));
    }

    @Step("Wait form submitted")
    public void waitFormSubmitted(String expectedTitle) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(getTitle(), expectedTitle));
    }

    @Step("Wait error is visible")
    public void waitErrorIsVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(webDriver.findElement(getErrorMessages())));
    }

    @Step("Get error message")
    public String getErrorMessage() {
        return webDriver.findElement(getErrorMessages()).getText();
    }

    @Step("Click auth link")
    public void clickAuthLink() {
        waitButtonIsClickable();
        webDriver.findElement(getAuthLink()).click();
    }


}
