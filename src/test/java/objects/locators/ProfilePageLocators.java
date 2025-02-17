package objects.locators;
import org.openqa.selenium.By;

public class ProfilePageLocators {

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
}
