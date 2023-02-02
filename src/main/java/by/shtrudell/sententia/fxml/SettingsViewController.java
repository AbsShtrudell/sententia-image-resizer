package by.shtrudell.sententia.fxml;

import by.shtrudell.sententia.SententiaApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

import java.io.*;
import java.util.Collections;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsViewController implements ClosableByEvent {
    @FXML
    private ChoiceBox<Locale> languageChoiceBox;

    private EventHandler<ActionEvent> closeEventHandler;

    private final Properties config = new Properties();

    @FXML
    private void initialize() {
        ResourceBundle languages = Localization.getLocalization("language-names");

        for(var language : Collections.list(languages.getKeys()))
            //noinspection deprecation
            languageChoiceBox.getItems().add(new Locale(language));

        try (InputStream configInputStream = new FileInputStream("config.properties")) {
            config.load(configInputStream);
            languageChoiceBox.getSelectionModel().select(new Locale(config.getProperty("localization")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        languageChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Locale locale) {
                if(locale == null) return null;
                return Localization.getTranslation("language-names", locale.getLanguage());
            }

            @Override
            public Locale fromString(String s) {
                return null;
            }
        });

        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            config.setProperty("localization", newValue.getLanguage());
            try {
                config.store(new FileOutputStream("config.properties"), "changed localization");
                Localization.setLocale(newValue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        closeEventHandler.handle(new ActionEvent());
    }

    public void setCloseEventHandler(EventHandler<ActionEvent> closeEventHandler) {
        this.closeEventHandler = closeEventHandler;
    }
}
