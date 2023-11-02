package ime.controller.commands;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import ime.controller.ImageController;
import ime.controller.ImageControllerInterface;
import ime.controller.helpers.file.FileHelper;
import ime.controller.helpers.file.FileHelperImpl;
import ime.model.ImageProcessor;

/**
 * The RunScript class represents a command that executes a script from a specified file path.
 * This command does not perform any image manipulation directly but rather triggers the execution
 * of a sequence of image manipulation commands from the script file.
 */
public class RunScript implements ImageProcessorCommand {

  private final String filepath;

  /**
   * Constructs a RunScript command with the specified file path to the script.
   *
   * @param filepath The path to the script file that will be executed.
   */
  public RunScript(String filepath) {
    this.filepath = filepath;
  }

  /**
   * Creates and returns a RunScript command based on the input provided through a Scanner.
   *
   * @param s The Scanner used to read the input parameters for the command.
   * @return A RunScript command with the specified file path to the script.
   */
  public static ImageProcessorCommand apply(Scanner s) {
    String filepath = s.next();
    return new RunScript(filepath);
  }

  /**
   * Executes the RunScript command, triggering the execution of a sequence of image manipulation
   * commands defined in the specified script file.
   *
   * @param p The ImageProcessor used to process the command.
   */
  @Override
  public void process(ImageProcessor p) throws IOException {
    FileHelper fileHelper = new FileHelperImpl();
    String fileContents = fileHelper.readFile(filepath);

    Reader in = new StringReader(fileContents);
    ImageControllerInterface imageController = new ImageController(in, System.out);
    imageController.execute(p);
  }
}
