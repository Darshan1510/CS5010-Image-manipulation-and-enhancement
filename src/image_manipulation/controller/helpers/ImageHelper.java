package image_manipulation.controller.helpers;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ImageHelper {

  InputStream readImage(String fileName) throws FileNotFoundException;

  int[] readHeightWidth(String fileName);

}
