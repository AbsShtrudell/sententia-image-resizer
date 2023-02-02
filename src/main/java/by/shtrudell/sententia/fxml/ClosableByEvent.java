package by.shtrudell.sententia.fxml;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface ClosableByEvent {
    void setCloseEventHandler(EventHandler<ActionEvent> closeEventHandler);
}
