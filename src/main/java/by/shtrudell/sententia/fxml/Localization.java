package by.shtrudell.sententia.fxml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Localization {
    public static Locale locale;

    static {
        try (InputStream configInputStream = new FileInputStream("config.properties")) {
            Properties config = new Properties();
            config.load(configInputStream);
            locale = new Locale(config.getProperty("localization"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResourceBundle getLocalization(String fxmlName) {
        try {
            return ResourceBundle.getBundle(fxmlName, locale != null? locale : Locale.getDefault());
        }
        catch (MissingResourceException e) {
            return ResourceBundle.getBundle(fxmlName);
        }
    }

    public static String getTranslation(String fxmlName, String phraseName) throws MissingResourceException {
        return getLocalization(fxmlName).getString(phraseName);
    }

    public static void setLocale(Locale locale) {
        Localization.locale = locale;
    }
}
