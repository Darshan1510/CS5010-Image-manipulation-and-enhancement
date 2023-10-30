package image_manipulation.controller;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The SimpleImageController class serves as a simple entry point for executing
 * image manipulation commands from the command line. It creates an instance of
 * ImageControllerInterface, which is initialized with a standard input reader
 * (InputStreamReader) for user input and the standard output for program output.
 * The controller then executes image manipulation commands by calling the execute()
 * method.
 */
public class SimpleImageController {

  /**
   * The main method creates an ImageControllerInterface instance and executes
   * image manipulation commands using the standard input and output streams.
   *
   * @param args The command-line arguments (not used in this context).
   * @throws IOException If an I/O error occurs during input or output operations.
   */
  public static void main(String[] args) throws IOException {
    ImageControllerInterface controller = new ImageController(new InputStreamReader(System.in),
            System.out);
    controller.execute();
  }
}
