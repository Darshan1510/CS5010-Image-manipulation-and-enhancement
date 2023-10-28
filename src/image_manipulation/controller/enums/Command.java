package image_manipulation.controller.enums;

public enum Command {
  RED_COMPONENT("red-component", 2),
  BLUE_COMPONENT("blue-component", 2), SAVE("save", 2),
  GREEN_COMPONENT("green-component", 2), LOAD("load", 2),
  VALUE_COMPONENT("value-component", 2), SEPIA("sepia", 2),
  SHARPEN("sharpen", 2), BRIGHTEN("brighten", 3),
  RGB_COMBINE("rgb-combine", 4), RGB_SPLIT("rgb-split", 4),
  HORIZONTAL_FLIP("horizontal-flip", 2), BLUR("blur", 2),
  VERTICAL_FLIP("vertical-flip", 2), RUN_SCRIPT("run", 1),
  LUMA_COMPONENT("luma-component", 2),
  INTENSITY_COMPONENT("intensity-component", 2);

  private final String command;

  private final int args;

  private Command(String command, int args) {
    this.command = command;
    this.args = args;
  }

  public String command() {
    return command;
  }

  public int args() {
    return args;
  }

}
