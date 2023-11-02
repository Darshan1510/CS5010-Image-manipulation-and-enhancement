# CS5010-Image-manipulation-and-enhancement

## Description:

This Java-based Image Manipulation Application allows users to perform a variety of operations on images, including
loading, saving, and applying various image processing techniques. The application provides a user-friendly interface
for image manipulation and transformation.

## Features:
- Load Images: Easily load images in various formats (e.g., PPM, JPEG, PNG) into the application.
- Save Images: Save the current state of an image as an ImageModel in a specified format.
- Color Transformations: Apply color transformation matrices to change the colors of images.
- Grayscale Conversion: Convert images to grayscale using different color components (e.g., RED, GREEN, BLUE).
- Brightness Adjustment: Adjust pixel values to brighten or darken images.
- Filtering: Apply custom filters to images for effects like blurring, sharpening, and edge detection.
- Flipping: Perform horizontal or vertical flips on images.
- RGB Component Splitting: Split RGB images into their red, green, and blue components.
- RGB Component Combining: Combine red, green, and blue images to create a new RGB image.

## Installation:
1. Clone this repository to your local machine:
   `git clone https://github.com/Darshan1510/CS5010-Image-manipulation-and-enhancement.git`

2. Build the project using your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

3. Run the application.

## Design

We're using the MVC Pattern for our design:

1. The **'Model'** is where our data and its related actions live.
2. The **'Controller'** is like a manager; it talks to the user, the model, 
and the view. Our controller uses the Command Design to work smoothly.
3. Right now, we don't have a specific 'View'. The user just gives commands, 
and the controller handles it using the Command design.

![image-manipulation - MVC Design](res/class_diagram.png)

## Package `image_manipulation`

This is our main package, that implements the given problem in MVC design.
* Package `model` -> Defines our model, which represents an Image processor that will also store
the processed images in a session.
* Package `controller` -> contains our controller, which interacts with the model using the
**Command Design Pattern**.
* Currently, we don't have a dedicated view, but as the application grows and requires more user interaction, 
we'll introduce a separate view package.

### Model

Our primary model functions as an Image Processor. It offers various methods to work on multiple images during 
a session and stores them.

* The `ImageProcessorImpl` class is the practical application of the `ImageProcessor` interface, 
  ensuring operations align with the problem's requirements and serving as the sole model recognized by the controller. 
  * This class facilitates all necessary interactions between the user and controller.
  * It represents the primary interface of the model.
  * Additionally, this main model utilizes the `ImageHelper` from the helpers package to manage various image 
    types and formats for both loading and saving purposes.

#### Package `image`

The `ImageModel` interface defines the properties and methods of an image with customizable pixels based on different 
channels. All image implementations adhere to or extend this interface.
* `RGBPixel` is a class representing a Pixel with red, green, and a blue value it can carry. 
  * This class is derived from the `PixelModel` interface. For future pixel variations, like ARGBPixel with 
    transparency, they can easily branch out from this interface and class.

Such pixels are employed by classes that implement the `ImageModel` interface.
* `ImageModel` interface provides a generic image, represented in terms of pixels. It offers basic
  setters and getters. It offers a range of filters that can be applied on the image.
* The `RGBImage` class showcases an RGB image composed of `RGBPixel` units. Essential attributes include dimensions 
  (height and width) and the maximum value a pixel component can possess. Additionally, it has the capability to apply 
  various filters to itself and return the newer version of the image.

### Controller

The controller in our setup adopts the "Command Design Pattern", aligning seamlessly with the program's needs.

Commands such as load, brighten, red-component, green-component, blue-component, save, run, luma, value, intensity, 
blur, sharpen, rgb-split, rgb-combine and flips (both vertical and horizontal) are treated as individual commands.
Considering the diverse range of commands, we've established an enum to catalog them.

Each of these commands adheres to the `ImageProcessorCommand` interface.

The controller here, follows **"Command Design Pattern"** as it meets the program requirements
perfectly.

Interface `ImageControllerInterface` and its implementation `ImageController`.

* The `ImageController` serves as our primary controller, implementing the `ImageControllerInterface` interface. 
  This interface outlines the "execute" method, which initiates the program.

#### Package `commands`

All our commands for the controller following Command Design pattern is defined here.

* Interface `ImageProcessorCommand`, defines the type of the command, it has only one method to offer,
  `process` that processes the respective command.

**Following classes each act as a command:**

