package ime.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ime.controller.Features;
import ime.controller.enums.Command;
import ime.model.ViewModel;
import ime.utils.MessageUtil;

public class ImageManipulatorView extends JFrame implements IView {

  private final ViewModel model;
  private final String RED_COMPONENT = "Red Component";
  private final String GREEN_COMPONENT = "Green Component";
  private final String BLUE_COMPONENT = "Blue Component";
  private final String VALUE_COMPONENT = "Value Component";
  private final String INTENSITY_COMPONENT = "Intensity Component";
  private final String LUMA_COMPONENT = "Luma Component";
  private final String BLUR = "Blur";
  private final String SHARPEN = "Sharpen";

  // View elements
  private final JPanel imagePanel;
  private final JLabel imageLabel;

  private final JLabel histogramLabel;
  private final JMenuItem openItem;
  private final JMenuItem saveItem;
  private final JMenuItem exitItem;
  private final JButton sepiaButton;
  private final JButton rgbSplitButton;
  private final JButton rgbCombineButton;
  private final JButton compressButton;
  private final JButton colorCorrectButton;
  private final JButton levelAdjustButton;
  private final JButton horizontalFlipButton;
  private final JButton verticalFlipButton;
  private final JButton brightenButton;
  private final FileNameExtensionFilter filter;
  private final JComboBox<String> filterTypes;

  private final JComboBox<String> greyscaleTypes;
  private final JButton filterTypesButton;
  private final JButton componentExecuteButton;
  private final JButton greyscaleExecuteButton;
  private final JComboBox<String> componentTypes;
  private final JTextField brightnessValue;
  private final JTextField compressValue;
  private final JTextField blackLevelAdjustValue;
  private final JTextField midLevelAdjustValue;
  private final JTextField whiteLevelAdjustValue;
  private final JTextField splitPercentageValue;
  private final JToggleButton splitToggleButton;
  private final JButton splitButton;
  private final JPanel mainPanel;
  private String selectedComponent;
  private String selectedFilter;
  private String selectedGreyscale;
  private Command currectCommand;

  public ImageManipulatorView(String caption, ViewModel model) {
    super(caption);
    this.model = model;

    filter = new FileNameExtensionFilter("JPG, PNG, & PPM Images",
            "jpg", "png", "ppm");

    // View items
    JMenuBar menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);

    openItem = new JMenuItem("Open...");
    saveItem = new JMenuItem("Save As");
    exitItem = new JMenuItem("Exit");

    fileMenu.add(openItem);
    fileMenu.add(saveItem);
    fileMenu.add(exitItem);

    menuBar.add(fileMenu);

    setJMenuBar(menuBar);

    // Create main panel with GridBagLayout
    mainPanel = new JPanel(new GridBagLayout());

    // Create mainScreen with GridBagLayout
    JPanel mainScreen = new JPanel(new GridBagLayout());

    // Add mainScreen to main panel with GridBagConstraints.
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 1.0;
    c.fill = GridBagConstraints.BOTH;
    c.insets = new java.awt.Insets(5, 2, 5, 2);

    c.gridx = 0;
    c.gridy = 1;
    c.weighty = 0.95;
    mainPanel.add(mainScreen, c);

    // Create leftScreen with GridBagLayout.
    JPanel leftScreen = new JPanel(new GridBagLayout());

    // Create rightScreen with GridBagLayout.
    JPanel rightScreen = new JPanel(new GridBagLayout());

    GridBagConstraints leftConstraints = new GridBagConstraints();
    leftConstraints.gridx = 0;
    leftConstraints.gridy = 0;
    leftConstraints.weightx = 0.2;
    leftConstraints.weighty = 1;
    leftConstraints.fill = GridBagConstraints.BOTH;

    // Set the layout for the leftScreen JPanel
    leftScreen.setLayout(new BoxLayout(leftScreen, BoxLayout.Y_AXIS));

