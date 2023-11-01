//import static org.junit.Assert.assertEquals;
//
//import image_manipulation.model.ImageProcessor;
//import image_manipulation.model.ImageProcessorImpl;
//import image_manipulation.model.enums.Component;
//import image_manipulation.model.image.ImageModel;
//import image_manipulation.model.image.PixelModel;
//import image_manipulation.model.image.RGBImage;
//import image_manipulation.model.image.RGBPixel;
//import org.junit.Before;
//import org.junit.Test;
//
//public class ImageProcessorImplTest {
//
//    private ImageProcessor imageProcessor;
//
//    @Before
//    public void setUp() {
//        imageProcessor = new ImageProcessorImpl();
//    }
//
//    @Test
//    public void testGrayscaleValidInput() {
//        // Load an image with known colors
//        ImageModel inputImage = new RGBImage(2, 2, new RGBPixel[][]{
//                {new RGBPixel(255, 0, 0), new RGBPixel(0, 255, 0)},
//                {new RGBPixel(0, 0, 255), new RGBPixel(128, 128, 128)}
//        });
//
//        imageProcessor.load("inputImage", inputImage);
//
//        // Apply grayscale conversion
//        imageProcessor.grayscale("inputImage", "outputImage", Component.LUMA);
//
//        // Check the result
//        ImageModel result = imageProcessor.get("outputImage");
//
//        // Verify that the result is of the same dimensions as the input
//        assertEquals(2, result.getHeight());
//        assertEquals(2, result.getWidth());
//
//        // Verify that the resulting pixels are grayscale
//        for (int i = 0; i < result.getHeight(); i++) {
//            for (int j = 0; j < result.getWidth(); j++) {
//                PixelModel pixel = result.getPixelValues(i, j);
//                assertEquals(pixel.getR(), pixel.getG());
//                assertEquals(pixel.getG(), pixel.getB());
//            }
//        }
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testGrayscaleInvalidInput() {
//        // Try to apply grayscale to an image that does not exist
//        imageProcessor.grayscale("nonExistentImage", "outputImage", Component.LUMA);
//    }
//}