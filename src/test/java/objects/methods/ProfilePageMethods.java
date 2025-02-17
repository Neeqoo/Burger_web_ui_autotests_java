package objects.methods;

import objects.locators.ProfilePageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePageMethods {

    private WebDriver webDriver;
    ProfilePageLocators locators = new ProfilePageLocators();

    public ProfilePageMethods (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitAuthFormVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locators.getProfileNavLink()));
    }
    public void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(locators.getModalOverlay())));
    }
    public void clickConstructorButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getConstructorButton()).click();
    }
    public void clickLinkOnLogo() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getLogoLink()).click();
    }
    public void clickLogoutButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getLogOutLink()).click();
    }

}
