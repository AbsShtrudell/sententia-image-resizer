package by.shtrudell.sententia.image;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ThumbnailatorImageEditor implements ImageEditor{
    private final File file;
    private BufferedImageAdaptor bufferedImageAdaptor;
    public ThumbnailatorImageEditor(File file) {
        this.file = file;
        this.bufferedImageAdaptor = null;
    }

    public BufferedImageAdaptor resize(int width, int height, boolean overwrite, boolean preserveRatio) throws IOException {
        if(bufferedImageAdaptor == null || bufferedImageAdaptor.getBufferedImage() == null)
            Thumbnails.of(file).size(width, height).keepAspectRatio(preserveRatio).toFile(filePath(overwrite));
        else
            Thumbnails.of(bufferedImageAdaptor.getBufferedImage()).size(width, height).keepAspectRatio(preserveRatio).toFile(filePath(overwrite));

        return null;
    }

    @Override
    public void setBufferedImage(BufferedImageAdaptor bufferedImage) {
        this.bufferedImageAdaptor = bufferedImage;
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
