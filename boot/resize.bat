@echo off
cd /d %~dp0
java --module-path .\lib --add-modules javafx.controls,javafx.fxml -jar .\bin\Sententia.jar %1