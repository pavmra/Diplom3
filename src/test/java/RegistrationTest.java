import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.UserModel;
import ru.practicum.UserCreate;
import steps.LoginPage;
import steps.MainPage;
import steps.RegistrationPage;
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
    private UserCreate userApiClient;
    private UserModel user;
    private String accessToken;
    public static final String MAIN = "https://stellarburgers.nomoreparties.site";

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver(); // будет использоваться браузер который мы указали в properties
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        userApiClient = new UserCreate();
        driver.get(MAIN);
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Тестируем успешную регистрацию")
    public void testSuccessfulRegistration() {
        UserModel user = new UserModel(
                TestDataGenerator.genEmail(),
                TestDataGenerator.genPass(),
                TestDataGenerator.genName()
        );

        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains("login"));
        assertTrue("После регистрации отображается страница входа в систему",
                driver.getCurrentUrl().contains("login"));

        // Получаем токен для удаления пользователя
        accessToken = userApiClient.getAccessToken(user);
    }


    @Test
    @DisplayName("Регистрация с невалидным паролем")
    @Description("Тестируем регистрацию с паролем меньше 6 символов")
    public void testRegistrationWrongPassword() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        String shortPassword = "12345";
        registrationPage.registration(TestDataGenerator.genName(),TestDataGenerator.genEmail(),shortPassword);
        String errorMessage = registrationPage.getErrorText();
        assertEquals("Должно отображаться сообщение об ошибке",
                "Некорректный пароль", errorMessage);
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