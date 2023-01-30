package by.shtrudell.sententia;

import javafx.scene.control.Alert;

public class Dialog {
    public static void show(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);

        alert.showAndWait();
    }
}
