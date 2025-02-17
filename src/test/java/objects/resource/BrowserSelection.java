package objects.resource;

/* В переменную browserName вписать необходимый браузер по выбору:  */
/* "chrome" - Google Chrome */
/* "yandex" - Yandex Browser  */

public class BrowserSelection {

    private static final String browserName = "yandex";

    public static String getBrowserName() {
        return browserName;
    }
}
