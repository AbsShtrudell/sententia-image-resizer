package by.shtrudell.sententia;

import by.shtrudell.sententia.image.ConcreteImageEditor;
import by.shtrudell.sententia.image.ImageEditor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;

public class SententiaApplication extends Application {
    private static ImageEditor imageEditor;
    private static Image image;

    @Override
    public void start(Stage window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SententiaApplication.class.getResource("fxml/main-view.fxml"));
        fxmlLoader.setResources(Localization.getLocalization("main-view"));

        fxmlLoader.setControllerFactory(c -> {
            MainViewController controller = new MainViewController(image, imageEditor);
            controller.setCloseEventHandler(e -> window.close());

            return controller;
        });

        Pane sceneRoot = fxmlLoader.load();
        Scene scene = new Scene(sceneRoot, sceneRoot.getPrefWidth(), sceneRoot.getPrefHeight());

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

        byte[] imageData;

        try (InputStream inputstream = new FileInputStream(args[0])) {
            imageData = inputstream.readAllBytes();
        } catch (IOException e) {
            System.out.println("Wrong path received");
            System.exit(1);
            return;
        }

        imageEditor = new ConcreteImageEditor(imageData, args[0]);
        image = new Image(new ByteArrayInputStream(imageData));

        launch();
    }
}