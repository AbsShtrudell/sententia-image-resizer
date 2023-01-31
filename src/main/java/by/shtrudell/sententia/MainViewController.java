package by.shtrudell.sententia;

import by.shtrudell.sententia.image.ImageEditor;
import by.shtrudell.sententia.image.Resolution;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public class MainViewController {
    @FXML
    private CheckBox dontMakeCopyCheckBox;
    @FXML
    private Label currentSizeValueLabel;
    @FXML
    private Label newSizeValueLabel;
    @FXML
    private CheckBox preserveRatioCheckBoc;
    @FXML
    private ChoiceBox<Resolution> sizeChoiceBox;
    @FXML
    private ImageView imageView;
    private final Image image;
    private final ImageEditor imageEditor;
    private int width;
    private int height;
    private EventHandler<ActionEvent> closeEventHandler;
    MainViewController(Image image, ImageEditor imageEditor) {
        this.image = image;
        this.imageEditor = imageEditor;

        this.width = (int)image.getWidth();
        this.height = (int)image.getHeight();
    }

    @FXML
    private void initialize() {
        sizeChoiceBox.getItems().setAll(Resolution.staticResolutions);

        sizeChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            width = newValue.getWidth();
            height = newValue.getHeight();
            newSizeValueLabel.setText(String.format("%.0f x %.0f Pixels", (double)newValue.getWidth(), image.getHeight() * (double) newValue.getWidth() / image.getWidth()));
        });

        preserveRatioCheckBoc.selectedProperty().addListener((v, oldValue, newValue) -> {

        });

        preserveRatioCheckBoc.setSelected(true);

        currentSizeValueLabel.setText(String.format("%.0f x %.0f Pixels", image.getWidth(), image.getHeight()));
        imageView.setImage(image);
        newSizeValueLabel.setText(String.format("%.0f x %.0f Pixels", (double)width, image.getHeight() * (double)width / image.getWidth()));
    }

    @FXML
    private void cancel(ActionEvent actionEvent) {
        closeEventHandler.handle(new ActionEvent());
    }

    @FXML
    private void apply(ActionEvent actionEvent) {
        if(image.getWidth() == width && image.getHeight() == height) {
            Dialog.show("Warning Dialog", Localization.getTranslation("main-view", "warning_size_didnt_change"), Alert.AlertType.WARNING);
            return;
        }

        try {
            imageEditor.resize(width, height, dontMakeCopyCheckBox.isSelected(), preserveRatioCheckBoc.isSelected());
        } catch (IOException e) {
            Dialog.show("Warning Dialog", Localization.getTranslation("main-view", "error_cant_write_file"), Alert.AlertType.ERROR);
            return;
        }

        Dialog.show("Information Dialog", Localization.getTranslation("main-view", "information_success"), Alert.AlertType.INFORMATION);

        closeEventHandler.handle(new ActionEvent());
    }

    public void setCloseEventHandler(EventHandler<ActionEvent> closeEventHandler) {
        this.closeEventHandler = closeEventHandler;
    }
}