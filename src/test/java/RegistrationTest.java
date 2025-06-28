

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.LoginPage;
import ru.practicum.MainPage;
import ru.practicum.RegistrationPage;
import ru.practicum.BrowserFactory;
import ru.practicum.TestDataGenerator;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private String name;
    private String email;
    private String password;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome"); // or "yandex"
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);

        name = TestDataGenerator.genName();
        email = TestDataGenerator.genEmail();
        password = TestDataGenerator.genPass();

        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Тестируем успешную регистрацию")
    public void testSuccessfulRegistration() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();

        registrationPage.registration(name, email, password);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains("login"));

        assertTrue("После регистрации отображается страница входа в систему",
                driver.getCurrentUrl().contains("login"));
    }

    @Test
    @DisplayName("Регистрация с невалидным паролем")
    @Description("Тестируем регистрацию с паролем меньше 6 символов")
    public void testRegistrationWrongPassword() {
        mainPage.clickLoginButton();

        loginPage.clickRegisterButton();

        String shortPassword = "12345";
        registrationPage.registration(name, email, shortPassword);

        String errorMessage = registrationPage.getErrorText();
        assertEquals("Должно отображаться сообщение об ошибке",
                "Некорректный пароль", errorMessage);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}