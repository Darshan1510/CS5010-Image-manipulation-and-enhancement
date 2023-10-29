package image_manipulation.controller.enums;

public enum Command {
  RED_COMPONENT("red-component"),
  BLUE_COMPONENT("blue-component"), SAVE("save"),
  GREEN_COMPONENT("green-component"), LOAD("load"),
  VALUE_COMPONENT("value-component"), SEPIA("sepia"),
  SHARPEN("sharpen"), BRIGHTEN("brighten" ),
  RGB_COMBINE("rgb-combine"), RGB_SPLIT("rgb-split"),
  HORIZONTAL_FLIP("horizontal-flip"), BLUR("blur"),
  VERTICAL_FLIP("vertical-flip"), RUN_SCRIPT("run"),
  LUMA_COMPONENT("luma-component"),
  INTENSITY_COMPONENT("intensity-component");

  private final String command;

  private Command(String command) {
    this.command = command;
  }

  public String command() {
    return command;
  }

}
