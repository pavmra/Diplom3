package ru.practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {
    private static final String YANDEX_BROWSER =
            "C:/Users/Redmi/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";

    public static WebDriver getDriver() {
        String browser = BrowserConfiguration.getBrowser();
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    return setupChrome();
                case "yandex":
                    return setupYandex();
                default:
                    throw new IllegalArgumentException("Неподдерживаемый браузер " + browser);
            }
        } catch (Exception e) {
            throw new RuntimeException("Не инициализируется " + browser, e);
        }
    }

    private static WebDriver setupChrome() {

        String chromeVersion = "138.0.7204.50";

        WebDriverManager.chromedriver()
                .driverVersion(chromeVersion)
                .setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);


}

    private static WebDriver setupYandex() {

        System.setProperty("webdriver.chrome.driver", "D:\\yandexdriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.setBinary("C:/Users/Redmi/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");

        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");

        return new ChromeDriver(options);
    }
}