* `Brighten` - brightens an image with the scale provided.
* `VerticalFlip` - flips the image with the vertical orientation.
* `HorizontalFlip` - flips the image with the horizontal orientation.
* `BlueComponent` - converts an input image to greyscale while preserving the blue component.
* `RedComponent` - converts an input image to greyscale while preserving the red component.
* `GreenComponent` - converts an input image to greyscale while preserving the green component.
* `ValueComponent` - converts an input image to greyscale while applying the maximum value of the three component for 
each pixel.
* `LumaComponent` - converts an input image to greyscale while applying the weighted sum using 
`(r'=g'=b'=0.2126r + 0.7152g + 0.0722b)` to each pixel.
* `IntensityComponent` - converts an input image to greyscale while applying the average of the three components 
to each pixel.
* `Load` - helps load a new image into the model.
* `Save` - helps save a new image into disk.
* `RGBCombine` - combines red, green, blue channels of 3 different (generally greyscale) images
  respectively.
* `RGBSplit` - Splits an image into its individual red, green, and blue greyscale components and stores them with 
  the assigned names in memory.
* `Run` - Executes a script containing various commands when given the correct script file path.
* `Blur` - blurs the image using 3*3 kernel.
* `Sharpen` - sharpens the image (sharpens the accentuates edges) using 5*5 kernel.
* `Sepia` - Applies the Sepia color transformation to the image.

### Helpers

A set of tools used by our image manipulation program.
This helper as of now provides the functionality, to read any file, to read an image files of particular type, which is
easy to extend again.

#### Package `image`

Our program utilizes `ImageHelperFactory` and its implementation `ImageHelperFactoryImpl` to determine the appropriate 
class for image IO operations. The `GenericImageHelper` manages reading and saving in **jpg, jpeg, and png formats**, 
while the `PPMImageHelper` specifically handles reading and saving in ppm format. Both these classes adhere to the 
`ImageHelper` interface.

#### Package `file`

The `FileHelper` interface and its corresponding implementation, `FileHelperImpl`, facilitate file reading based on 
the provided file path.

### Running the program.

Navigate to *src -> image_manipulation -> controller -> * `SimpleImageController`  and execute the static main method. 
This action initializes a `ImageController` object and triggers the program by invoking the `execute` method of the 
controller.

#### Accepted commands by the program.

###### Loads a new image named paris-test.png

```
load res/paris-test.png paris
```

###### Stores the output image under the given name

```
save res/paris-save.png paris
```

###### Boosts the brightness of the loaded paris image by a factor of 50

```
brighten 50 paris paris-brighter
```

###### Stores the output image under the given name

```
save res/paris-brighter-50.png paris-brighter
```
###### Darkens the loaded paris image by a factor of 50

```
brighten -50 paris paris-darken
```

###### Stores the output image under the given name

```
save res/paris-darken-50.png paris-darken
```


###### Vertically flips the paris image

```
vertical-flip paris paris-vertical
```

###### Stores the output image under the given name

```
save res/paris-vertical.png paris-vertical
```

###### Horizontally flips the paris-vertical image

```
horizontal-flip paris-vertical paris-vertical-horizontal
```

###### Stores the output image under the given name

```
save res/paris-vertical-horizontal.png paris-vertical-horizontal
```

###### Generates a greyscale image based on the red component

```
red-component paris paris-red
```

###### Stores the output image under the given name

```
save res/paris-red.png paris-red
```

###### Generates a greyscale image based on the green component

```
green-component paris paris-green
```

###### Stores the output image under the given name

```
save res/paris-green.png paris-green
```

###### Generates a greyscale image based on the blue component

```
blue-component paris paris-blue
```

###### Stores the output image under the given name

```
save res/paris-blue.ppm paris-blue
```

###### Generates a greyscale image based on the value component

```
value-component paris paris-value
```

###### Stores the output image under the given name

```
save res/paris-value.png paris-value
```

###### Generates a greyscale image based on the luma component

```
luma-component paris paris-luma
```

###### Stores the output image under the given name

```
save res/paris-luma.png paris-luma
```

###### Generates a greyscale image based on the intensity component

```
intensity-component paris paris-intensity
```

###### Stores the output image under the given name

```
save res/paris-intensity.png paris-intensity
```

###### Divides an image into its individual red, green, and blue greyscale components.

```
rgb-split paris paris-red paris-green paris-blue
```

###### Stores the output image under the given name

```
save res/paris-split-red.ppm paris-red
save res/paris-split-green.ppm paris-green
save res/paris-split-blue.ppm paris-blue
```

###### Merges three greyscale images using their red, green, and blue components.

```
rgb-combine paris-combined paris-red paris-green paris-blue
```

###### Stores the output image under the given name

```
save res/paris-combined.png paris-combined
```

###### Runs commands from a provided script file path

###### Please provide this script file as command line argument for running a script - res/script-test.txt

Any or all of the above valid commands can execute successfully here.

```
run res/script-test.txt
```

#### Exception handling

We handle errors by catching any misspelled commands or invalid arguments. When detected, we provide helpful feedback 
to the user.

### Image Citation:

> Paris image
> ![Paris](res/paris-test.png)

Author: Darshan Jayeshbhai Shah

Date: November 1, 2023.

Source: [Imgur](https://imgur.com).

Terms of usage: [Imgur terms](https://imgur.com/tos)