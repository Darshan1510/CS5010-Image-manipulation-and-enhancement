package ime.model;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

import ime.controller.helpers.image.ImageHelperFactory;
import ime.controller.helpers.image.ImageHelperFactoryImpl;
import ime.model.image.ImageModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A Junit test class to test the ImageProcessor.
 */
public class ImageProcessorImplTest {

  private final ImageHelperFactory factory = new ImageHelperFactoryImpl();
  private ImageProcessorImpl processor;
  private String filePath;
  private ImageModel image;

  @Before
  public void setUp() {
    try {
      filePath = "test_images/paris-test.ppm";
      processor = new ImageProcessorImpl();
      InputStream inputStream = factory.getImageHelper(filePath).readImage(filePath);
      processor.load("test", inputStream);
      image = processor.get("test");
    } catch (Exception e) {
      fail("Failed to instantiate the PPM image");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageFail() {
    processor.get("random_image");
  }


}
