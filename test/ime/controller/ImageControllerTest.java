//package ime.controller;
//
//import org.junit.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.Reader;
//import java.io.StringReader;
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import ime.controller.helpers.image.ImageHelperFactory;
//import ime.controller.helpers.image.ImageHelperFactoryImpl;
//import ime.model.ImageProcessor;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
///**
// * A JUnit test class for the SimpleImageController.
// */
//public class ImageControllerTest {
//  private ImageControllerInterface controller;
//  private Reader in;
//  private Appendable out;
//
//  private ImageHelperFactory imageHelperFactory = new ImageHelperFactoryImpl();
//
//  @Test(expected = RuntimeException.class)
//  public void testLoadCommandInvalidFilePath() throws RuntimeException, IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "load";
//    String testArguments = " res/test.png test";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testInvalidCommand() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "brigten ";
//    String testArguments = "";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Invalid command: brigten", out.toString().trim());
//  }
//
//  @Test
//  public void testLoadCommandValid() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "load";
//    String testArguments = " res/paris-test.png paris-test";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command: " + testArguments.split(" ")[2], logger.toString());
//  }
//
//  @Test
//  public void testSaveCommandValid() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "save";
//    String testArguments = " res/paris-test.png paris-test";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command: " + testArguments.split(" ")[2], logger.toString());
//  }
//
//  @Test
//  public void testBrightenCommand() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "brighten";
//    String testArguments = " 50 test test-brighter";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testBrightenCommandWithNegativeValues() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "brighten";
//    String testArguments = " -50 test test-brighter";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testBrightenRedOnly() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "brighten";
//    String testArguments = " 50 test-red test-red";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testBrightenGreenOnly() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "brighten";
//    String testArguments = " 50 test-green test-green";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testBrightenBlueOnly() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "brighten";
//    String testArguments = " 50 test-blue test-blue";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testRgbSplitCommand() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "rgb-split";
//    String testArguments = " test test-red test-green test-blue";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testVerticalFlipCommand() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "vertical-flip";
//    String testArguments = " test test-vertical";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testHorizontalFlipCommand() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "horizontal-flip";
//    String testArguments = " test test-horizontal";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testHorizontalFlipCommandOnVerticallyFlipped() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "vertical-flip";
//    String testArguments = " test-vertical test-horizontal-vertical";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testRgbCombineCommand() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "rgb-combine";
//    String testArguments = " test test-red test-green test-blue";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testGreyscaleCommand() throws IOException {
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    String testCommand = "value-component";
//    String testArguments = " test test-greyscale";
//
//    in = new StringReader(testCommand + testArguments);
//    out = new StringWriter();
//
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals("Command:" + testArguments, logger.toString());
//  }
//
//  @Test
//  public void testInvalidCommandError() throws IOException {
//
//    String expectedOutput =
//            "Command: paris\n"
//                    + "Invalid Command: horizo-flip\n"
//                    + "Command: paris\n"
//                    + "Command: paris paris-red\n"
//                    + "Command: paris paris-blue\n"
//                    + "Command: paris paris-green\n"
//                    + "Command: paris paris-value\n";
//
//    String inputCommand =
//            "load res/paris-test.png paris\n"
//                    + "loading\n"
//                    + "load res/paris-test.png paris\n"
//                    + "red-component paris paris-red\n"
//                    + "run res/test-script.txt";
//
//
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    in = new StringReader(inputCommand);
//    out = new StringWriter();
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals(expectedOutput, logger.toString());
//  }
//
//  @Test
//  public void testMixOps() throws IOException {
//
//    List<String> expectedFileName = new ArrayList<>(List.of(
//            "png/paris-green-split-png.png",
//            "ppm/paris-red-split-ppm.ppm",
//            "jpg/paris-blue-split-jpg.jpg",
//            "jpg/paris-combined-jpg.jpg",
//            "ppm/paris-value-greyscale-ppm.ppm",
//            "jpg/paris-luma-greyscale-jpg.jpg",
//            "ppm/paris-red-greyscale-ppm.ppm",
//            "png/paris-green-greyscale-png.png",
//            "ppm/paris-horizontal-ppm.ppm",
//            "png/paris-horizontal-vertical-png.png",
//            "ppm/paris-blur-ppm.ppm",
//            "png/paris-sharpen-png.png",
//            "jpg/paris-luma-greyscale-jpg.jpg",
//            "jpg/paris-sepia-jpg.jpg"
//    ));
//    in = new StringReader("load test_images/ppm/paris-test.ppm paris-ppm\n" +
//            "load res/png/paris.png paris-png\n" +
//            "load res/jpg/paris.jpg paris-jpg\n" +
//            "brighten 50 paris-ppm paris-brighter\n" +
//            "brighten -50 paris-ppm paris-darken\n" +
//            "rgb-split paris-ppm paris-red paris-green paris-blue\n" +
//            "save res/png/paris-green-split-png.png paris-green\n" +
//            "save res/ppm/paris-red-split-ppm.ppm paris-red\n" +
//            "save res/jpg/paris-blue-split-jpg.jpg paris-blue\n" +
//            "load res/png/paris-green-split-png.png paris-green\n" +
//            "load res/ppm/paris-red-split-ppm.ppm paris-red\n" +
//            "load res/jpg/paris-blue-split-jpg.jpg paris-blue\n" +
//            "rgb-combine paris-combined paris-red paris-green paris-blue\n" +
//            "save res/jpg/paris-combined-jpg.jpg paris-combined\n" +
//            "value-component paris-png paris-value\n" +
//            "save res/ppm/paris-value-greyscale-ppm.ppm paris-value\n" +
//            "luma-component paris-png paris-luma\n" +
//            "intensity-component paris-png paris-intensity\n" +
//            "red-component paris-jpg paris-red\n" +
//            "save res/ppm/paris-red-greyscale-ppm.ppm paris-red\n"
//    );
//
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    out = new StringWriter();
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    try {
//      for (int i = 0; i < expectedFileName.size(); i++) {
//        String outputImagePath = "res/" + expectedFileName.get(i);
//        String expectedImagePath = "test/" + expectedFileName.get(i);
//        assertEquals(imageHelperFactory.getImageHelper(outputImagePath)
//                        .readImage(outputImagePath).toString(),
//                imageHelperFactory.getImageHelper(outputImagePath)
//                        .readImage(expectedImagePath).toString()
//        );
//      }
//    } catch (Exception e) {
//      fail("Path doesn't have any files.");
//    }
//  }
//
//  @Test
//  public void testMixScriptCommandFileController() throws IOException {
//    String filepath = "res/paris-test.png";
//
//    String expectedOutput =
//            "Command: paris\n"
//                    + "Command: paris paris-horizontal\n"
//                    + "Command: paris\n"
//                    + "Command: paris paris-red\n"
//                    + "Command: paris paris-blue\n"
//                    + "Command: paris paris-green\n"
//                    + "Command: paris paris-value\n";
//
//    String inputCommand =
//            "load res/paris-test.png paris\n"
//                    + "horizontal-flip paris paris-horizontal\n"
//                    + "load res/paris-test.png paris\n"
//                    + "red-component paris paris-red\n"
//                    + "run res/test-script.txt";
//
//
//    StringBuilder logger = new StringBuilder();
//    ImageProcessor mockModel = new MockModel(logger);
//
//    in = new StringReader(inputCommand);
//    out = new StringWriter();
//    controller = new ImageController(in, out);
//    controller.execute(mockModel);
//
//    assertEquals(expectedOutput, logger.toString());
//  }
//
//  /**
//   * Represents a MockModel of the ImageProcessor to test the controller..
//   */
//  private static class MockModel implements ImageProcessor {
//
//    private final StringBuilder sb;
//
//    public MockModel(StringBuilder sb) {
//      this.sb = sb;
//    }
//
//    @Override
//    public OutputStream save(String imageName) throws IOException {
//      sb.append("Command: ").append(imageName);
//      // Return does not matter as this is a controller test.
//      String newImage = "2 2 183 10 10 15 90 10 12 10 10 15 12 10 10";
//      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//      byte[] strBytes = newImage.getBytes(); // Convert string to byte array
//      outputStream.write(strBytes);
//      return outputStream;
//    }
//
//    @Override
//    public void sepia(String imgName, String destImgName) {
//      sb.append("Command: ").append(imgName).append(" ").append(destImgName).append("\n");
//    }
//
//    @Override
//    public void redComponent(String imgName, String destImgName) {
//      sb.append("Command: ").append(imgName).append(" ").append(destImgName).append("\n");
//    }
//
//    @Override
//    public void blueComponent(String imgName, String destImgName) {
//      sb.append("Command: ").append(imgName).append(" ").append(destImgName).append("\n");
//    }
//
//    @Override
//    public void greenComponent(String imgName, String destImgName) {
//      sb.append("Command: ").append(imgName).append(" ").append(destImgName).append("\n");
//    }
//
//    @Override
//    public void lumaGreyscale(String imgName, String destImgName) {
//      sb.append("Command: ").append(imgName).append(" ").append(destImgName).append("\n");
//    }
//
//    @Override
//    public void valueGreyscale(String imgName, String destImgName) {
//      sb.append("Command: ").append(imgName).append(" ").append(destImgName).append("\n");
//    }
//
//    @Override
//    public void intensityGreyscale(String imgName, String destImgName) {
//      sb.append("Command: ").append(imgName).append(" ").append(destImgName).append("\n");
//    }
//
//    @Override
//    public void verticalFlip(String image, String result) {
//      sb.append("Command: ").append(image).append(" ").append(result).append("\n");
//    }
//
//    @Override
//    public void load(String imgName, InputStream inputStream) {
//      sb.append("Command: ").append(imgName).append("\n");
//    }
//
//
//    @Override
//    public void brighten(String imgName, String destImgName, int increment) {
//      sb.append("Command: ").append(increment).append(" ").append(imgName).append(" ")
//              .append(destImgName).append("\n");
//    }
//
//    @Override
//    public void horizontalFlip(String image, String result) {
//      sb.append("Command: ").append(image).append(" ").append(result).append("\n");
//    }
//
//
//    @Override
//    public void rgbSplit(String image, String redResult, String greenResult, String blueResult) {
//      sb.append("Command: ").append(image).append(" ").append(redResult).append(" ")
//              .append(greenResult).append(" ").append(blueResult).append("\n");
//
//    }
//
//    @Override
//    public void rgbCombine(String redImage, String greenImage,
//                           String blueImage, String resultImage) {
//      sb.append("Command: ").append(resultImage).append(" ").append(redImage).append(" ")
//              .append(greenImage).append(" ").append(blueImage).append("\n");
//    }
//
//    @Override
//    public void blur(String image, String result) {
//      sb.append("Command: ").append(image).append(" ").append(result).append("\n");
//    }
//
//    @Override
//    public void sharpen(String image, String result) {
//      sb.append("Command: ").append(image).append(" ").append(result).append("\n");
//    }
//
//
//  }
//
//}
