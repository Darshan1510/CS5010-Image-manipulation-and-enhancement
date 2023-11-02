package ime.controller;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import ime.model.ImageProcessor;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the SimpleImageController
 */
public class ImageControllerTest {
  private ImageControllerInterface controller;
  private Reader in;
  private Appendable out;

  @Test(expected = RuntimeException.class)
  public void testLoadCommandInvalidFilePath() throws RuntimeException {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "load";
    String testArguments = " res/test.png test";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testInvalidCommand() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "brigten ";
    String testArguments = "";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Invalid command", out.toString().trim());
  }

  @Test
  public void testLoadCommandValid() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "load";
    String testArguments = " res/paris-test.png paris-test";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command: " + testArguments.split(" ")[2], logger.toString());
  }

  @Test
  public void testSaveCommandValid() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "save";
    String testArguments = " res/paris-test.png paris-test";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command: " + testArguments.split(" ")[2], logger.toString());
  }

  @Test
  public void testBrightenCommand() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "brighten";
    String testArguments = " 50 test test-brighter";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testBrightenCommandWithNegativeValues() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "brighten";
    String testArguments = " -50 test test-brighter";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testBrightenRedOnly() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "brighten";
    String testArguments = " 50 test-red test-red";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testBrightenGreenOnly() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "brighten";
    String testArguments = " 50 test-green test-green";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testBrightenBlueOnly() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "brighten";
    String testArguments = " 50 test-blue test-blue";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testRgbSplitCommand() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "rgb-split";
    String testArguments = " test test-red test-green test-blue";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testVerticalFlipCommand() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "vertical-flip";
    String testArguments = " test test-vertical";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testHorizontalFlipCommand() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "horizontal-flip";
    String testArguments = " test test-horizontal";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testHorizontalFlipCommandOnVerticallyFlipped() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "vertical-flip";
    String testArguments = " test-vertical test-horizontal-vertical";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testRgbCombineCommand() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "rgb-combine";
    String testArguments = " test test-red test-green test-blue";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  @Test
  public void testGreyscaleCommand() {
    StringBuilder logger = new StringBuilder();
    ImageProcessor mockModel = new MockModel(logger);

    String testCommand = "value-component";
    String testArguments = " test test-greyscale";

    in = new StringReader(testCommand + testArguments);
    out = new StringWriter();

    controller = new ImageController(in, out);
    controller.execute(mockModel);

    assertEquals("Command:" + testArguments, logger.toString());
  }

  /**
   * Represents a MockModel of the ImageProcessor to test the controller..
   */
  private static class MockModel implements ImageProcessor {

    private final StringBuilder sb;

    public MockModel(StringBuilder sb) {
      this.sb = sb;
    }

    @Override
    public OutputStream save(String imageName) throws IOException {
      sb.append("Command: ").append(imageName);
      // Return does not matter as this is a controller test.
      String newImage = "2 2 183 10 10 15 90 10 12 10 10 15 12 10 10";
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      byte[] strBytes = newImage.getBytes(); // Convert string to byte array
      outputStream.write(strBytes);
      return outputStream;
    }

    @Override
    public void sepia(String imgName, String destImgName) {
      sb.append("Command: ").append(imgName).append(" ").append(destImgName);
    }

    @Override
    public void redGrayscale(String imgName, String destImgName) {
      sb.append("Command: ").append(imgName).append(" ").append(destImgName);
    }

    @Override
    public void blueGrayscale(String imgName, String destImgName) {
      sb.append("Command: ").append(imgName).append(" ").append(destImgName);
    }

    @Override
    public void greenGrayscale(String imgName, String destImgName) {
      sb.append("Command: ").append(imgName).append(" ").append(destImgName);
    }

    @Override
    public void lumaGrayscale(String imgName, String destImgName) {
      sb.append("Command: ").append(imgName).append(" ").append(destImgName);
    }

    @Override
    public void valueGrayscale(String imgName, String destImgName) {
      sb.append("Command: ").append(imgName).append(" ").append(destImgName);
    }

    @Override
    public void intensityGrayscale(String imgName, String destImgName) {
      sb.append("Command: ").append(imgName).append(" ").append(destImgName);
    }

    @Override
    public void verticalFlip(String image, String result) {
      sb.append("Command: ").append(image).append(" ").append(result);
    }

    @Override
    public void load(String imgName, InputStream inputStream) {
      sb.append("Command: ").append(imgName);
    }


    @Override
    public void brighten(String imgName, String destImgName, int increment) {
      sb.append("Command: ").append(increment).append(" ").append(imgName).append(" ").append(destImgName);
    }

    @Override
    public void horizontalFlip(String image, String result) {
      sb.append("Command: ").append(image).append(" ").append(result);
    }


    @Override
    public void rgbSplit(String image, String redResult, String greenResult, String blueResult) {
      sb.append("Command: ").append(image).append(" ").append(redResult).append(" ")
              .append(greenResult).append(" ").append(blueResult);

    }

    @Override
    public void rgbCombine(String redImage, String greenImage,
                           String blueImage, String resultImage) {
      sb.append("Command: ").append(resultImage).append(" ").append(redImage).append(" ")
              .append(greenImage).append(" ").append(blueImage);
    }

    @Override
    public void blur(String image, String result) {
      sb.append("Command: ").append(image).append(" ").append(result);
    }

    @Override
    public void sharpen(String image, String result) {
      sb.append("Command: ").append(image).append(" ").append(result);
    }


  }

}
