package image_manipulation.controller;

import image_manipulation.model.ImageProcessor;

import java.io.IOException;

/**
 * The ImageControllerInterface is an interface that defines a contract for classes responsible
 * for handling image manipulation commands. Classes that implement this interface should
 * provide an implementation for the execute() method, which is used to execute image
 * manipulation commands.
 */
public interface ImageControllerInterface {

  /**
   * Execute image manipulation commands based on user input.
   */
  void execute(ImageProcessor processor) throws IOException;
}
