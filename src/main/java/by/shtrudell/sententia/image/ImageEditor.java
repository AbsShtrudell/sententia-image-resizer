package by.shtrudell.sententia.image;

import java.io.IOException;

public interface ImageEditor {
    void resize(int width, int height, boolean overwrite, boolean preserveRatio) throws IOException;
}
