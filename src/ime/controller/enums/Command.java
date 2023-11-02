package ime.controller.enums;

/**
 * The Command enum defines a set of image manipulation commands, each associated with a
 * command string used for user input and processing. These commands represent various
 * image processing operations that can be performed on images.
 */
public enum Command {
  RED_COMPONENT("red-component"),
  BLUE_COMPONENT("blue-component"),
  SAVE("save"),
  GREEN_COMPONENT("green-component"),
  LOAD("load"),
  VALUE_COMPONENT("value-component"),
  SEPIA("sepia"),
  SHARPEN("sharpen"),
  BRIGHTEN("brighten"),
  RGB_COMBINE("rgb-combine"),
  RGB_SPLIT("rgb-split"),
  HORIZONTAL_FLIP("horizontal-flip"),
  BLUR("blur"),
  VERTICAL_FLIP("vertical-flip"),
  RUN_SCRIPT("run"),
  LUMA_COMPONENT("luma-component"),
  INTENSITY_COMPONENT("intensity-component");

  private final String command;

  /**
   * Constructs a Command enum value with the specified command string.
   *
   * @param command The command string associated with the enum value.
   */
  private Command(String command) {
    this.command = command;
  }

  /**
   * Get the command string associated with this enum value.
   *
   * @return The command string.
   */
  public String command() {
    return command;
  }
}
