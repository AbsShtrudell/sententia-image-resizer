package by.shtrudell.sententia;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Localization {
    public static ResourceBundle getLocalization(String fxmlName) {
        try {
            return ResourceBundle.getBundle(fxmlName, Locale.getDefault());
        }
        catch (MissingResourceException e) {
            return ResourceBundle.getBundle(fxmlName, Locale.ENGLISH);
        }
    }

    public static String getTranslation(String fxmlName, String phraseName) throws MissingResourceException {
        return getLocalization(fxmlName).getString(phraseName);
    }
}
