package image_manipulation.model;

/**
 * <h6>Represents a model for RGB pixel values.</h6>
 * <p>
 * This interface provides methods to retrieve individual RGB color components of a pixel.
 * Implementing classes should define how these RGB values are stored and retrieved.
 * </p>
 */
public interface PixelModel {

  /**
   * Retrieves the red component value of the pixel.
   *
   * @return An integer value between 0 and 255 representing the red component of the pixel.
   */
  int getR();

  /**
   * Retrieves the green component value of the pixel.
   *
   * @return An integer value between 0 and 255 representing the green component of the pixel.
   */
  int getG();

  /**
   * Retrieves the blue component value of the pixel.
   *
   * @return An integer value between 0 and 255 representing the blue component of the pixel.
   */
  int getB();

}
