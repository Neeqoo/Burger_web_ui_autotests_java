package resources.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private final By constructorButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Конструктор']");
    private final By logoLink = By.xpath(".//div[starts-with(@class,'AppHeader_header__logo')]/a");
    private final By profileNavLink = By.xpath(".//a[contains(@class, 'Account_link_active')]");
    private final By logOutLink = By.xpath(".//nav[starts-with(@class, 'Account_nav')]/ul/li/button");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div/div[starts-with(@class, 'Modal_modal_overlay')]");

    public By getConstructorButton() {
        return constructorButton;
    }

    public By getLogoLink() {
        return logoLink;
    }

    public By getProfileNavLink() {
        return profileNavLink;
    }

    public By getLogOutLink() {
        return logOutLink;
    }

    public By getModalOverlay() {
        return modalOverlay;
    }

    private WebDriver webDriver;

    public ProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Wait auth form visible")
    public void waitAuthFormVisible() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(getProfileNavLink()));
    }

    @Step("Wait button is clickable")
    public void waitButtonIsClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(webDriver.findElement(getModalOverlay())));
    }

    @Step("Click constructor button")
    public void clickConstructorButton() {
        waitButtonIsClickable();
        webDriver.findElement(getConstructorButton()).click();
    }

    @Step("Click link on logo")
    public void clickLinkOnLogo() {
        waitButtonIsClickable();
        webDriver.findElement(getLogoLink()).click();
    }

    @Step("Click log out button")
    public void clickLogoutButton() {
        waitButtonIsClickable();
        webDriver.findElement(getLogOutLink()).click();
    }

}
