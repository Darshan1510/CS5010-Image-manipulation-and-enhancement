package ime.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import ime.model.image.ImageModel;
import ime.model.image.RGBImage;
import ime.model.image.RGBPixel;
import ime.utils.ImageProcessorUtil;

/**
 * The ImageProcessorImpl class implements the ImageProcessor interface, providing the core
 * functionality for processing and manipulating images. It manages a collection of images and
 * offers methods for loading, saving, transforming, and modifying these images.
 */
public class ImageProcessorImpl implements ImageProcessor {

  protected final Map<String, ImageModel> images;

  /**
   * Constructs a new ImageProcessorImpl instance with an empty map to store images.
   */
  public ImageProcessorImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void load(String imgName, InputStream inputStream) {
    Scanner sc = new Scanner(inputStream);
    int width = sc.nextInt();
    int height = sc.nextInt();
    int max = sc.nextInt();

    if (height < 0 || width < 0 || max < 0) {
      throw new IllegalArgumentException("Invalid file");
    }

    RGBPixel[][] pixels = new RGBPixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        if (r < 0 || g < 0 || b < 0) {
          throw new IllegalArgumentException("Invalid file.");
        }
        pixels[i][j] = new RGBPixel(r, g, b);

      }
    }
    images.put(imgName, new RGBImage(height, width, pixels));
  }

  @Override
  public OutputStream save(String imgName) throws IOException {
    ImageModel imageModel = this.get(imgName);
    StringBuilder sb = new StringBuilder();
    int width = imageModel.getWidth();
    int height = imageModel.getHeight();
    int max = imageModel.getMaxValue();
    sb.append(width).append(" ").append(height).append(System.lineSeparator());
    sb.append(max).append(System.lineSeparator());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sb.append(imageModel.getPixelValues(i, j).getR()).append(System.lineSeparator());
        sb.append(imageModel.getPixelValues(i, j).getG()).append(System.lineSeparator());
        sb.append(imageModel.getPixelValues(i, j).getB()).append(System.lineSeparator());
      }
    }
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputStream.write(sb.toString().getBytes());
    return outputStream;
  }

  @Override
  public void sepia(String imgName, String destImgName) {
    double[][] transformer = ImageProcessorUtil.SEPIA_TRANSFORMER;
    Function<RGBPixel, RGBPixel> transformerFunc = pixel -> {
      int r = pixel.getR();
      int g = pixel.getG();
      int b = pixel.getB();

      int transformedR = applyTransformation(transformer[0], r, g, b);
      int transformedG = applyTransformation(transformer[1], r, g, b);
      int transformedB = applyTransformation(transformer[2], r, g, b);

      return new RGBPixel(transformedR, transformedG, transformedB);
    };
    this.images.put(destImgName, this.get(imgName).applyTransform(transformerFunc));
  }

  @Override
  public void redGrayscale(String imgName, String destImgName) {
    Function<RGBPixel, RGBPixel> transformerFunc = p -> new RGBPixel(p.getR(), p.getR(), p.getR());
    this.images.put(destImgName, this.get(imgName).applyTransform(transformerFunc));
  }

  @Override
  public void blueGrayscale(String imgName, String destImgName) {
    Function<RGBPixel, RGBPixel> blueTransform = p -> new RGBPixel(p.getB(), p.getB(), p.getB());
    this.images.put(destImgName, this.get(imgName).applyTransform(blueTransform));
  }

  @Override
  public void greenGrayscale(String imgName, String destImgName) {
    Function<RGBPixel, RGBPixel> greenTransform = p -> new RGBPixel(p.getG(), p.getG(), p.getG());
    this.images.put(destImgName, this.get(imgName).applyTransform(greenTransform));
  }

  @Override
  public void lumaGrayscale(String imgName, String destImgName) {
    Function<RGBPixel, RGBPixel> lumaTransform = p -> {
      int weightedSumPixelVal = (int) (0.2126 * p.getR() + 0.7152 * p.getG() + 0.0722 * p.getB());
      return new RGBPixel(weightedSumPixelVal, weightedSumPixelVal, weightedSumPixelVal);
    };
    this.images.put(destImgName, this.get(imgName).applyTransform(lumaTransform));
  }

  @Override
  public void valueGrayscale(String imgName, String destImgName) {
    Function<RGBPixel, RGBPixel> valueTransform = p -> {
      int maxPixelVal = Math.max(p.getR(), Math.max(p.getB(), p.getG()));
      return new RGBPixel(maxPixelVal, maxPixelVal, maxPixelVal);
    };
    this.images.put(destImgName, this.get(imgName).applyTransform(valueTransform));
  }

  @Override
  public void intensityGrayscale(String imgName, String destImgName) {
    Function<RGBPixel, RGBPixel> intensityTransform = p -> {
      int avgPixelVal = (p.getR() + p.getG() + p.getB()) / 3;
      return new RGBPixel(avgPixelVal, avgPixelVal, avgPixelVal);
    };
    this.images.put(destImgName, this.get(imgName).applyTransform(intensityTransform));
  }

  @Override
  public void brighten(String imgName, String destImgName, int increment) {
    Function<RGBPixel, RGBPixel> brightenTransform = p -> {
      int r = p.getR() + increment;
      int g = p.getG() + increment;
      int b = p.getB() + increment;

      return new RGBPixel(r, g, b);
    };
    this.images.put(destImgName, this.get(imgName).applyTransform(brightenTransform));
  }

  @Override
  public void sharpen(String imgName, String destImgName) {
    double[][] kernel = ImageProcessorUtil.SHARPEN_KERNEL;
    if (validateKernel(kernel)) {
      throw new IllegalArgumentException("Invalid kernel! Please provide the valid kernel "
              + "with odd dimensions. (ex: 3*3, 5*5)");
    }
    this.images.put(destImgName, this.get(imgName).filter(kernel));
  }

  @Override
  public void blur(String imgName, String destImgName) {
    double[][] kernel = ImageProcessorUtil.BLUR_KERNEL;
    if (validateKernel(kernel)) {
      throw new IllegalArgumentException("Invalid kernel! Please provide the valid kernel "
              + "with odd dimensions. (ex: 3*3, 5*5)");
    }
    this.images.put(destImgName, this.get(imgName).filter(kernel));
  }

  @Override
  public void horizontalFlip(String imgName, String destImgName) {
    this.images.put(destImgName, this.get(imgName).horizontalFlip());
  }

  @Override
  public void verticalFlip(String imgName, String destImgName) {
    this.images.put(destImgName, this.get(imgName).verticalFlip());
  }

  @Override
  public void rgbSplit(String imgName, String destRedImgName, String destGreenImageName,
                       String destBlueImgName) {
    this.redGrayscale(imgName, destRedImgName);
    this.greenGrayscale(imgName, destGreenImageName);
    this.blueGrayscale(imgName, destBlueImgName);
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
      throw new IllegalArgumentException("Images do not have the same dimension.");
    }
  }

  ImageModel get(String imgName) throws IllegalArgumentException {
    if (this.images.get(imgName) == null) {
      throw new IllegalArgumentException("Image Not Found");
    }
    return this.images.get(imgName);
  }

  /**
   * Applies a color transformation to the given RGB values using a specified transform row.
   *
   * @param transformRow An array of three doubles representing the transformation coefficients
   *                     for the RGB values.
   * @param r            The red value of the pixel.
   * @param g            The green value of the pixel.
   * @param b            The blue value of the pixel.
   * @return The transformed color value, capped at a maximum of 255.
   */
  private int applyTransformation(double[] transformRow, int r, int g, int b) {
    return (int) Math.min(255, Math.round(transformRow[0] * r + transformRow[1] * g
            + transformRow[2] * b));
  }

  /**
   * To validate the Kernel for filters.
   * It should be of odd dimension for the operation.
   *
   * @param kernel a 2D matrix representing the kernel that will be used for filtering.
   * @return returns true if the kernel is valid otherwise false.
   */
  private boolean validateKernel(double[][] kernel) {
    int l = kernel.length;
    if (l % 2 == 1) {
      for (double[] doubles : kernel) {
        if (doubles.length != l) {
          return true;
        }
      }
      return false;
    }
    return true;
  }
}
