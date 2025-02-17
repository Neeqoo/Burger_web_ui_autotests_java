package objects.resource;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
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
                String driverPath = "C:\\Users\\Nick\\Desktop\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\Nick\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
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
}
