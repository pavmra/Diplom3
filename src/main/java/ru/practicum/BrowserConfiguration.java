package ru.practicum;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserConfiguration {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = BrowserConfiguration.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл сonfig.properties не найден");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Не удается загрузить config.properties");
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
}