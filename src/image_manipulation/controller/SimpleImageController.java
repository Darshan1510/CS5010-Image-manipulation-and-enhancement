package image_manipulation.controller;

import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleImageController {

  public static void main(String[] args) throws IOException {
    ImageControllerInterface controller = new ImageController(new InputStreamReader(System.in),
            System.out);
    controller.execute();

  }
}
