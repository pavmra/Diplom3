package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By namePole = By.xpath("//label[contains(text(),'Имя')]/following-sibling::input");
    private final By emailPole = By.xpath("//label[contains(text(),'Email')]/following-sibling::input");
    private final By passwordPole = By.xpath("//input[@name='Пароль']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath("//a[text()='Войти']");
    private final By errorMessage = By.xpath("//p[contains(@class, 'input__error')]");

    @Step("Ввести имя на странице регистрации")
    public void setName(String name) {

        driver.findElement(namePole).sendKeys(name);
    }

    @Step("Ввести почту на странице регистрации")
    public void setEmail(String email) {

        driver.findElement(emailPole).sendKeys(email);
    }

    @Step("Ввести пароль на странице регистрации")
    public void setPassword(String password) {

        driver.findElement(passwordPole).sendKeys(password);
    }

    @Step("Нажать кнопку зарегистрироваться на странице регистрации")
    public void clickRegisterButton() {

        driver.findElement(registerButton).click();
    }

    @Step("Нажать кнопку Войти на странице регистрации")
    public void clickLoginLink() {

        driver.findElement(loginButton).click();
    }

    @Step("Получить текст ошибки")
    public String getErrorText() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }

    @Step("Зарегистрироваться")
    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
}