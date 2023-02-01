package by.shtrudell.sententia.image;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class BufferedImageAdaptor {
    private final BufferedImage bufferedImage;

    public BufferedImageAdaptor(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public Image getImage() {
        return convertToFxImage(bufferedImage);
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public Resolution getResolution() {
        return new Resolution(bufferedImage.getWidth(), bufferedImage.getHeight());
    }

    public static Image convertToFxImage(BufferedImage image) {
        return SwingFXUtils.toFXImage(image, null);
    }
}
