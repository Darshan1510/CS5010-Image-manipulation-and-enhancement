package image_manipulation.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import image_manipulation.model.enums.Component;

public class ImageProcessorImpl implements ImageProcessor {

  private final Map<String, ImageModel> images;

  public ImageProcessorImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void load(String imgName, InputStream is, int height, int width) throws IOException {
    RGBPixel[][] rgbPixels = new RGBPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = is.read();
        int green = is.read();
        int blue = is.read();

        if (red == -1 || green == -1 || blue == -1) {
          throw new IOException("Unexpected end of stream while reading pixel data.");
        }

        rgbPixels[i][j] = new RGBPixel(red, green, blue);
      }
    }
    this.images.put(imgName, new ImageModelImpl(rgbPixels));
  }

  @Override
  public void save(String imgName) {

  }

  @Override
  public void colorTransform(String imgName, String destImgName, double[][] transformer) {
    this.images.put(destImgName, this.get(imgName).colorTransform(transformer));
  }

  @Override
  public void colorRepresentation(String imgName, String destImgName, Component c) {
    this.images.put(destImgName, this.get(imgName).colorRepresentation(c));
  }

  @Override
  public void filter(String imgName, String destImgName, double[][] kernel) {
    this.images.put(destImgName, this.get(imgName).filter(kernel));
  }

  @Override
  public void horizontalFlip(String imgName, String destImgName) {
    this.images.put(destImgName, this.get(imgName).horizontalFlip());
  }

  @Override
  public void verticalFlip(String imgName, String destImgName) {
    this.images.put(destImgName, this.get(imgName).verticalFlip());
  }

  @Override
  public void rgbSplit(String imgName, String destRedImgName, String destGreenImageName,
                       String destBlueImgName) {
    this.images.put(destRedImgName, this.get(imgName).colorRepresentation(Component.RED));
    this.images.put(destGreenImageName, this.get(imgName).colorRepresentation(Component.GREEN));
    this.images.put(destBlueImgName, this.get(imgName).colorRepresentation(Component.BLUE));
  }

  @Override
  public void rgbCombine(String redImgName, String greenImageName, String blueImgName,
                         String destImgName) {

  }

  private ImageModel get(String imgName) throws IllegalArgumentException {

    // TODO: Write a new Exception which throws when the image is not found.
    if (this.images.get(imgName) == null) {
      throw new IllegalArgumentException("Image Not Found");
    }

    return this.images.get(imgName);
  }

}
