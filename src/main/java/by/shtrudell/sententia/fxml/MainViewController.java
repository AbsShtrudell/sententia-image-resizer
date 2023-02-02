package by.shtrudell.sententia.fxml;

import by.shtrudell.sententia.image.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.io.*;

public class MainViewController implements ClosableByEvent{
    @FXML
    private CheckBox dontMakeCopyCheckBox;
    @FXML
    private Label currentResLabel;
    @FXML
    private Label newResLabel;
    @FXML
    private CheckBox preserveRatioCheckBoc;
    @FXML
    private ChoiceBox<Resolution> resChoiceBox;
    @FXML
    private ImageView imageView;

    private final ImageEditor imageEditor;
    private final File imageFile;

    private BufferedImageAdaptor bufferedImage;
    private Resolution newResolution;

    private EventHandler<ActionEvent> closeEventHandler;

    public  MainViewController(File imageFile, ImageEditor imageEditor) {
        this.imageEditor = imageEditor;
        this.imageFile = imageFile;
    }

    @FXML
    private void initialize() {
        try {
            bufferedImage = new BufferedImageAdaptor(ImageIO.read(imageFile));
            newResolution = bufferedImage.getResolution();
            imageEditor.setBufferedImage(bufferedImage);
        } catch (IOException e) {
            Dialog.show("Fatal Error", Localization.getTranslation("main-view", "error_cant_read_file"), Alert.AlertType.ERROR);
            closeEventHandler.handle(new ActionEvent());
            return;
        }

        resChoiceBox.getItems().setAll(Resolution.staticResolutions);

        resChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            newResolution = newValue;
            updateNewResLabel();
        });

        preserveRatioCheckBoc.selectedProperty().addListener((v, oldValue, newValue) -> {
            updateNewResLabel();
        });

        preserveRatioCheckBoc.setSelected(true);

        setCurrentResLabel(bufferedImage.getResolution());
        updateNewResLabel();

        imageView.setImage(bufferedImage.getImage());
    }

    private void updateNewResLabel() {
        if(preserveRatioCheckBoc.isSelected())
            setNewResLabel(bufferedImage.getResolution(), newResolution, new PreserveRatioCalculator());
        else
            setNewResLabel(bufferedImage.getResolution(), newResolution, new StraightCalculator());
    }

    private void setCurrentResLabel(Resolution resolution) {
        setResLabelText(resolution, currentResLabel);
    }

    private void setNewResLabel(Resolution currentResolution, Resolution targetResolution, ResolutionCalculator calculatorMethod) {
        setResLabelText(calculatorMethod.calculate(currentResolution, targetResolution), newResLabel);
    }

    private void setResLabelText(Resolution resolution, Label label) {
        if(label == null || resolution == null) throw new NullPointerException();

        label.setText(String.format("%d x %d Pixels", resolution.getWidth(), resolution.getHeight()));
    }

    @FXML
    private void apply(ActionEvent actionEvent) {
        if(bufferedImage.getWidth() == newResolution.getWidth() && bufferedImage.getHeight() == newResolution.getHeight()) {
            Dialog.show("Warning Dialog", Localization.getTranslation("main-view", "warning_size_didnt_change"), Alert.AlertType.WARNING);
            return;
        }

        try {
            imageEditor.resize(newResolution.getWidth(), newResolution.getHeight(), dontMakeCopyCheckBox.isSelected(), preserveRatioCheckBoc.isSelected());
        } catch (IOException e) {
            Dialog.show("Fatal Error", Localization.getTranslation("main-view", "error_cant_write_file"), Alert.AlertType.ERROR);
            return;
        }

        Dialog.show("Information Dialog", Localization.getTranslation("main-view", "information_success"), Alert.AlertType.INFORMATION);

        closeEventHandler.handle(new ActionEvent());
    }

    @FXML
    private void cancel(ActionEvent actionEvent) {
        closeEventHandler.handle(new ActionEvent());
    }

    @FXML
    private void openSettings(ActionEvent actionEvent) {
        try {
            FXMLWindow<SettingsViewController> fxmlWindow = new FXMLWindow<>("settings-view", c -> {
                return new SettingsViewController();
            });
            fxmlWindow.show("Settings");
        } catch (WindowShowError e) {
            throw new RuntimeException(e);
        }
    }

    public void setCloseEventHandler(EventHandler<ActionEvent> closeEventHandler) {
        this.closeEventHandler = closeEventHandler;
    }
}