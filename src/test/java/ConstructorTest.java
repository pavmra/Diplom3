package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.MainPage;
import ru.practicum.BrowserFactory;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome"); // or "yandex"
        mainPage = new MainPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Выбор зоны булочек")
    @Description("Тестируем переход к зоне булочек в конструкторе")
    public void testSwitchToBunsSection() {

        String initialTab = mainPage.getTextChoseArea();
        if (!"Булки".equals(initialTab)) {
            mainPage.clickBunsArea();

        mainPage.clickSaucesArea();
        mainPage.clickBunsArea();

        String activeTab = mainPage.getTextChoseArea();
        assertEquals("Зона булочек должна быть выбрана", "Булки", activeTab);
    }
    }

    @Test
    @DisplayName("Выбор зоны соусов")
    @Description("Тестируем переход к зоне соусов в конструкторе")
    public void testSwitchToSaucesSection() {
        mainPage.clickSaucesArea();

        String activeTab = mainPage.getTextChoseArea();
        assertEquals("Зона соусов должна быть выбрана", "Соусы", activeTab);
    }

    @Test
    @DisplayName("Выбор зоны наполнителя")
    @Description("Тестируем переход к зоне наполнителя в конструкторе")
    public void testSwitchToFillingsSection() {
        mainPage.clickFillingsArea();

        String activeTab = mainPage.getTextChoseArea();
        assertEquals("Зона наполнителя должна быть выбрана", "Начинки", activeTab);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}