package by.shtrudell.sententia.image;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ThumbnailatorImageEditor implements ImageEditor{
    private final File file;

    public ThumbnailatorImageEditor(File file) {
        this.file = file;
    }

    public void resize(int width, int height, boolean overwrite, boolean preserveRatio) throws IOException {
            Thumbnails.of(file).size(width, height).keepAspectRatio(preserveRatio).toFile(filePath(overwrite));
    }

    protected String filePath(boolean overwrite) {
        if(overwrite)
            return file.getAbsolutePath();
        else
            return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf('.')) +
                    "(resized)" +
                    file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'));
    }
}
