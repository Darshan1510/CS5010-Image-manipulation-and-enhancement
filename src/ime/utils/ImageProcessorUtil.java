package ime.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * The ImageProcessorUtil class provides utility methods and constants for image processing
 * operations.
 */
public class ImageProcessorUtil {

  /**
   * The Sepia transformation matrix for applying a sepia filter to an image.
   */
  public static final double[][] SEPIA_TRANSFORMER = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };

  /**
   * The sharpening convolution kernel for enhancing image details.
   */
  public static final double[][] SHARPEN_KERNEL = new double[][]{
          {-0.125, -0.125, -0.125, -0.125, -0.125,},
          {-0.125, 0.25, 0.25, 0.25, -0.125,},
          {-0.125, 0.25, 1.00, 0.25, -0.125,},
          {-0.125, 0.25, 0.25, 0.25, -0.125,},
          {-0.125, -0.125, -0.125, -0.125, -0.125,}};

  /**
   * A 3x3 kernel used for blurring an image to reduce noise or smooth details.
   */
  public static final double[][] BLUR_KERNEL = new double[][]{
          {0.0625, 0.125, 0.0625},
          {0.125, 0.25, 0.125},
          {0.0625, 0.125, 0.0625}};


  /**
   * Pads a 2D channel array to a specified size.
   *
   * @param channel The original channel array to pad.
   * @param paddedSize The size to which the channel should be padded.
   * @return The padded channel array.
   */
  public static double[][] padChannel(double[][] channel, int paddedSize) {
    double[][] paddedChannel = new double[paddedSize][paddedSize];
    for (int i = 0; i < channel.length; i++) {
      System.arraycopy(channel[i], 0, paddedChannel[i], 0, channel[i].length);
    }
    return paddedChannel;
  }

  /**
   * Unpads a padded channel array to its original dimensions.
   *
   * @param paddedImage The padded channel array to unpad.
   * @param originalHeight The original height of the channel.
   * @param originalWidth The original width of the channel.
   * @return The unpadded channel array.
   */
  public static double[][] unpadChannel(double[][] paddedImage, int originalHeight,
                                        int originalWidth) {
    double[][] originalImage = new double[originalHeight][originalWidth];

    for (int i = 0; i < originalHeight; i++) {
      System.arraycopy(paddedImage[i], 0, originalImage[i], 0, originalWidth);
    }

    return originalImage;
  }

  /**
   * Determines the size to which a channel should be padded (nearest power of two).
   *
   * @param size The original size of the channel.
   * @return The padded size.
   */
  public static int padSize(int size) {
    int paddedSize = 1;
    while (paddedSize < size) {
      paddedSize *= 2;
    }
    return paddedSize;
  }

  /**
   * Applies the 2D Haar wavelet transform to an input matrix.
   *
   * @param X The input matrix.
   * @param s The size of the matrix.
   * @return The transformed matrix.
   */
  public static double[][] haarTransform2D(double[][] X, int s) {
    int c = s;

    while (c > 1) {
      for (int i = 0; i < s; i++) {
        double[] row = new double[c];
        System.arraycopy(X[i], 0, row, 0, c);
        row = transformSequence1D(row);
        System.arraycopy(row, 0, X[i], 0, c);
      }

      for (int j = 0; j < s; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = X[i][j];
        }
        column = transformSequence1D(column);
        for (int i = 0; i < c; i++) {
          X[i][j] = column[i];
        }
      }

      c = c / 2;
    }

    return X;
  }

  /**
   * Applies the inverse Haar wavelet transform to a transformed sequence.
   *
   * @param s The transformed sequence.
   * @return The inverted sequence.
   */
  public static double[][] inverseHaarTransform2D(double[][] X, int s) {
    int c = 2;

    while (c <= s) {
      for (int j = 0; j < s; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = X[i][j];
        }
        column = inverseTransform1D(column);
        for (int i = 0; i < c; i++) {
          X[i][j] = column[i];
        }
      }

      for (int i = 0; i < s; i++) {
        double[] row = new double[c];
        System.arraycopy(X[i], 0, row, 0, c);
        row = inverseTransform1D(row);
        System.arraycopy(row, 0, X[i], 0, c);
      }

      c = c * 2;
    }

    return X;
  }

  /**
   * Applies the Haar wavelet transform to a sequence of values.
   *
   * @param s The input sequence.
   * @return The transformed sequence.
   */
  public static double[] transformSequence1D(double[] s) {
    List<Double> avg = new ArrayList<>();
    List<Double> diff = new ArrayList<>();

    for (int i = 0; i < s.length; i += 2) {
      double a = s[i];
      double b = s[i + 1];
      double av = (a + b) / Math.sqrt(2);
      double di = (a - b) / Math.sqrt(2);
      avg.add(av);
      diff.add(di);
    }

    List<Double> result = new ArrayList<>(avg);
    result.addAll(diff);

    return result.stream().mapToDouble(Double::doubleValue).toArray();
  }

  /**
   * Applies the inverse Haar wavelet transform to a transformed sequence.
   *
   * @param s The transformed sequence.
   * @return The inverted sequence.
   */
  public static double[] inverseTransform1D(double[] s) {
    List<Double> values = DoubleStream.of(s).boxed().collect(Collectors.toList());
    List<Double> avg = values.subList(0, values.size() / 2);
    List<Double> diff = values.subList(values.size() / 2, values.size());

    List<Double> result = new ArrayList<>();
    for (int i = 0, j = 0; i < avg.size(); i++, j++) {
      double a = avg.get(i) / Math.sqrt(2);
      double d = diff.get(j) / Math.sqrt(2);
      result.add(a + d);
      result.add(a - d);
    }

    return result.stream().mapToDouble(Double::doubleValue).toArray();
  }

  /**
   * Extracts unique non-zero values from a 2D matrix.
   *
   * @param image The input matrix.
   * @return An array containing unique non-zero values.
   */
  public static double[] getUniqueValues(double[][] image) {
    Set<Double> uniqueValues = new HashSet<>();

    for (double[] row : image) {
      for (double value : row) {
        if (value != 0.0) {
          uniqueValues.add(Math.abs(value));
        }
      }
    }

    return uniqueValues.stream().mapToDouble(Double::doubleValue).toArray();
  }

  /**
   * Finds the threshold for channel compression based on a given percentage of unique values.
   *
   * @param values The array of unique values.
   * @param percentage The percentage of values to keep.
   * @return The calculated threshold.
   */
  public static double findThreshold(double[] values, double percentage) {
    int numToReset = (int) (values.length * percentage);
    if (numToReset < 1) {
      return 0.0;
    }

    // Sort the unique values
    Arrays.sort(values);

    return values[numToReset - 1];
  }

}
