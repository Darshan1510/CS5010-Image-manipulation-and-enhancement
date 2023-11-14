//package ime.controller;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Scanner;
//import java.util.function.Function;
//
//import ime.controller.commands.ImageProcessorCommand;
//import ime.controller.commands.RedComponent;
//import ime.controller.enums.Command;
//import ime.model.ExtendedImageProcessor;
//
//public class ExtendedImageController extends ImageController
//        implements ExtendedImageControllerInterface {
//
//  /**
//   * Constructor for ImageController.
//   *
//   * @param in  A Readable source for user input.
//   * @param out An Appendable destination for program output.
//   */
//  public ExtendedImageController(Readable in, Appendable out) {
//    super(in, out);
//  }
//
//  /**
//   * This method creates and populates a map of commands to their corresponding
//   * ImageProcessorCommand functions.
//   *
//   * @return A map containing command strings and associated functions.
//   */
//  protected static Map<String, Function<Scanner, ImageProcessorCommand>> getImageProcessorCommand() {
//
//    Map<String, Function<Scanner, ImageProcessorCommand>> knownCommands =
//            new HashMap<>(ImageController.getImageProcessorCommand());
//
//    knownCommands.put(Command.LEVEL_ADJUST.command(), RedComponent::apply);
//    knownCommands.put(Command.COLOR_CORRECT.command(), RedComponent::apply);
//    knownCommands.put(Command.HISTOGRAM.command(), RedComponent::apply);
//    knownCommands.put(Command.COMPRESS.command(), RedComponent::apply);
//
//    return knownCommands;
//  }
//
//  /**
//   * The execute() method reads user input, processes image manipulation commands,
//   * and applies them to an ImageProcessor object until the user exits.
//   */
//  @Override
//  public void execute(ExtendedImageProcessor imageProcessor) throws IOException {
//    Objects.requireNonNull(imageProcessor);
//    Scanner scan = new Scanner(in);
//
//    Map<String, Function<Scanner, ImageProcessorCommand>> knownCommands
//            = getImageProcessorCommand();
//
//    while (scan.hasNext()) {
//      try {
//        ImageProcessorCommand c;
//        String in = scan.next();
//        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
//          return;
//        }
//
//        Function<Scanner, ImageProcessorCommand> cmd =
//                knownCommands.getOrDefault(in, null);
//        if (cmd == null) {
//          throw new IllegalArgumentException("Invalid command: " + in);
//        } else {
//          c = cmd.apply(scan);
//          c.process(imageProcessor);
//          out.append("Command performed: ").append(in).append("\n");
//        }
//      } catch (IOException | IllegalArgumentException e) {
//        this.out.append(e.getMessage()).append("\n");
//      }
//    }
//  }
//}
