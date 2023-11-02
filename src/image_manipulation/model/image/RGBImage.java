package image_manipulation.model.image;

import image_manipulation.enums.Component;

import java.util.Objects;

public class RGBImage implements ImageModel {

  private final RGBPixel[][] pixels;
  private final int height;
  private final int width;
  private final int maxValue;

  public RGBImage(int height, int width, RGBPixel[][] pixels) {
    if (pixels == null || pixels.length == 0 || pixels[0].length == 0) {
      throw new IllegalArgumentException("pixels cannot be null or length of the " +
              "pixels must not be zero.");
    }
    this.height = height;
    this.width = width;
    this.pixels = pixels;
    this.maxValue = 255;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  public int getMaxValue() {
    return maxValue;
  }


  @Override
  public PixelModel getPixelValues(int i, int j) {
    try {
      return this.pixels[i][j];
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Pixel indices are out of bound!");
    }
  }

  @Override
  public RGBPixel[][] getPixels() {
    return this.pixels;
  }

  @Override
  public ImageModel horizontalFlip() {
    RGBPixel[][] result = new RGBPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result[i][j] = this.pixels[i][width - 1 - j];
      }
    }
    return new RGBImage(height, width, result);
  }

  @Override
  public ImageModel verticalFlip() {
    RGBPixel[][] result = new RGBPixel[height][width];
    for (int i = 0; i < height; i++) {
      System.arraycopy(this.pixels[height - 1 - i], 0, result[i], 0, width);
    }

    return new RGBImage(height, width, result);
  }

  @Override
  public ImageModel brighten(int increment) {
    RGBPixel[][] rgbPixels = new RGBPixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = this.pixels[i][j].getR() + increment;
        int g = this.pixels[i][j].getG() + increment;
        int b = this.pixels[i][j].getB() + increment;

        rgbPixels[i][j] = new RGBPixel(r, g, b);
      }
    }
    return new RGBImage(height, width, rgbPixels);
  }

  @Override
  public ImageModel filter(double[][] kernel) {
    RGBPixel[][] result = new RGBPixel[height][width];
    int kernelSize = kernel.length;
    int kernelLength = kernelSize / 2;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double redValue = 0, greenValue = 0, blueValue = 0;
        PixelModel rgb;

        for (int k = i - kernelLength, x = 0; k <= i + kernelLength; k++, x++) {
          for (int l = j - kernelLength, y = 0; l <= j + kernelLength; l++, y++) {
            if ((k >= 0 && k < height) && (l >= 0 && l < width)) {
              rgb = this.getPixelValues(k, l);
              redValue += rgb.getR() * kernel[x][y];
              greenValue += rgb.getG() * kernel[x][y];
              blueValue += rgb.getB() * kernel[x][y];
            }
          }
        }

        result[i][j] = new RGBPixel((int) Math.round(redValue), (int) Math.round(greenValue),
                (int) Math.round(blueValue));
      }
    }

    return new RGBImage(height, width, result);
  }

  @Override
  public ImageModel colorTransform(double[][] transformer) {
    RGBPixel[][] rgbPixels = new RGBPixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int r = this.pixels[i][j].getR();
        int g = this.pixels[i][j].getG();
        int b = this.pixels[i][j].getB();

        rgbPixels[i][j] = new RGBPixel(r, g, b);

        int transformedR = (int) Math.min(255,
                Math.round(transformer[0][0] * r + transformer[0][1] * g + transformer[0][2] * b));

        int transformedG = (int) Math.min(255,
                Math.round(transformer[1][0] * r + transformer[1][1] * g + transformer[1][2] * b));

        int transformedB = (int) Math.min(255,
                Math.round(transformer[2][0] * r + transformer[2][1] * g + transformer[2][2] * b));

        rgbPixels[i][j] = new RGBPixel(transformedR, transformedG, transformedB);

      }
    }

    return new RGBImage(height, width, rgbPixels);
  }

  @Override
  public ImageModel grayscale(Component component) {
    int height = this.pixels.length;
    int width = this.pixels[0].length;
    RGBPixel[][] result = new RGBPixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel pixel = this.pixels[i][j];
        RGBPixel newPixel = null;

        switch (component) {
          case RED:
            newPixel = new RGBPixel(pixel.getR(), pixel.getR(), pixel.getR());
            break;
          case GREEN:
            newPixel = new RGBPixel(pixel.getG(), pixel.getG(), pixel.getG());
            break;
          case BLUE:
            newPixel = new RGBPixel(pixel.getB(), pixel.getB(), pixel.getB());
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

        result[i][j] = newPixel;
      }
    }
    return new RGBImage(result.length, result[0].length, result);
  }


  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof RGBImage)) {
      return false;
    }

    ImageModel other = (RGBImage) o;

    boolean isEqual =
            this.height == other.getHeight() && this.maxValue == other.getMaxValue()
                    && this.width == other.getWidth();


    if (isEqual) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          isEqual = isEqual && this.getPixelValues(i, j).equals(other.getPixelValues(i, j));
        }
      }
    }

    return isEqual;
  }

  @Override
  public int hashCode() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sb.append(this.pixels[i][j].getR()).append(this.pixels[i][j].getG())
                .append(this.pixels[i][j].getB());
      }
    }
    return Objects.hash(this.height, this.width, sb.toString(), this.maxValue);
  }

}

