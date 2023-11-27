package ime.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class ViewModelImpl implements ViewModel {

  private final ExtendedImageProcessor processor;
  private Image image;

  private boolean imageReady;

  public ViewModelImpl(ExtendedImageProcessor processor) {
    this.processor = processor;
    this.imageReady = false;
  }

  @Override
  public void processImage(String imageName) throws IOException {
    OutputStream outputStream = this.processor.save(imageName);

    String data = outputStream.toString();
    Scanner sc = new Scanner(data);

    int width = sc.nextInt();
    int height = sc.nextInt();
    int max = sc.nextInt();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    WritableRaster raster = image.getRaster();

    int[] pixel = new int[3]; // RGB components
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        pixel[0] = sc.nextInt();   // red component
        pixel[1] = sc.nextInt();   // green component
        pixel[2] = sc.nextInt();   // blue component
        // setPixels
        raster.setPixel(y, x, pixel);
      }
    }
    this.image = image;
    this.imageReady = true;
  }

  /**
   * Checks if the image processing is complete and the processed image is ready for access.
   * This method is typically used internally to ensure that other methods are called only
   * after the image has been processed using the {@code processImage} method.
   *
   * @throws IllegalStateException If the image is not ready, indicating that the
   *                               {@code processImage} method should be called first.
   */
  private void isImageReady() {
    if (!this.imageReady) {
      throw new IllegalStateException("Call the processImage method to process the image " +
              "before accessing other methods.");
    }
  }


  @Override
  public Image getImage() {
    this.isImageReady();
    return this.image;
  }

}
