package by.shtrudell.sententia.image;

import java.io.IOException;

public interface ImageEditor {
    BufferedImageAdaptor resize(int width, int height, boolean overwrite, boolean preserveRatio) throws IOException;
    void setBufferedImage(BufferedImageAdaptor bufferedImage);
}
