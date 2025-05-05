package resources.tools;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    /* В переменную browserName вписать необходимый браузер по выбору:  */
    /* "chrome" - Google Chrome */
    /* "yandex" - Yandex Browser  */

    private static final String browserName = "yandex";

    public static String getBrowserName() {
        return browserName;
    }

    private WebDriver driver;

    @Step("Start browser")
    public static ChromeDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                options.addArguments(
                        "--no-sandbox",
                        "--headless",
                        "--disable-dev-shm-usage",
                        "--disable-gpu"
                );
                WebDriverManager.chromedriver().driverVersion("133.0.6943.98")
                        .setup();
                return new ChromeDriver(options);
            }

            case "yandex": {
                String yandexBinaryPath = System.getenv("YANDEX_BROWSER_PATH");
                String driverPath = "C:\\Users\\Nick\\Desktop\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions options = new ChromeOptions();
                options.setBinary(yandexBinaryPath);
                options.addArguments(
                        "--no-sandbox",
                        "--headless",
                        "--disable-dev-shm-usage",
                        "--disable-gpu"
                );
                return new ChromeDriver(options);
            }

            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
    }


    @Step("Launching the browser and opening URL: {url}")
    public WebDriver startBrowser(String url) {
        driver = createDriver(getBrowserName());
        driver.get(url);
        return driver;
    }

    @Step("Closing the browser")
    public void stopBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
