module by.shtrudell.sententia {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires de.jensd.fx.glyphs.fontawesome;
    requires net.coobird.thumbnailator;
    requires java.desktop;

    opens by.shtrudell.sententia to javafx.fxml;
    exports by.shtrudell.sententia;
    exports by.shtrudell.sententia.image;
    opens by.shtrudell.sententia.image to javafx.fxml;
    exports by.shtrudell.sententia.fxml;
    opens by.shtrudell.sententia.fxml to javafx.fxml;
}