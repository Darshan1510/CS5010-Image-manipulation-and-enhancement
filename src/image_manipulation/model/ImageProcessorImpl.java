package image_manipulation.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import image_manipulation.model.enums.Component;

/**
 * The ImageProcessorImpl class implements the ImageProcessor interface, providing the core
 * functionality for processing and manipulating images. It manages a collection of images and
 * offers methods for loading, saving, transforming, and modifying these images.
 */
public class ImageProcessorImpl implements ImageProcessor {

  private final Map<String, ImageModel> images;

  /**
   * Constructs a new ImageProcessorImpl instance with an empty map to store images.
   */
  public ImageProcessorImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void load(String imgName, RGBImage rgbImage) throws IOException {
    this.images.put(imgName, rgbImage);
  }

  @Override
  public ImageModel save(String imgName) {
    return this.get(imgName);
  }

  @OverridedestImgName
  public void colorTransform(String imgName, String destImgName, double[][] transformer) {
    ImageModel inputImage = this.get(imgName);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();

    // Create a new RGBPixel array to store the transformed image
    RGBPixel[][] transformedPixels = new RGBPixel[height][width];

    // Apply the color transformation to each pixel
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel pixel = inputImage.getPixelValues(i, j);

        int newRed = (int) (transformer[0][0] * pixel.getR() + transformer[0][1] * pixel.getG()
                + transformer[0][2] * pixel.getB());
        int newGreen = (int) (transformer[1][0] * pixel.getR() + transformer[1][1] * pixel.getG()
                + transformer[1][2] * pixel.getB());
        int newBlue = (int) (transformer[2][0] * pixel.getR() + transformer[2][1] * pixel.getG()
                + transformer[2][2] * pixel.getB());

        newRed = Math.max(0, Math.min(newRed, 255)); // Clamp the values
        newGreen = Math.max(0, Math.min(newGreen, 255));
        newBlue = Math.max(0, Math.min(newBlue, 255));

        transformedPixels[i][j] = new RGBPixel(newRed, newGreen, newBlue);
      }
    }

    // Create a new RGBImage with the transformed pixels and update the destination image
    this.images.put(destImgName, new RGBImage(height, width, transformedPixels));
    // this.images.put(destImgName, this.get(imgName).colorTransform(transformer));
  }

  @Override
  public void grayscale(String imgName, String , Component c) {
    // Get the input image
    ImageModel inputImage = this.get(imgName);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();

    // Create a new RGBPixel array to store the grayscale image
    RGBPixel[][] grayscalePixels = new RGBPixel[height][width];

    // Apply the grayscale transformation to each pixel
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel pixel = inputImage.getPixelValues(i, j);

        int grayValue = 0;
        switch (c) {
          case RED:
            grayValue = pixel.getR();
            break;
          case GREEN:
            grayValue = pixel.getG();
            break;
          case BLUE:
            grayValue = pixel.getB();
            break;
          case VALUE:
          case INTENSITY:
          case LUMA:
            // For these cases, calculate the grayscale value as a weighted sum of RGB values
            grayValue = (int) (0.2126 * pixel.getR() + 0.7152 * pixel.getG() + 0.0722 * pixel.getB());
            break;
        }
        grayValue = Math.max(0, Math.min(grayValue, 255)); // Clamp the value

        grayscalePixels[i][j] = new RGBPixel(grayValue, grayValue, grayValue);
      }
    }

    // Create a new RGBImage with the grayscale pixels and update the destination image
    this.images.put(destImgName, new RGBImage(height, width, grayscalePixels));
  }

  @Override
  public void brighten(String imgName, String destImgName, int increment) {
    // Get the input image
    ImageModel inputImage = this.get(imgName);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();

    // Create a new RGBPixel array to store the brightened image
    RGBPixel[][] brightenedPixels = new RGBPixel[height][width];

    // Brighten the image by adding the increment to each color component
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        RGBPixel pixel = inputImage.getPixelValues(i, j);

        int newRed = Math.min(pixel.getR() + increment, 255);
        int newGreen = Math.min(pixel.getG() + increment, 255);
        int newBlue = Math.min(pixel.getB() + increment, 255);

        brightenedPixels[i][j] = new RGBPixel(newRed, newGreen, newBlue);
      }
    }

    // Create a new RGBImage with the brightened pixels and update the destination image
    this.images.put(destImgName, new RGBImage(height, width, brightenedPixels));
    //this.images.put(destImgName, this.get(imgName).brighten(increment));
  }

  @Override
  public void filter(String imgName, String destImgName, double[][] kernel) {
    ImageModel sourceImage = get(imgName);
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();

    // Create a destination image with the same dimensions.
    RGBPixel[][] filteredPixels = new RGBPixel[height][width];

    // Apply the filter to each pixel in the source image.
    int kernelSize = kernel.length;
    int kernelHalf = kernelSize / 2;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double redSum = 0.0;
        double greenSum = 0.0;
        double blueSum = 0.0;

        for (int j = -kernelHalf; j <= kernelHalf; j++) {
          for (int i = -kernelHalf; i <= kernelHalf; i++) {
            int newX = x + i;
            int newY = y + j;

            // Ensure the new coordinates are within the image bounds.
            if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
              RGBPixel pixel = sourceImage.getPixelValues(newY, newX);
              double kernelValue = kernel[j + kernelHalf][i + kernelHalf];
              redSum += pixel.getR() * kernelValue;
              greenSum += pixel.getG() * kernelValue;
              blueSum += pixel.getB() * kernelValue;
            }
          }
        }

        // Set the filtered pixel in the destination image.
        int redValue = (int) redSum;
        int greenValue = (int) greenSum;
        int blueValue = (int) blueSum;
        filteredPixels[y][x] = new RGBPixel(redValue, greenValue, blueValue);
      }
    }

    // Create a new image with the filtered pixel data and store it in the destination.
    RGBImage filteredImage = new RGBImage(height, width, filteredPixels);
    images.put(destImgName, filteredImage);

    this.images.put(destImgName, this.get(imgName).filter(kernel));
  }

  @Override
  public void horizontalFlip(String imgName, String destImgName) {
    // Get the input image
    ImageModel inputImage = this.get(imgName);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();

    // Create a new RGBPixel array to store the horizontally flipped image
    RGBPixel[][] flippedPixels = new RGBPixel[height][width];

    // Flip the image horizontally
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        flippedPixels[i][j] = inputImage.getPixelValues(i, width - 1 - j);
      }
    }

    // Create a new RGBImage with the flipped pixels and update the destination image
    this.images.put(destImgName, new RGBImage(height, width, flippedPixels));
    //this.images.put(destImgName, this.get(imgName).horizontalFlip());
  }

  @Override
  public void verticalFlip(String imgName, String destImgName) {
    // Get the input image
    ImageModel inputImage = this.get(imgName);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();

    // Create a new RGBPixel array to store the vertically flipped image
    RGBPixel[][] flippedPixels = new RGBPixel[height][width];

    // Flip the image vertically
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        flippedPixels[i][j] = inputImage.getPixelValues(height - 1 - i, j);
      }
    }

    // Create a new RGBImage with the flipped pixels and update the destination image
    this.images.put(destImgName, new RGBImage(height, width, flippedPixels));
    //this.images.put(destImgName, this.get(imgName).verticalFlip());
  }

  @Override
  public void rgbSplit(String imgName, String destRedImgName, String destGreenImageName,
                       String destBlueImgName) {
    this.images.put(destRedImgName, this.get(imgName).grayscale(Component.RED));
    this.images.put(destGreenImageName, this.get(imgName).grayscale(Component.GREEN));
    this.images.put(destBlueImgName, this.get(imgName).grayscale(Component.BLUE));
  }

  @Override
  public void rgbCombine(String redImgName, String greenImageName, String blueImgName,
                         String destImgName) {
    ImageModel red = this.get(redImgName);
    ImageModel green = this.get(greenImageName);
    ImageModel blue = this.get(blueImgName);

    if (red.getHeight() == green.getHeight() && blue.getHeight() == red.getHeight()
            && red.getWidth() == blue.getWidth() && red.getWidth() == green.getWidth()) {

      RGBPixel[][] pixelResults = new RGBPixel[red.getHeight()][red.getWidth()];

      for (int i = 0; i < red.getHeight(); i++) {
        for (int j = 0; j < red.getWidth(); j++) {
          pixelResults[i][j] = new RGBPixel(red.getPixelValues(i, j).getR(),
                  green.getPixelValues(i, j).getG(),
                  blue.getPixelValues(i, j).getB());
        }
      }

      ImageModel combinedImage = new RGBImage(red.getHeight(), red.getWidth(), pixelResults);
      this.images.put(destImgName, combinedImage);
    } else {
      throw new IllegalArgumentException("To combine, images should be of the same dimensions");
    }
  }

  private ImageModel get(String imgName) throws IllegalArgumentException {
    if (this.images.get(imgName) == null) {
      throw new IllegalArgumentException("Image Not Found");
    }
    return this.images.get(imgName);
  }
}
