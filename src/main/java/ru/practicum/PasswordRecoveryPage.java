package ru.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginLink = By.xpath("//a[text()='Войти']");

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}