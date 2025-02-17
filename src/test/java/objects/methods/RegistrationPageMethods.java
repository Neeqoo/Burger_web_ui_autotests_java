package objects.methods;

import objects.locators.RegistrationPageLocators;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPageMethods {

    private final WebDriver webDriver;
    RegistrationPageLocators locators = new RegistrationPageLocators();

    public RegistrationPageMethods(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @Step("Ввод значения в поле 'Имя'")
    public void setName(String name) {
        webDriver.findElements(locators.getInputsField()).get(0).sendKeys(name);
    }
    @Step("Ввод значения в поле 'Email'")
    public void setEmail(String email) {
        webDriver.findElements(locators.getInputsField()).get(1).sendKeys(email);
    }
    @Step("Ввод значения в поле 'Пароль'")
    public void setPassword(String password) {
        webDriver.findElements(locators.getInputsField()).get(2).sendKeys(password);
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegisterButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getRegisterButton()).click();
    }

    private void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(locators.getModalOverlay())));
    }
    public void waitFormSubmitted(String expectedTitle) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(locators.getTitle(), expectedTitle));
    }
    public void waitErrorIsVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(webDriver.findElement(locators.getErrorMessage())));
    }
    public String getErrorMessage() {
        return webDriver.findElement(locators.getErrorMessage()).getText();
    }
    public void clickAuthLink() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getAuthLink()).click();
    }


}
