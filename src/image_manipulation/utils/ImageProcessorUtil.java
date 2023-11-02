package image_manipulation.utils;

public class ImageProcessorUtil {

    // Sepia transformation matrix
    public static final double[][] SEPIA_TRANSFORMER = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };

    // Sharpening convolution kernel
    public static final double[][] SHARPEN_KERNEL = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125,},
            {-0.125, 0.25, 0.25, 0.25, -0.125,},
            {-0.125, 0.25, 1.00, 0.25, -0.125,},
            {-0.125, 0.25, 0.25, 0.25, -0.125,},
            {-0.125, -0.125, -0.125, -0.125, -0.125,}};


    // A 3x3 kernel used for blurring the image
    public static final double[][] BLUR_KERNEL = new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
}
