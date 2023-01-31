package by.shtrudell.sententia.image;

import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConcreteImageEditor implements ImageEditor{
    private final byte[] fileData;
    private final String filePath;

    public ConcreteImageEditor(byte[] fileData, String filePath) {
        this.fileData = fileData;
        this.filePath = filePath;
    }

    protected InputStream getFileStream() {
        return new ByteArrayInputStream(fileData);
    }

    public void resize(int width, int height, boolean overwrite, boolean preserveRatio) throws IOException {
            Thumbnails.of(getFileStream()).size(width, height).keepAspectRatio(preserveRatio).toFile(filePath(overwrite));
    }

    protected String filePath(boolean overwrite) {
        if(overwrite)
            return filePath;
        else
            return filePath.substring(0, filePath.lastIndexOf('.')) +
                    "(resized)" +
                    filePath.substring(filePath.lastIndexOf('.'));
    }
}
