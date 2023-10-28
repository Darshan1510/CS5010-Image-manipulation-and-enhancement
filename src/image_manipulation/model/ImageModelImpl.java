package image_manipulation.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import image_manipulation.model.enums.Component;

public class ImageModelImpl implements ImageModel {
  private RGBPixel[][] pixels;

  public ImageModelImpl(RGBPixel[][] pixels) {
    if (pixels == null || pixels.length == 0 || pixels[0].length == 0) {
      throw new IllegalArgumentException("pixels cannot be null or length of the " +
              "pixels must not be zero.");
    }
    this.pixels = pixels;

  }

  @Override
  public ImageModel horizontalFlip() {

    int height = this.pixels.length;
    int width = this.pixels[0].length;
    RGBPixel[][] processedImage = new RGBPixel[width][height];
//    for(int row = 0; row < height; row++)
//    {
//      for(int col = 0; col < width/2; col++)
//      { // swap two symmetric pixels
//        Pixel tmp = imageArr[row][col];
//        imageArr[row][col] = imageArr[row][width-col-1];
//        imageArr[row][width-col-1] = tmp;
//      }
//    }
    return null;
  }

  @Override
  public ImageModel verticalFlip() {
    return null;
  }

  @Override
  public ImageModel filter(double[][] kernel) {
    return null;
  }

  @Override
  public ImageModel colorTransform(double[][] matrix) {
    return null;
  }

  @Override
  public ImageModel colorRepresentation(Component component) {
    int height = this.pixels.length;
    int width = this.pixels[0].length;
    RGBPixel[][] result = new RGBPixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGBPixel pixel = this.pixels[y][x];
        RGBPixel newPixel = null;

        switch (component) {
          case RED:
            newPixel = new RGBPixel(pixel.getR(), 0, 0);
            break;
          case GREEN:
            newPixel = new RGBPixel(0, pixel.getG(), 0);
            break;
          case BLUE:
            newPixel = new RGBPixel(0, 0, pixel.getB());
            break;
          case VALUE:
            int maxPixelVal = Math.max(pixel.getR(), Math.max(pixel.getB(), pixel.getG()));
            newPixel = new RGBPixel(maxPixelVal, maxPixelVal, maxPixelVal);
            break;
          case INTENSITY:
            int avgPixelVal = pixel.getB() + pixel.getG() + pixel.getR() / 3;
            newPixel = new RGBPixel(avgPixelVal, avgPixelVal, avgPixelVal);
            break;
          case LUMA:
            int weightedSumPixelVal = (int) (0.2126 * pixel.getR() + 0.7152 * pixel.getG()
                    + 0.0722 * pixel.getB());
            newPixel = new RGBPixel(weightedSumPixelVal, weightedSumPixelVal, weightedSumPixelVal);
            break;
          default:
            throw new IllegalArgumentException("Invalid component");
        }

        result[y][x] = newPixel;
      }
    }

    return new ImageModelImpl(result);
  }

  /**
   * Clamps the given value to ensure it lies within the range [0, 255].
   * <p>
   * If the input value is negative, this method returns 0. If the input value
   * is greater than 255, it returns 255. Otherwise, it returns the input value.
   * This function is typically used to ensure that RGB values remain within
   * valid bounds.
   * </p>
   *
   * @param value The integer value to be clamped.
   * @return The clamped value, guaranteed to be within the range [0, 255].
   */
  private int clamp(int value) {
    if (value < 0) {
      return 0;
    }
    return Math.min(255, value);
  }

}
