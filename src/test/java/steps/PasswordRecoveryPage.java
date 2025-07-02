package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {

        this.driver = driver;
    }

    private final By loginLink = By.xpath("//a[text()='Войти']");

    @Step("Нажать кнопку войти на странице восстановления пароля")
    public void clickLoginLink() {

        driver.findElement(loginLink).click();
    }
}