package ru.practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {
    private static final String YANDEX_BROWSER =
            "C:/Users/Redmi/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";

    public static WebDriver getDriver(String browser) {
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
        // Автоматически подбирает правильную версию ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");

        return new ChromeDriver(options);
    }

    private static WebDriver setupYandex() {
        // Для Yandex используем ChromeDriver, но с указанием пути к Yandex браузеру
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary(YANDEX_BROWSER);
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }
}