package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.UserModel;
import ru.practicum.UserCreate;
import steps.LoginPage;
import steps.MainPage;
import steps.PasswordRecoveryPage;
import steps.RegistrationPage;
import ru.practicum.BrowserFactory;
import ru.practicum.TestDataGenerator;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private UserCreate userApiClient;
    private UserModel user;
    private String accessToken;
    public static final String MAIN = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER = MAIN + "/register";
    public static final String FORGET = MAIN + "/forgot-password";


    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver(); // будет использоваться браузер который мы указали в properties
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        userApiClient = new UserCreate();
        user = new UserModel(
                TestDataGenerator.genEmail(),
                TestDataGenerator.genPass(),
                TestDataGenerator.genName()
        );

        userApiClient.register(user);
        accessToken = userApiClient.getAccessToken(user);
    }

    @Test
    @DisplayName("Авторизация с кнопки на главной странице")
    @Description("Тестируем успешную авторизацию через кнопку на главной странице")
    public void loginMainButton() {
        driver.get(MAIN);
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Авторизация ОК", loginPage.isLoginOk());
    }

    @Test
    @DisplayName("Авторизация через личный кабинет")
    @Description("Тестируем успешную авторизацию через личный кабинет")
    public void loginAccountButton() {
        driver.get(MAIN);
        mainPage.clickMyAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Авторизация ОК", loginPage.isLoginOk());
    }

    @Test
    @DisplayName("Авторизация через форму регистрации")
    @Description("Тестируем успешную авторизацию через форму регистрации")
    public void loginRegistrationForm() {
        driver.get(REGISTER);
        registrationPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Авторизация ОК", loginPage.isLoginOk());
    }

    @Test
    @DisplayName("Авторизация через форму восстановления пароля")
    @Description("Тестируем успешную авторизацию через форму восстановления пароля")
    public void loginPasswordRecovery() {
        driver.get(FORGET);
        passwordRecoveryPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue("Авторизация ОК", loginPage.isLoginOk());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userApiClient.delete(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}