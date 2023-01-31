@echo off
java --module-path ".\lib" --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web -jar ./bin/Sententia.jar %1