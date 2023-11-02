package image_manipulation.model.image;

import image_manipulation.enums.Component;

/**
 * The ImageModel interface represents an image and provides methods for image-related operations.
 */
public interface ImageModel {

    /**
     * Get the height of the image.
     *
     * @return The height of the image.
     */
    int getHeight();

    /**
     * Get the width of the image.
     *
     * @return The width of the image.
     */
    int getWidth();

    /**
     * Get the maximum pixel value in the image.
     *
     * @return The maximum pixel value.
     */
    int getMaxValue();

    /**
     * Get the pixel values at the specified position in the image.
     *
     * @param i The vertical position of the pixel.
     * @param j The horizontal position of the pixel.
     * @return The pixel values at the specified position.
     */
    PixelModel getPixelValues(int i, int j);

    /**
     * Get the pixel values of the entire image in a 2D array.
     *
     * @return The pixel values of the entire image.
     */
    RGBPixel[][] getPixels();

    /**
     * Create a new image by horizontally flipping the current image.
     *
     * @return A new image after horizontal flipping.
     */
    ImageModel horizontalFlip();

    /**
     * Create a new image by vertically flipping the current image.
     *
     * @return A new image after vertical flipping.
     */
    ImageModel verticalFlip();

    /**
     * Create a new image by brightening the current image with the specified increment value.
     *
     * @param increment The increment value for brightening the image.
     * @return A new image after brightening.
     */
    ImageModel brighten(int increment);

    /**
     * Apply a filter with the specified kernel to the current image, resulting in a modified image.
     * The kernel can be used for sharpening or blurring the image.
     *
     * @param kernel The filter kernel to apply to the image.
     * @return A new image after applying the filter.
     */
    ImageModel filter(double[][] kernel);

    /**
     * Apply a color transformation matrix to the current image. The transformation can be used for
     * grayscale or sepia effects.
     *
     * @param transformer The color transformation matrix to apply to the image.
     * @return A new image after applying the color transformation.
     */
    ImageModel colorTransform(double[][] transformer);

    /**
     * Convert the current image to grayscale based on the specified color component or color space.
     *
     * @param component The color component or color space for grayscale conversion.
     * @return A new grayscale image based on the selected component.
     */
    ImageModel grayscale(Component component);
}
