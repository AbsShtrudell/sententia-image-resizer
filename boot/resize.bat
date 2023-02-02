@echo off
cd /d %~dp0
java --module-path .\lib --add-modules javafx.controls,javafx.fxml,javafx.swing,de.jensd.fx.glyphs.fontawesome -jar .\bin\sententia.jar %1