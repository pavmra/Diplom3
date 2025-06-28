package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.LoginPage;
import ru.practicum.MainPage;
import ru.practicum.PasswordRecoveryPage;
import ru.practicum.RegistrationPage;
import ru.practicum.BrowserFactory;
import ru.practicum.TestDataGenerator;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private String email;
    private String password;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);

        email = TestDataGenerator.genEmail();
        password = TestDataGenerator.genPass();


        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registrationPage.registration(TestDataGenerator.genName(), email, password);
    }

    @Test
    @DisplayName("Авторизация с кнопки на главной странице")
    @Description("Тестируем успешную авторизацию с кнопки на главной странице")
    public void loginMainButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage.clickLoginButton();
        loginPage.login(email, password);

        assertTrue("Авторизация ОК", loginPage.isLoginOk());
    }

    @Test
    @DisplayName("Авторизация через личный кабинет")
    @Description("Тестируем успешную авторизацию через личный кабинет")
    public void loginAccountButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage.clickMyAccountButton();
        loginPage.login(email, password);

        assertTrue("Авторизация ОК", loginPage.isLoginOk());
    }

    @Test
    @DisplayName("Авторизация через форму регистрации")
    @Description("Тестируем успешную авторизацию через форму регистрации")
    public void loginRegistrationForm() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registrationPage.clickLoginLink();
        loginPage.login(email, password);

        assertTrue("Авторизация ОК", loginPage.isLoginOk());
    }

    @Test
    @DisplayName("Авторизация через форму восстановления пароля")
    @Description("Тестируем успешную авторизацию через форму восстановления пароля")
    public void loginPasswordRecovery() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        passwordRecoveryPage.clickLoginLink();
        loginPage.login(email, password);

        assertTrue("Авторизация ОК", loginPage.isLoginOk());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}