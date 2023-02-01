package by.shtrudell.sententia;

import by.shtrudell.sententia.image.ThumbnailatorImageEditor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;

public class SententiaApplication extends Application {
    private static File imageFile;

    @Override
    public void start(Stage window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SententiaApplication.class.getResource("fxml/main-view.fxml"));
        fxmlLoader.setResources(Localization.getLocalization("main-view"));

        fxmlLoader.setControllerFactory(c -> {
            MainViewController controller = new MainViewController(imageFile ,new ThumbnailatorImageEditor(imageFile));
            controller.setCloseEventHandler(e -> window.close());

            return controller;
        });

        Pane sceneRoot = fxmlLoader.load();
        Scene scene = new Scene(sceneRoot, sceneRoot.getPrefWidth(), sceneRoot.getPrefHeight());

        window.setOnCloseRequest(e -> System.exit(0));
        window.setResizable(false);
        window.setTitle("Sententia");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("Image path expected");
            System.exit(1);
            return;
        }

        imageFile = new File(args[0]);

        if(!imageFile.exists() || !imageFile.isFile()) {
            System.out.println("Wrong path received");
            System.exit(1);
            return;
        }

        launch();
    }
}