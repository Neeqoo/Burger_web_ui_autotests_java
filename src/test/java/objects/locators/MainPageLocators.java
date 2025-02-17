package objects.locators;

import org.openqa.selenium.By;

public class MainPageLocators {

    private final By constructorButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Конструктор']");
    private final By orderFeedButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Лента заказов']");
    private final By profileButton = By.xpath(".//p[starts-with(@class,'AppHeader_header__linkText') and text()='Личный Кабинет']");
    private final By bunsButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Булки']");
    private final By saucesButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Соусы']");
    private final By fillingsButton = By.xpath(".//section[starts-with(@class, 'BurgerIngredients_ingredients')]/div/div/span[text()='Начинки']");
    private final By bunsTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Булки']");
    private final By saucesTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Соусы']");
    private final By fillingsTypes = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer')]//h2[text()='Начинки']");
    private final By authButton = By.xpath(".//div[starts-with(@class,'BurgerConstructor_basket__container')]/button");
    private final By header = By.xpath(".//main//h1[text()='Соберите бургер']");
    private final By modalOverlay = By.xpath(".//div[starts-with(@class, 'App_App')]/div/div[starts-with(@class, 'Modal_modal_overlay')]");

    public By getConstructorButton() {
        return constructorButton;
    }

    public By getOrderFeedButton() {
        return orderFeedButton;
    }

    public By getProfileButton() {
        return profileButton;
    }

    public By getBunsButton() {
        return bunsButton;
    }

    public By getSaucesButton() {
        return saucesButton;
    }

    public By getFillingsButton() {
        return fillingsButton;
    }

    public By getBunsTypes() {
        return bunsTypes;
    }

    public By getSaucesTypes() {
        return saucesTypes;
    }

    public By getFillingsTypes() {
        return fillingsTypes;
    }

    public By getAuthButton() {
        return authButton;
    }

    public By getHeader() {
        return header;
    }

    public By getModalOverlay() {
        return modalOverlay;
    }
}
