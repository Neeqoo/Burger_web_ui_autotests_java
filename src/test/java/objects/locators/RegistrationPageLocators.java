package objects.locators;

import org.openqa.selenium.By;

public class RegistrationPageLocators {

    private final By inputsField = By.xpath(".//form[starts-with(@class, 'Auth_form')]//fieldset//div[@class='input__container']//input");
    private final By registerButton = By.xpath(".//form[starts-with(@class, 'Auth_form')]/button");
    private final By errorMessage = By.xpath(".//form[starts-with(@class, 'Auth_form')]//fieldset//div[@class='input__container']//p[starts-with(@class,'input__error')]");
    private final By title = By.xpath(".//main//h2");
    private final By authLink = By.xpath(".//a[starts-with(@class,'Auth_link')]");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div[starts-with(@class, 'Modal_modal')]");

    public By getInputsField() {
        return inputsField;
    }

    public By getRegisterButton() {
        return registerButton;
    }

    public By getErrorMessage() {
        return errorMessage;
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
}
