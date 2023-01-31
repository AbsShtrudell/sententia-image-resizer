package by.shtrudell.sententia;

import by.shtrudell.sententia.image.ImageEditor;
import by.shtrudell.sententia.image.Resolution;
import by.shtrudell.sententia.image.ResolutionCalculator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
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

    private final ImageEditor imageEditor;
    private int width;
    private int height;
    private EventHandler<ActionEvent> closeEventHandler;
    private BufferedImage bufferedImage;

    MainViewController(ImageEditor imageEditor) {
        this.imageEditor = imageEditor;
    }

    @FXML
    private void initialize() {
        sizeChoiceBox.getItems().setAll(Resolution.staticResolutions);

        sizeChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            width = newValue.getWidth();
            height = newValue.getHeight();
        });

        preserveRatioCheckBoc.selectedProperty().addListener((v, oldValue, newValue) -> {

        });

        preserveRatioCheckBoc.setSelected(true);

        currentSizeValueLabel.setText(String.format("%.0f x %.0f Pixels", image.getWidth(), image.getHeight()));
        imageView.setImage(image);
        newSizeValueLabel.setText(String.format("%.0f x %.0f Pixels", (double)width, image.getHeight() * (double)width / image.getWidth()));
    }

    private void updateNewSizeValueLabelText() {
        if(preserveRatioCheckBoc.isSelected())
            setNewSizeValueLabelText(new Resolution(bufferedImage.getWidth(), bufferedImage.getHeight()), sizeChoiceBox.getValue(), (currentRes, targetRes) -> {
                return new
            });
        else
            setNewSizeValueLabelText();
    }

    private void setCurrentSizeValueLabelText(Resolution resolution) {
        setResLabelText(resolution, currentSizeValueLabel);
    }

    private void setNewSizeValueLabelText(Resolution currentResolution, Resolution targetResolution, ResolutionCalculator calculatorMethod) {
        setResLabelText(calculatorMethod.calculate(currentResolution, targetResolution), newSizeValueLabel);
    }

    private void setResLabelText(Resolution resolution, Label label) {
        if(label == null) throw new NullPointerException();

        label.setText(String.format("%d x %d Pixels", resolution.getWidth(), resolution.getHeight()));
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

    @FXML
    private void cancel(ActionEvent actionEvent) {
        closeEventHandler.handle(new ActionEvent());
    }

    public void setCloseEventHandler(EventHandler<ActionEvent> closeEventHandler) {
        this.closeEventHandler = closeEventHandler;
    }
}