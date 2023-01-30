module by.shtrudell.sententia {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires net.coobird.thumbnailator;

    opens by.shtrudell.sententia to javafx.fxml;
    exports by.shtrudell.sententia;
}