    JPanel operationPanel = new JPanel();
    operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.Y_AXIS));

    JPanel splitViewPanel = new JPanel(new GridBagLayout());
    splitViewPanel.setBorder(BorderFactory.createTitledBorder("Split view"));
    GridBagConstraints splitViewPanelConstraints = new GridBagConstraints();
    splitViewPanelConstraints.anchor = GridBagConstraints.CENTER;
    splitViewPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
    splitViewPanelConstraints.weightx = 1;
    splitViewPanelConstraints.weighty = 1;
    splitViewPanelConstraints.insets = new Insets(3, 3, 3, 3);

    splitToggleButton = new JToggleButton("Enable");
    splitToggleButton.setToolTipText("Enable split");

    splitViewPanel.add(splitToggleButton, splitViewPanelConstraints);

    splitPercentageValue = new JTextField("50", 3);
    splitViewPanelConstraints.gridx = 1;
    splitViewPanelConstraints.gridy = 0;
    splitViewPanel.add(splitPercentageValue, splitViewPanelConstraints);

    splitButton = new JButton("View");
    splitButton.setToolTipText("Apply split");
    splitViewPanelConstraints.gridx = 2;
    splitViewPanelConstraints.gridy = 0;
    splitViewPanel.add(splitButton, splitViewPanelConstraints);

    operationPanel.add(splitViewPanel);

    JPanel colorTransformAndFilterPanel = new JPanel();
    colorTransformAndFilterPanel.setBorder(
            BorderFactory.createTitledBorder("Color transform & Filters"));

    JPanel colorTransformAndFilterControlsPanel = new JPanel();
    colorTransformAndFilterControlsPanel.setLayout(new GridBagLayout());

    GridBagConstraints controlsPanelConstraints = new GridBagConstraints();
    controlsPanelConstraints.anchor = GridBagConstraints.CENTER;
    controlsPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
    controlsPanelConstraints.weightx = 1;
    controlsPanelConstraints.weighty = 1;
    controlsPanelConstraints.insets = new Insets(3, 3, 3, 3);

    JLabel greyscaleTypesLabel = new JLabel("Greyscale:");
    controlsPanelConstraints.gridx = 0;
    controlsPanelConstraints.gridy = 0;
    colorTransformAndFilterControlsPanel.add(greyscaleTypesLabel, controlsPanelConstraints);

    greyscaleTypes = new JComboBox<>(new String[]{"Select Greyscale",
            LUMA_COMPONENT, INTENSITY_COMPONENT, VALUE_COMPONENT});
    greyscaleTypes.setToolTipText("Select Greyscale");

    // Add action listener to track the change
    greyscaleTypes.addActionListener(e -> this.selectedGreyscale =
            (String) greyscaleTypes.getSelectedItem());

    controlsPanelConstraints.gridx = 1;
    controlsPanelConstraints.gridy = 0;
    colorTransformAndFilterControlsPanel.add(greyscaleTypes, controlsPanelConstraints);

    greyscaleExecuteButton = new JButton("Execute");
    greyscaleExecuteButton.setToolTipText("Execute the selected greyscale type operation");
    controlsPanelConstraints.gridx = 2;
    controlsPanelConstraints.gridy = 0;
    colorTransformAndFilterControlsPanel.add(greyscaleExecuteButton, controlsPanelConstraints);

    JLabel componentTypesLabel = new JLabel("Component:");
    controlsPanelConstraints.gridx = 0;
    controlsPanelConstraints.gridy = 1;
    colorTransformAndFilterControlsPanel.add(componentTypesLabel, controlsPanelConstraints);

    componentTypes = new JComboBox<>(new String[]{"Select Component",
            RED_COMPONENT, GREEN_COMPONENT, BLUE_COMPONENT});
    componentTypes.setToolTipText("Select Component");

    // Add action listener to track the change
    componentTypes.addActionListener(e -> this.selectedComponent =
            (String) componentTypes.getSelectedItem());

    controlsPanelConstraints.gridx = 1;
    controlsPanelConstraints.gridy = 1;
    colorTransformAndFilterControlsPanel.add(componentTypes, controlsPanelConstraints);

    componentExecuteButton = new JButton("Execute");
    componentExecuteButton.setToolTipText("Execute the selected component type operation");
    controlsPanelConstraints.gridx = 2;
    controlsPanelConstraints.gridy = 1;
    colorTransformAndFilterControlsPanel.add(componentExecuteButton, controlsPanelConstraints);

    JLabel filterTypesLabel = new JLabel("Filter:");
    controlsPanelConstraints.gridx = 0;
    controlsPanelConstraints.gridy = 2;
    colorTransformAndFilterControlsPanel.add(filterTypesLabel, controlsPanelConstraints);

    filterTypes = new JComboBox<>(new String[]{"Select Filter", BLUR, SHARPEN});
    filterTypes.setToolTipText("Select Filter");

    // Add action listener to track the change
    filterTypes.addActionListener(e -> this.selectedFilter =
            (String) filterTypes.getSelectedItem());

    controlsPanelConstraints.gridx = 1;
    controlsPanelConstraints.gridy = 2;
    colorTransformAndFilterControlsPanel.add(filterTypes, controlsPanelConstraints);

    filterTypesButton = new JButton("Execute");
    filterTypesButton.setToolTipText("Execute the selected filter operation");
    controlsPanelConstraints.gridx = 2;
    controlsPanelConstraints.gridy = 2;
    colorTransformAndFilterControlsPanel.add(filterTypesButton, controlsPanelConstraints);

    // Add the color transformation and filter panel to left screen
    colorTransformAndFilterPanel.add(colorTransformAndFilterControlsPanel);

    operationPanel.add(colorTransformAndFilterPanel, BorderLayout.CENTER);

    JPanel basicOperationsPanel = new JPanel(new GridBagLayout());
    basicOperationsPanel.setBorder(BorderFactory.createTitledBorder("Basic Operations"));
    GridBagConstraints basicOperationsPanelConstraints = new GridBagConstraints();
    basicOperationsPanelConstraints.anchor = GridBagConstraints.CENTER;
    basicOperationsPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
    basicOperationsPanelConstraints.weightx = 1;
    basicOperationsPanelConstraints.weighty = 1;
    basicOperationsPanelConstraints.insets = new Insets(3, 3, 3, 3);

    brightnessValue = new JTextField("Enter value", 3);
    basicOperationsPanelConstraints.gridx = 0;
    basicOperationsPanelConstraints.gridy = 1;
    basicOperationsPanel.add(brightnessValue, basicOperationsPanelConstraints);

    brightenButton = new JButton("Adjust Brightness");
    brightenButton.setToolTipText("Apply brightness");
    basicOperationsPanelConstraints.gridx = 1;
    basicOperationsPanelConstraints.gridy = 1;
    basicOperationsPanel.add(brightenButton, basicOperationsPanelConstraints);

    compressValue = new JTextField("Enter value", 3);
    basicOperationsPanelConstraints.gridx = 0;
    basicOperationsPanelConstraints.gridy = 2;
    basicOperationsPanel.add(compressValue, basicOperationsPanelConstraints);

    compressButton = new JButton("Compress");
    compressButton.setToolTipText("Compress image");
    basicOperationsPanelConstraints.gridx = 1;
    basicOperationsPanelConstraints.gridy = 2;
    basicOperationsPanel.add(compressButton, basicOperationsPanelConstraints);

    rgbSplitButton = new JButton("RGB Split");
    rgbSplitButton.setToolTipText("Split the RGB Channels");
    basicOperationsPanelConstraints.gridx = 0;
    basicOperationsPanelConstraints.gridy = 3;
    basicOperationsPanel.add(rgbSplitButton, basicOperationsPanelConstraints);

    rgbCombineButton = new JButton("RGB Combine");
    rgbCombineButton.setToolTipText("Combine the RGB Channels");
    basicOperationsPanelConstraints.gridx = 1;
    basicOperationsPanelConstraints.gridy = 3;
    basicOperationsPanel.add(rgbCombineButton, basicOperationsPanelConstraints);

    horizontalFlipButton = new JButton("Horizontal Flip");
    horizontalFlipButton.setToolTipText("Apply horizontal flip");
    basicOperationsPanelConstraints.gridx = 0;
    basicOperationsPanelConstraints.gridy = 4;
    basicOperationsPanel.add(horizontalFlipButton, basicOperationsPanelConstraints);

    verticalFlipButton = new JButton("Vertical Flip");
    verticalFlipButton.setToolTipText("Apply vertical flip");
    basicOperationsPanelConstraints.gridx = 1;
    basicOperationsPanelConstraints.gridy = 4;
    basicOperationsPanel.add(verticalFlipButton, basicOperationsPanelConstraints);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setToolTipText("Apply Sepia Filter");
    basicOperationsPanelConstraints.gridx = 0;
    basicOperationsPanelConstraints.gridy = 5;
    basicOperationsPanel.add(sepiaButton, basicOperationsPanelConstraints);

    colorCorrectButton = new JButton("Color correct");
    colorCorrectButton.setToolTipText("Apply Color correction");
    basicOperationsPanelConstraints.gridx = 1;
    basicOperationsPanelConstraints.gridy = 5;
    basicOperationsPanel.add(colorCorrectButton, basicOperationsPanelConstraints);

    operationPanel.add(basicOperationsPanel);

    JPanel levelAdjustPanel = new JPanel(new GridBagLayout());
    levelAdjustPanel.setBorder(BorderFactory.createTitledBorder("Level adjust operation"));
    GridBagConstraints levelAdjustPanelConstraints = new GridBagConstraints();
    levelAdjustPanelConstraints.anchor = GridBagConstraints.CENTER;
    levelAdjustPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
    levelAdjustPanelConstraints.weightx = 1;
    levelAdjustPanelConstraints.weighty = 1;
    levelAdjustPanelConstraints.insets = new Insets(3, 3, 3, 3);

    blackLevelAdjustValue = new JTextField("Enter black", 3);
    controlsPanelConstraints.gridx = 1;
    controlsPanelConstraints.gridy = 0;
    levelAdjustPanel.add(blackLevelAdjustValue, levelAdjustPanelConstraints);

    midLevelAdjustValue = new JTextField("Enter mid", 3);
    controlsPanelConstraints.gridx = 2;
    controlsPanelConstraints.gridy = 0;
    levelAdjustPanel.add(midLevelAdjustValue, levelAdjustPanelConstraints);

    whiteLevelAdjustValue = new JTextField("Enter white", 3);
    controlsPanelConstraints.gridx = 3;
    controlsPanelConstraints.gridy = 0;
    levelAdjustPanel.add(whiteLevelAdjustValue, levelAdjustPanelConstraints);

    levelAdjustButton = new JButton("Level adjust");
    levelAdjustButton.setToolTipText("Apply level adjust");
    controlsPanelConstraints.gridx = 4;
    controlsPanelConstraints.gridy = 0;
    levelAdjustPanel.add(levelAdjustButton, levelAdjustPanelConstraints);

    operationPanel.add(levelAdjustPanel, BorderLayout.AFTER_LAST_LINE);

    this.enableOperationButtons(false);
    splitPercentageValue.setEnabled(false);
    splitButton.setEnabled(false);

    // Cannot save an image at start
    saveItem.setEnabled(false);

    leftScreen.add(operationPanel);

    JScrollPane operationPanelScroller = new JScrollPane(operationPanel);
    operationPanelScroller
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    leftScreen.add(operationPanelScroller, BorderLayout.CENTER);

    histogramLabel = new JLabel();

    JPanel histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.setSize(400, 500);
    histogramPanel.add(histogramLabel);
    histogramPanel.setVisible(true);

    leftScreen.add(histogramPanel);

    mainScreen.add(leftScreen, leftConstraints);

    GridBagConstraints rightConstraints = new GridBagConstraints();
    rightConstraints.gridx = 1;
    rightConstraints.gridy = 0;
    rightConstraints.weightx = 0.8;
    rightConstraints.weighty = 1;
    rightConstraints.fill = GridBagConstraints.BOTH;

    rightScreen.setLayout(new BorderLayout());

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image preview"));

    JScrollPane scroller = new JScrollPane(imagePanel);
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    // Add the imagePanel to the center of the rightScreen
    rightScreen.add(scroller, BorderLayout.CENTER);

    // Main image

    imageLabel = new JLabel("Please load an image\n" + " File > Open…");
    imagePanel.add(imageLabel);

    mainScreen.add(rightScreen, rightConstraints);

    // Add main panel to frame
    add(mainPanel);

    // Set frame properties
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setPreferredSize(new Dimension(1500, 800));
    setMinimumSize(new Dimension(1200, 500));
    pack();
    setVisible(true);
  }

  private void enableOperationButtons(boolean enable) {
    brightenButton.setEnabled(enable);
    brightnessValue.setEnabled(enable);
    horizontalFlipButton.setEnabled(enable);
    verticalFlipButton.setEnabled(enable);
    greyscaleTypes.setEnabled(enable);
    greyscaleExecuteButton.setEnabled(enable);
    filterTypes.setEnabled(enable);
    filterTypesButton.setEnabled(enable);
    sepiaButton.setEnabled(enable);
    rgbSplitButton.setEnabled(enable);
    rgbCombineButton.setEnabled(enable);
    componentTypes.setEnabled(enable);
    colorCorrectButton.setEnabled(enable);
    levelAdjustButton.setEnabled(enable);
    blackLevelAdjustValue.setEnabled(enable);
    midLevelAdjustValue.setEnabled(enable);
    whiteLevelAdjustValue.setEnabled(enable);
    componentExecuteButton.setEnabled(enable);
    compressValue.setEnabled(enable);
    compressButton.setEnabled(enable);
    splitToggleButton.setEnabled(enable);
  }

  @Override
  public void refreshView(String imageName, String histogram) {
    try {
      model.processImage(imageName);
      Image bufferedImage = model.getImage();
      // Reset the current content of the label
      imageLabel.setText("");
      imageLabel.setIcon(new ImageIcon(bufferedImage));

      // process the histogram
      model.processImage(histogram);
      Image histogramBufferedImage = model.getImage();
      this.histogramLabel.setIcon(new ImageIcon(histogramBufferedImage));

    } catch (IOException | InputMismatchException e) {
      showErrorMessage("Unable to display the image. Please provide a valid file.");
    }
  }

  @Override
  public void splitView(String splitImageName) {
    try {
      model.processImage(splitImageName);
      Image bufferedImage = model.getImage();
      // Reset the current content of the label
      imageLabel.setText("");
      imageLabel.setIcon(new ImageIcon(bufferedImage));

    } catch (IOException | InputMismatchException e) {
      showErrorMessage("Unable to display the split view. Please provide a valid file.");
    }
  }

  private void component(Features features) {
    try {
      switch (selectedComponent) {
        case RED_COMPONENT:
          features.redComponent();
          break;
        case BLUE_COMPONENT:
          features.blueComponent();
          break;
        case GREEN_COMPONENT:
          features.greenComponent();
          break;
        default:
          throw new InputMismatchException();
      }
      componentTypes.setSelectedItem(" Select Component ");
    } catch (IOException | InputMismatchException | NullPointerException e) {
      showErrorMessage("Please select a valid Component");
      componentTypes.setSelectedItem("=== Select Component ===");
    }
  }

  private void filter(Features features) {
    try {
      switch (selectedFilter) {
        case BLUR:
          features.blur();
          break;
        case SHARPEN:
          features.sharpen();
          break;
        default:
          throw new InputMismatchException();
      }
      filterTypes.setSelectedItem(" Select Filter ");
    } catch (IOException | InputMismatchException | NullPointerException e) {
      showErrorMessage("Please select a valid filter");
      filterTypes.setSelectedItem(" Select Filter ");
    }
  }

  private void loadImage(Features features) {
    final JFileChooser fChooser = new JFileChooser();
    fChooser.setFileFilter(filter);
    int retValue = fChooser.showOpenDialog(this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();

      try {
        features.load(f.getPath());
        imagePanel.setBorder(BorderFactory.createTitledBorder(f.getPath()));

        // Enabling all the buttons for operations
        this.enableOperationButtons(true);

        // Save will work after loading the image
        saveItem.setEnabled(true);
        this.allowSplitView(Command.LOAD);
        displaySuccessMessage(mainPanel, "Successfully loaded the image.");
      } catch (IOException | InputMismatchException e) {
        showErrorMessage("Please provide the valid file in input!");
      }
    }
  }

  /**
   * A utility method that displays a file chooser dialog for loading files
   * and allows setting a default filename.
   *
   * @param defaultFilename The default filename to be pre-selected in the file chooser.
   * @return The absolute path of the selected file if chosen; otherwise, an empty string.
   */
  private String openFilePathSelector(String defaultFilename) {
    // Create a file chooser dialog
    JFileChooser fileChooser = new JFileChooser();

    // Set a file filter if needed (replace 'filter' with the actual file filter)
    fileChooser.setFileFilter(filter);

    // Set the default file name
    File defaultFile = new File(defaultFilename);
    fileChooser.setSelectedFile(defaultFile);

    int result = fileChooser.showOpenDialog(this);
    // Check if the user selected a file
    if (result == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return "";
  }

  private void rgbCombine(Features features) {
    String message = "This operation requires 3 images which are red, green and "
            + "blue component images respectively that you want to combine.";
    JOptionPane.showMessageDialog(mainPanel, message, "RGB Combine",
            JOptionPane.INFORMATION_MESSAGE);

    String filepathRed;
    String filepathGreen = "";
    String filepathBlue = "";

    filepathRed = openFilePathSelector("red-split.png");
    if (!filepathRed.isEmpty()) {
      filepathGreen = openFilePathSelector("green-split.png");
      if (!filepathGreen.isEmpty()) {
        filepathBlue = openFilePathSelector("blue-split.png");
      }
    }

    // After receiving all the required paths, we will call rgbCombine from the features
    if (!filepathRed.isEmpty() && !filepathGreen.isEmpty() && !filepathBlue.isEmpty()) {
      try {
        features.rgbCombine(filepathRed, filepathGreen, filepathBlue);
        this.allowSplitView(Command.RGB_COMBINE);
      } catch (IOException | InputMismatchException e) {
        showErrorMessage(MessageUtil.getInvalidFilePathErrorMessage());
      }
    } else {
      showErrorMessage(MessageUtil.getInvalidFilePathErrorMessage());
    }
  }

  private String openFileSaver(String defaultFilename) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(filter);
    // Setting the default file name
    File defaultFile = new File(defaultFilename);
    fileChooser.setSelectedFile(defaultFile);
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return "";
  }

  private void rgbSplit(Features features) {
    String message = "This operation requires 3 locations for the splits to "
            + "be saved, for red, green and blue splits respectively.";
    JOptionPane.showMessageDialog(mainPanel, message, "RGB Split",
            JOptionPane.INFORMATION_MESSAGE);

    String filepathRed;
    String filepathGreen = "";
    String filepathBlue = "";

    filepathRed = openFileSaver("red-split.png");
    if (!filepathRed.isEmpty()) {
      filepathGreen = openFileSaver("green-split.png");
      if (!filepathGreen.isEmpty()) {
        filepathBlue = openFileSaver("blue-split.png");
      }
    }

    // After receiving all the required paths, we will call rgbSplit from the features
    if (!filepathRed.isEmpty() && !filepathGreen.isEmpty() && !filepathBlue.isEmpty()) {
      try {
        features.rgbSplit(filepathRed, filepathGreen, filepathBlue);
        this.allowSplitView(Command.RGB_SPLIT);
      } catch (IOException e) {
        showErrorMessage(MessageUtil.getInvalidFilePathErrorMessage());
      } catch (InputMismatchException e) {
        showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
      }
    } else {
      showErrorMessage(MessageUtil.getInvalidFilePathErrorMessage());
    }
  }

  private void displaySuccessMessage(Component parentFrame, String informMessage) {
    JOptionPane.showMessageDialog(parentFrame, informMessage, "Success",
            JOptionPane.INFORMATION_MESSAGE);
  }

  private void saveImage(Features features) {
    final JFileChooser fChooser = new JFileChooser();
    int retValue = fChooser.showSaveDialog(this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      //Please validate the file format
      try {
        String path = f.getAbsolutePath();
        features.save(path);
        this.allowSplitView(Command.SAVE);
        displaySuccessMessage(mainPanel, "Successfully saved the image.");
      } catch (IOException | InputMismatchException e) {
        showErrorMessage("Unable to save the image. Please provide a valid path and try again");
      }
    }
  }

  private void allowSplitView(Command currectCommand) {
//    final List<Command> splitViewAllowedCmds = List.of(Command.SHARPEN, Command.BLUR,
//            Command.SEPIA, Command.LUMA_COMPONENT, Command.INTENSITY_COMPONENT,
//            Command.VALUE_COMPONENT, Command.COLOR_CORRECT, Command.LEVEL_ADJUST);
//    if (splitViewAllowedCmds.contains(currectCommand)) {
//      splitPercentageValue.setEnabled(true);
//      splitToggleButton.setEnabled(true);
//      this.currectCommand = currectCommand;
//    } else {
//      splitPercentageValue.setEnabled(false);
//      splitToggleButton.setEnabled(false);
//    }
  }

  private void sepia(Features features) {
    try {
      features.sepia();
    } catch (IOException | InputMismatchException e) {
      showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
    }
  }

  private void greyscale(Features features) {
    try {
      switch (selectedGreyscale) {
        case VALUE_COMPONENT:
          features.valueGreyscale();
          this.allowSplitView(Command.VALUE_COMPONENT);
          break;
        case INTENSITY_COMPONENT:
          features.intensityGreyscale();
          this.allowSplitView(Command.INTENSITY_COMPONENT);
          break;
        case LUMA_COMPONENT:
          features.lumaGreyscale();
          this.allowSplitView(Command.LUMA_COMPONENT);
          break;
        default:
          throw new InputMismatchException();
      }
      filterTypes.setSelectedItem("=== Select Greyscale ===");
    } catch (IOException | InputMismatchException | NullPointerException e) {
      showErrorMessage("Please select a valid Greyscale");
      filterTypes.setSelectedItem("=== Select Greyscale ===");
    }
  }

  private void horizontalFlip(Features features) {
    try {
      features.horizontalFlip();
      this.allowSplitView(Command.HORIZONTAL_FLIP);
    } catch (IOException | InputMismatchException e) {
      showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
    }
  }

  private void verticalFlip(Features features) {
    try {
      features.verticalFlip();
      this.allowSplitView(Command.VERTICAL_FLIP);
    } catch (IOException | InputMismatchException e) {
      showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
    }
  }

  private void brighten(Features features) {
    try {
      int value = Integer.parseInt(brightnessValue.getText());
      features.brighten(value);
      this.allowSplitView(Command.BRIGHTEN);
      brightnessValue.setText("");
    } catch (IOException | InputMismatchException e) {
      showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
      brightnessValue.setText("");
    } catch (NumberFormatException e) {
      showErrorMessage("Invalid brighten value. It should be a numeric value");
      brightnessValue.setText("");
    }
  }

  private void compress(Features features) {
    try {
      double value = Double.parseDouble(compressValue.getText());
      features.compress(value);
      compressValue.setText("");
      this.allowSplitView(Command.COMPRESS);
    } catch (IOException | InputMismatchException e) {
      showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
      compressValue.setText("");
    } catch (NumberFormatException e) {
      showErrorMessage("Invalid compress value. It should be a numeric value");
      compressValue.setText("");
    }
  }

  private void colorCorrect(Features features) {
    try {
      features.colorCorrect();
      this.allowSplitView(Command.COLOR_CORRECT);
    } catch (IOException | InputMismatchException e) {
      showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
    }
  }

  private void levelAdjust(Features features) {
    try {
      int black = Integer.parseInt(blackLevelAdjustValue.getText());
      int mid = Integer.parseInt(midLevelAdjustValue.getText());
      int white = Integer.parseInt(whiteLevelAdjustValue.getText());
      features.levelAdjust(black, mid, white);
      blackLevelAdjustValue.setText("");
      midLevelAdjustValue.setText("");
      whiteLevelAdjustValue.setText("");
      this.allowSplitView(Command.LEVEL_ADJUST);
    } catch (IOException | InputMismatchException e) {
      showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
      blackLevelAdjustValue.setText("");
      midLevelAdjustValue.setText("");
      whiteLevelAdjustValue.setText("");
    } catch (NumberFormatException e) {
      showErrorMessage("Invalid black, mid or white value. It should be a numeric value");
      compressValue.setText("");
    }
  }

  private void split(Features features) {
   // features.split(Command.SEPIA, this.getSplitPercentage());
  }

  private double getSplitPercentage() {
    try {
      double value = Double.parseDouble(splitPercentageValue.getText());
      splitPercentageValue.setText("");
      return value;
    } catch (NumberFormatException e) {
      showErrorMessage("Invalid split percentage value. It should be a numeric value");
      splitPercentageValue.setText("");
    }
    return 0;
  }

  private void toggleSplitView(ActionEvent event, Features features) {
    try {
      AbstractButton abstractButton = (AbstractButton) event.getSource();
      // return true or false according to the selection or deselection of the button
      boolean selected = abstractButton.getModel().isSelected();
      if (selected) {
        splitPercentageValue.setEnabled(true);
        splitButton.setEnabled(true);
        sepiaButton.setEnabled(true);

      } else {
        splitPercentageValue.setEnabled(false);
        splitButton.setEnabled(false);
        features.reloadImage();
      }
    } catch (IOException | InputMismatchException e) {
      showErrorMessage(MessageUtil.getPerformOperationErrorMessage());
    }
  }

  @Override
  public void addFeatures(Features features) {
    rgbCombineButton.addActionListener(event -> rgbCombine(features));

    componentExecuteButton.addActionListener(event -> component(features));

    filterTypesButton.addActionListener(event -> filter(features));

    horizontalFlipButton.addActionListener(event -> horizontalFlip(features));

    verticalFlipButton.addActionListener(event -> verticalFlip(features));

    brightenButton.addActionListener(event -> brighten(features));

    rgbSplitButton.addActionListener(event -> rgbSplit(features));

    exitItem.addActionListener(event -> features.exitProgram());

    openItem.addActionListener(event -> loadImage(features));

    saveItem.addActionListener(event -> saveImage(features));

    sepiaButton.addActionListener(event -> sepia(features));

    compressButton.addActionListener(event -> compress(features));

    levelAdjustButton.addActionListener(event -> levelAdjust(features));

    colorCorrectButton.addActionListener(event -> colorCorrect(features));

    greyscaleExecuteButton.addActionListener(event -> greyscale(features));

    splitToggleButton.addActionListener(event -> toggleSplitView(event, features));

    splitButton.addActionListener(event -> split(features));
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(mainPanel, error, "Error occurred",
            JOptionPane.ERROR_MESSAGE);
  }
}
