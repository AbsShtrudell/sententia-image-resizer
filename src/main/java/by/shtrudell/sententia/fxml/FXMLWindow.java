package by.shtrudell.sententia.fxml;

import by.shtrudell.sententia.SententiaApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;

public abstract class FXMLWindow<T> implements Window{
    private final String fxmlName;

    public FXMLWindow(String fxmlName, Callback<Class<T>, Object> controllerFactory) {
        this.fxmlName = fxmlName;
    }
    @Override
    public void show(String title) throws WindowShowError {
        FXMLLoader fxmlLoader = new FXMLLoader(SententiaApplication.class.getResource(String.format("fxml/%s.fxml", fxmlName)));

        fxmlLoader.setResources(Localization.getLocalization(fxmlName));

        Stage window = new Stage();

        fxmlLoader.setControllerFactory(c -> {
            SettingsViewController settingsViewController = new SettingsViewController();
            settingsViewController.setCloseEventHandler(e -> window.close());

            return settingsViewController;
        });

        Pane sceneRoot;
        try {
            sceneRoot = fxmlLoader.load();
        } catch (IOException e) {
            throw new WindowShowError(e.getMessage(), e.getCause());
        }

        Scene scene = new Scene(sceneRoot, sceneRoot.getPrefWidth(), sceneRoot.getPrefHeight());

        window.getIcons().add(new Image(Objects.requireNonNull(SententiaApplication.class.getResourceAsStream("app-icon.png"))));
        window.setResizable(false);
        window.setTitle("Settings");
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }
}
