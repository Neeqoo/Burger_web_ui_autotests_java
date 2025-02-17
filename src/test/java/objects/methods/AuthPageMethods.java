package objects.methods;

import io.qameta.allure.Step;
import objects.locators.AuthPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPageMethods {

    private WebDriver webDriver;
    AuthPageLocators locators = new AuthPageLocators();

    public AuthPageMethods(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Entering a value in the Email")
    public void setEmail(String email) {
        webDriver.findElements(locators.getInputsField()).get(0).sendKeys(email);
    }
    @Step("Entering a value in the Password")
    public void setPassword(String password) {
        webDriver.findElements(locators.getInputsField()).get(1).sendKeys(password);
    }

    @Step("Clicking on the authorization button")
    public void clickAuthButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getAuthButton()).click();
    }
    private void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(locators.getModalOverlay())));
    }
    public void waitFormSubmitted() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locators.getHeader()));
    }
    public void waitAuthFormVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.textToBe(locators.getTitle(), "Вход"));
    }

}
