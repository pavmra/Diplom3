package ru.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By poleEmail = By.xpath("//input[@name='name']");
    private final By polePassword = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerButton = By.xpath("//a[text()='Зарегистрироваться']");
    private final By findPasswordButton = By.xpath("//a[text()='Восстановить пароль']");
    private final By wrongPassword = By.xpath("//p[contains(@class, 'input__error')]");

    // Methods
    public void setEmail(String email) {
        driver.findElement(poleEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(polePassword).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }


    public String getErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(wrongPassword));
        return driver.findElement(wrongPassword).getText();
    }
    public boolean isLoginOk() { // проверяем что залогинились
        try {
                        new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("stellarburgers.nomoreparties.site"));

                        By placeOrderButton = By.xpath("//button[contains(text(), 'Оформить заказ')]");
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}