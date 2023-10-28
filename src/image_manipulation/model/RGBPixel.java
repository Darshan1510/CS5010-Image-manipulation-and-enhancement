package image_manipulation.model;

/**
 * Represents a concrete RGB pixel with individual red, green, and blue components.
 * <p>
 * This class implements the PixelModel interface, providing storage and retrieval mechanisms
 * for each RGB color component of a pixel.
 * <p>
 * RGB values are expected to be non-negative integers.
 * </p>
 */
public class RGBPixel implements PixelModel {

  /**
   * The red component of the pixel.
   */
  private final int r;

  /**
   * The green component of the pixel.
   */
  private final int g;

  /**
   * The blue component of the pixel.
   */
  private final int b;


  /**
   * Constructs a new Pixel with the specified RGB values.
   *
   * @param r The red component value.
   * @param g The green component value.
   * @param b The blue component value.
   * @throws IllegalArgumentException if any of the RGB values are negative.
   */
  public RGBPixel(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("r, g or b values cannot be negative.");
    }

    // TODO: Please write the condition to check the upper bound of the pixel too.
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public int getR() {
    return r;
  }

  @Override
  public int getG() {
    return g;
  }

  @Override
  public int getB() {
    return b;
  }
}
