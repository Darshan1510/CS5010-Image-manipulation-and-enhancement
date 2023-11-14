package ime.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

import ime.model.image.ImageModel;
import ime.model.image.PixelModel;

public class HistogramGenerator {

  private final BufferedImage histogramImage;

  public HistogramGenerator() {
    // Initialize the histogram image with a white background
    this.histogramImage = new BufferedImage(255, 255, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = histogramImage.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, histogramImage.getWidth(), histogramImage.getHeight());

    // Draw grid lines
    g2d.setColor(Color.GRAY);
    for (int i = 0; i < histogramImage.getWidth(); i += 10) {
      g2d.drawLine(i, 0, i, histogramImage.getHeight());
      g2d.drawLine(0, i, histogramImage.getWidth(), i);
    }
    g2d.dispose();
  }

  public BufferedImage createHistogram(ImageModel rgbImage) {
    int[] reds = new int[256];
    int[] greens = new int[256];
    int[] blues = new int[256];

    // Compute the frequency of each intensity level for each color
    for (int i = 0; i < rgbImage.getHeight(); i++) {
      for (int j = 0; j < rgbImage.getWidth(); j++) {
        PixelModel pixel = rgbImage.getPixelValues(i, j);
        reds[pixel.getR()]++;
        greens[pixel.getG()]++;
        blues[pixel.getB()]++;
      }
    }

    int maxFrequency = findMaxFrequency(reds, greens, blues);

    // Draw the histograms
    Graphics2D g = histogramImage.createGraphics();
    drawHistogramLines(g, reds, Color.RED, maxFrequency);
    drawHistogramLines(g, greens, Color.GREEN, maxFrequency);
    drawHistogramLines(g, blues, Color.BLUE, maxFrequency);

    g.dispose();

    return histogramImage;
  }

  private void drawHistogramLines(Graphics2D g, int[] frequencies, Color color, int maxFrequency) {
    g.setColor(color);

    int lastY = histogramImage.getHeight();
    for (int i = 0; i < frequencies.length; i++) {
      int y = histogramImage.getHeight() -
              (frequencies[i] * histogramImage.getHeight() / maxFrequency);
      g.drawLine(i, lastY, i, y);
      lastY = y;
    }
  }

  public static int findMaxFrequency(int[]... arrays) {
    int max = 0;
    for (int[] array : arrays) {
      for (int value : array) {
        if (value > max) {
          max = value;
        }
      }
    }
    return max;
  }
}
