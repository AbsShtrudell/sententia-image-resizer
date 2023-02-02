package by.shtrudell.sententia.fxml;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class SettingsViewController {
    @FXML
    private ChoiceBox languageChoiceBox;

    private EventHandler<ActionEvent> closeEventHandler;

    @FXML
    private void close(ActionEvent actionEvent) {
    }

    public void setCloseEventHandler(EventHandler<ActionEvent> closeEventHandler) {
        this.closeEventHandler = closeEventHandler;
    }
}
