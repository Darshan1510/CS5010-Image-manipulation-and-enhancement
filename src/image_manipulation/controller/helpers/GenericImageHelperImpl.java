package image_manipulation.controller.helpers;

import java.io.InputStream;

public class GenericImageHelperImpl extends AbstractImageHelper {
  @Override
  public InputStream readImage(String fileName) {
    return null;
  }

  @Override
  public int[] readHeightWidth(String fileName) {
    return new int[0];
  }

  public static String getExtension(String fileName) throws IllegalArgumentException {
    int lastIndexOfDot = fileName.lastIndexOf('.');
    if (lastIndexOfDot == -1) {
      throw new IllegalArgumentException("No extension found");  // No extension found
    }
    return fileName.substring(lastIndexOfDot + 1);
  }
}
