package by.shtrudell.sententia;

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

    public void resize(int width, int height, boolean overwrite) throws IOException {

        String destinationFile;

        if(overwrite)
            destinationFile = filePath;
        else
        {
            destinationFile = filePath.substring(0, filePath.lastIndexOf('.')) +
                    "(resized)" +
                    filePath.substring(filePath.lastIndexOf('.'));
        }

        Thumbnails.of(getFileStream()).size(width, height).toFile(destinationFile);
    }
}
