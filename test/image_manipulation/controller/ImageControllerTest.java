package image_manipulation.controller;

import image_manipulation.model.ImageProcessor;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the SimpleImageController
 */
public class ImageControllerTest {
    private ImageControllerInterface controller;
    private Reader in;
    private Appendable out;

    @Test
    public void testBrighten() throws IOException {
        StringBuilder logger = new StringBuilder();
        ImageProcessor mockModel = new MockModel(logger);

        String testCommand = "brighten";
        String testArguments = " 50 test test-brighter";

        in = new StringReader(testCommand + testArguments);
        out = new StringWriter();

        controller = new ImageController(in, out);
        controller.execute(mockModel);

        assertEquals(logger.toString(), "Command:" + testArguments);
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
            sb.append("Input: ").append(imageName);
            // Return does not matter as this is a controller test.
            String newImage = "2 2 230 10 10 15 90 10 12 10 10 15 12 10 10";
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
