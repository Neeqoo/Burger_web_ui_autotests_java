package objects.methods;

import objects.locators.PasswordPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordPageMethods {

    PasswordPageLocators locators = new PasswordPageLocators();
    private WebDriver webDriver;

    public PasswordPageMethods(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void clickAuthLink() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getAuthLink()).click();
    }
    private void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(locators.getModalOverlay())));
    }

}
