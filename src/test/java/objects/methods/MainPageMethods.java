package objects.methods;

import objects.locators.MainPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageMethods {

    private WebDriver webDriver;
    MainPageLocators locators = new MainPageLocators();


    public MainPageMethods (WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void clickAuthButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getAuthButton()).click();
    }
    public void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(locators.getModalOverlay())));
    }
    public void waitHeaderIsVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locators.getHeader()));
    }
    public void scrollToElementAndWait(By elementLocator) {
        WebElement element = new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

        new Actions(webDriver)
                .moveToElement(element)
                .perform();

        new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void clickConstructorButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getConstructorButton()).click();
    }


    public void clickOrderFeedButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getOrderFeedButton()).click();
    }


    public void clickLinkToProfile() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getProfileButton()).click();
    }

    public String getAuthButtonText() {
        return webDriver.findElement(locators.getAuthButton()).getText();
    }

    public void clickBunsButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getBunsButton()).click();
        scrollToElementAndWait(locators.getBunsTypes());
    }

    public void clickSaucesButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getSaucesButton()).click();
        scrollToElementAndWait(locators.getSaucesTypes());
    }

    public void clickFillingsButton() {
        waitButtonIsClickable();
        webDriver.findElement(locators.getFillingsButton()).click();
        scrollToElementAndWait(locators.getFillingsTypes());
    }


}
