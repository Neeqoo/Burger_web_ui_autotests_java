package objects.locators;
import org.openqa.selenium.By;

public class PasswordPageLocators {

    private final By authLink = By.xpath(".//a[starts-with(@class,'Auth_link')]");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div[starts-with(@class, 'Modal_modal')]");

    public By getAuthLink() {
        return authLink;
    }

    public By getModalOverlay() {
        return modalOverlay;
    }
}
