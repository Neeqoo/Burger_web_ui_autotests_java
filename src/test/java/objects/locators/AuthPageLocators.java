package objects.locators;

import org.openqa.selenium.By;

public class AuthPageLocators {

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
}
