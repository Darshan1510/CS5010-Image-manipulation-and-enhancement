package ime.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import ime.model.ExtendedImageProcessor;
import ime.model.ExtendedImageProcessorImpl;

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
    ExtendedImageProcessor imageProcessor = new ExtendedImageProcessorImpl();
    ImageControllerInterface controller = getController(args);
    controller.execute(imageProcessor);
  }

  /**
   * This method determines the appropriate ImageControllerInterface based on the input arguments.
   * If the arguments indicate the use of the "-file" option, it creates an
   * ImageControllerInterface with a suitable input stream for script execution; otherwise, it
   * creates an ImageControllerInterface for interactive command-line input.
   *
   * @param args The input arguments passed to the method. It is expected to contain information
   *             about the input source, such as the "-file" option.
   * @return An instance of ImageControllerInterface configured based on the specified conditions.
   */
  private static ImageControllerInterface getController(String[] args) {
    if (args != null && args.length == 2 && args[0].equals("-file")) {
      // If the "-file" option is present, create an ImageControllerInterface for script execution.
      Reader in = new StringReader("run " + args[1] + "\nq");
      return new ImageController(in, System.out);
    } else {
      // If the conditions are not met or if args is null, create an ImageControllerInterface
      // for interactive command-line input.
      return new ImageController(new InputStreamReader(System.in), System.out);
    }

  }

}
