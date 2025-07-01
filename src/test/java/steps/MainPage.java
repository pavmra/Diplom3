package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By myAccountButton = By.xpath("//a[@href='/account']");
    private final By fillingsArea = By.xpath("//span[text()='Начинки']/..");
    private final By saucesArea = By.xpath("//span[text()='Соусы']/..");
    private final By bunsArea = By.xpath("//span[text()='Булки']/..");
    private final By choseArea = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span");

    @Step("Нажать кнопку войти в аккаунт на главной странице")
    public void clickLoginButton() {

        driver.findElement(loginButton).click();
    }
    @Step("Нажать кнопку Личный кабинет на главной странице")
    public void clickMyAccountButton() {

        driver.findElement(myAccountButton).click();
    }
    @Step("Выбрать зону Начинки")
    public void clickFillingsArea() {

        driver.findElement(fillingsArea).click();
    }
    @Step("Выбрать зону Булки")
    public void clickBunsArea() {

        driver.findElement(bunsArea).click();
    }
    @Step("Выбрать зону Соусы")
    public void clickSaucesArea() {

        driver.findElement(saucesArea).click();
    }

    @Step("Получить название активной зоны")
    public String getTextChoseArea() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(choseArea));
        return driver.findElement(choseArea).getText();
    }
}