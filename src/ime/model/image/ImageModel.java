package ime.model.image;

import java.util.function.Function;

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
   * Apply a filter with the specified kernel to the current image, resulting in a modified image.
   * The kernel can be used for sharpening or blurring the image.
   *
   * @param kernel The filter kernel to apply to the image.
   * @return A new image after applying the filter.
   */
  ImageModel filter(double[][] kernel);

  /**
   * Convert the current image to specified transformationFunction based on the specified transform
   * function.
   *
   * @param transformFunction The transformationFunction to apply on the pixels.
   * @return A new greyscale image based on the provided transformation.
   */
  ImageModel applyTransform(Function<RGBPixel, RGBPixel> transformFunction);
}
