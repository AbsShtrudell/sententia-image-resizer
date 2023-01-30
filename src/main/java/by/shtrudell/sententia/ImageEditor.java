package by.shtrudell.sententia;

import java.io.IOException;

public interface ImageEditor {
    void resize(int width, int height, boolean overwrite) throws IOException;
}
