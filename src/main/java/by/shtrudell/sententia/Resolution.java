package by.shtrudell.sententia;

import java.util.*;

public class Resolution {
    public static final Resolution RES_640x480;
    public static final Resolution RES_800x600;
    public static final Resolution RES_1024x768;
    public static final Resolution RES_1280x720;
    public static final Resolution RES_1920x1080;
    public static final Resolution RES_3840x2160;
    public static final Resolution RES_7680x4320;

    public static final Collection<Resolution> staticResolutions;
    private final int width;
    private final int height;

    static
    {
        staticResolutions = new ArrayList<>();

        RES_640x480 = createStatic(640, 480);
        RES_800x600 = createStatic(800, 600);
        RES_1024x768 = createStatic(1024, 768);
        RES_1280x720 = createStatic(1280,720);
        RES_1920x1080 = createStatic(1920, 1080);
        RES_3840x2160 = createStatic(3840, 2160);
        RES_7680x4320 = createStatic(7680, 4320);
    }

    private static Resolution createStatic(int width, int height) {
        Resolution resolution = new Resolution(width, height);
        staticResolutions.add(resolution);
        return resolution;
    }

    Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("%dx%d",width, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resolution that = (Resolution) o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
