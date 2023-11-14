package ime.model;

import java.awt.image.BufferedImage;
import java.util.InputMismatchException;
import java.util.function.Function;

import ime.model.image.ImageModel;
import ime.model.image.RGBImage;
import ime.model.image.RGBPixel;
import ime.utils.HistogramGenerator;

/**
 * Implementation class for the ExtendedImageProcessor interface, extending the base
 * ImageProcessorImpl. It provides additional image processing functionality beyond the
 * standard image processing operations.
 */
public class ExtendedImageProcessorImpl extends ImageProcessorImpl
        implements ExtendedImageProcessor {

  public ExtendedImageProcessorImpl() {
    super();
  }

  @Override
  public void colorCorrect(String[] args) {
    String imgName = args[0];
    String destImgName = args[1];
    ImageModel sourceImg = this.getImage(imgName);
    HistogramGenerator histogramGenerator = new HistogramGenerator();
    BufferedImage histogram = histogramGenerator.createHistogram(sourceImg);

    ImageModel rgbImage = this.convertBufferedImageToImageModel(histogram);

  }

  @Override
  public void compression(double percentage, String imgName, String destImgName) {
    //check absolute value while setting zero
    // implement Haar -> test it clearly if it's working or not (1st)
    // implement inverse Haar -> do the same
    // RGBPixel[][] -> R -> G -> B (you can do it together) -
  }


  @Override
  public void histogram(String imgName, String destImgName) {
    HistogramGenerator histogramGenerator = new HistogramGenerator();
    ImageModel sourceImg = this.getImage(imgName);
    BufferedImage histogram = histogramGenerator.createHistogram(sourceImg);

    this.putImage(destImgName, this.convertBufferedImageToImageModel(histogram));
  }

  @Override
  public void levelsAdjust(String[] args) {
    int black = Integer.parseInt(args[0]);
    int mid = Integer.parseInt(args[1]);
    int white = Integer.parseInt(args[2]);
    String imgName = args[3];
    String destImgName = args[4];

    if (black < 0 || mid < 0 || white < 0 || white > 255 || black > mid || mid > white) {
      throw new InputMismatchException("b, m and w should be in (0, 255) range.");
    }


    Function<RGBPixel, RGBPixel> levelAdjust = p -> {
      int updatedR = this.fittingProcess(black, mid, white, p.getR());
      int updatedG = this.fittingProcess(black, mid, white, p.getG());
      int updatedB = this.fittingProcess(black, mid, white, p.getB());

      return new RGBPixel(updatedR, updatedG, updatedB);
    };

    ImageModel currentImage = this.getImage(imgName);
    ImageModel filteredImage = currentImage.applyTransform(levelAdjust);

    if (args.length > 5) {
      String split = args[5];
      if (!split.equals("split")) {
        throw new InputMismatchException("Invalid args for Split view for Levels adjust.");
      }
      float widthPercentage = Float.parseFloat(args[6]);
      filteredImage = this.split(currentImage, filteredImage, widthPercentage);
    }

    this.putImage(destImgName, filteredImage);
  }

  /**
   * This private method performs a fitting process based on the given parameters.
   * It calculates and returns a fitted value using a quadratic equation.
   *
   * @param black  The black level parameter for the fitting process.
   * @param mid    The mid-level parameter for the fitting process.
   * @param white  The white level parameter for the fitting process.
   * @param signal The input signal value for which the fitting process is performed.
   * @return The fitted value calculated using the quadratic equation.
   */
  private int fittingProcess(int black, int mid, int white, int signal) {
    double A = Math.pow(black, 2) * (mid - white) - black * (Math.pow(mid, 2)
            - Math.pow(white, 2)) + white * Math.pow(mid, 2) - mid * Math.pow(white, 2);

    double Aa = -black * (128 - 255) + 128 * white - 255 * mid;

    double Ab = Math.pow(black, 2) * (128 - 255) + 255 * Math.pow(mid, 2)
            - 128 * Math.pow(white, 2);


    double Ac = Math.pow(black, 2) * (255 * mid - 128 * white)
            - black * (255 * Math.pow(mid, 2) - 128 * Math.pow(white, 2));

    double a = Aa / A;

    double b = Ab / A;

    double c = Ac / A;

    return (int) (a * Math.pow(signal, 2) + b * signal + c);
  }

  /**
   * This private method converts a BufferedImage to an ImageModel.
   *
   * @param image The BufferedImage to be converted.
   * @return An ImageModel representing the converted image with RGB pixels.
   */
  private ImageModel convertBufferedImageToImageModel(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    RGBPixel[][] pixels = new RGBPixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int color = image.getRGB(x, y);
        int red = (color >> 16) & 0xff;
        int green = (color >> 8) & 0xff;
        int blue = color & 0xff;
        pixels[y][x] = new RGBPixel(red, green, blue);
      }
    }

    return new RGBImage(height, width, pixels);
  }
}
