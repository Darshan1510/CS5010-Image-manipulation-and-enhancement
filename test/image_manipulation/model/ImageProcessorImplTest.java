package image_manipulation.model;

import image_manipulation.controller.helpers.image.ImageHelperFactory;
import image_manipulation.controller.helpers.image.ImageHelperFactoryImpl;
import image_manipulation.model.image.ImageModel;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

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
            filePath = "res/paris-test.ppm";
            processor = new ImageProcessorImpl();
            InputStream inputStream = factory.getImageHelper(filePath).readImage(filePath);
            processor.load("test", inputStream);
            image = processor.get("test");
        } catch (Exception e) {
            fail("Failed to instantiate the PPM image");
        }
    }

    @Test
    public void testValidPPM() {
        int lineWithP3 = 1;
        int lineWithHeightWidth = 2;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 1;

            while ((line = br.readLine()) != null) {
                if (currentLine == lineWithP3) {
                    assertEquals("P3", line);
                } else if (currentLine == lineWithHeightWidth) {
                    int width = Integer.parseInt(line.split(" ")[0]);
                    int height = Integer.parseInt(line.split(" ")[1]);
                    assertEquals(width, processor.get("test").getWidth());
                    assertEquals(height, processor.get("test").getHeight());
                }
                currentLine++;
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
