package by.shtrudell.sententia.fxml;

public interface Window {
    void show() throws WindowShowError;

    void show(String title) throws WindowShowError;
}
