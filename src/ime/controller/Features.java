package ime.controller;

import java.io.IOException;

import ime.controller.enums.Command;

public interface Features {


  void load(String filepath) throws IOException;


  void save(String filepath) throws IOException;

  /**
   * Converts an input image to the red component.
   */
  void redComponent() throws IOException;

  /**
   * Converts an input image to the blue component.
   */
  void blueComponent() throws IOException;

  /**
   * Converts an input image to the green component.
   */
  void greenComponent() throws IOException;

  /**
   * Converts an input image to greyscale using the luma component.
   */
  void lumaGreyscale() throws IOException;

  /**
   * Converts an input image to greyscale using the value component.
   */
  void valueGreyscale() throws IOException;

  /**
   * Converts an input image to greyscale using the intensity component.
   */
  void intensityGreyscale() throws IOException;


  void blur() throws IOException;

  void sharpen() throws IOException;


  void sepia() throws IOException;

  void brighten(int scale) throws IOException;

  void verticalFlip() throws IOException;

  void horizontalFlip() throws IOException;

  void colorCorrect() throws IOException;

  void levelAdjust(int b, int m, int w) throws IOException;

  void compress(double percentage) throws IOException;


  void rgbSplit(String redFilePath, String greenFilePath, String blueFilePath) throws IOException;

  void rgbCombine(String redImageFile, String greenImageFile, String blueImageFile)
          throws IOException;

  void split(Command command, double percentage) throws IOException;

  void reloadImage() throws IOException;

  /**
   * Exit the program.
   */
  void exitProgram();

}
