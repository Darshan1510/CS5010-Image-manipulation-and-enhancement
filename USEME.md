## Commands that are supported by our application and its usage.

### To run the script file in command-line

* Open terminal/cmd at the location of jar file : `res` ->
  `ImageProcessing.jar`<br>
* Run `java -jar ImageProcessing.jar -file scriptV1.txt`

The above step runs all the commands that are supported by the applications and operates
on the paris image and saves all the resulting images in the res folder.

### Running the project interactively.

#### Method 1. To run the project from jar file.

* open terminal/cmd at location where the jar file is present : `res` ->
  `ImageProcessing.jar`<br>
* Run `java -jar ImageProcessing.jar`

#### Method 2. To run the project in IDE or code.

* In `src`
* Open file `SimpleImageController.java`
* Run the `public static void main` method

##### Command to run a set of commands in a file.

```
run scriptV1.txt
```

When executing the script mentioned earlier, all commands are executed, mirroring the previous 
demonstration. However, this time, the script runs interactively within the program.

##### Commands to `load` a file.

```
load paris.png paris
```

##### Commands to `save` a file.

```
save paris.png paris-png
```

##### Commands to `rgb-split` the file `paris.png`.And save the file in any of the supportedformats.

```
rgb-split paris-ppm paris-red paris-green paris-blue
save paris-green-split.jpg paris-green
```

##### Commands to `rgb-combine` the three red, green, and blue image. And save the file in any of the supported formats.

```
rgb-combine paris-combine paris-red paris-green paris-blue
save paris-combine.png paris-tint
```

##### Commands to `brighten` the file `paris.ppm`. And save the file in any of the supported formats.

```
brighten 50 paris paris-brighten
save paris-brighten.png paris-brighten
```

```
brighten -50 paris paris-darken
save paris-darken.png paris-darken
```

##### Commands to `greyscale` the file `paris.png` into all component. And save the file in any of the supported formats.

```
value-component paris paris-value
save paris-value.png paris-value

luma-component paris paris-luma
save paris-luma.jpg paris-luma

intensity-component paris paris-intensity
save paris-intensity.png paris-intensity

```

##### Commands to apply `component` (Red, Green and Blue) the file `paris.png`. And save the file in any of the supported formats.

```

red-component paris paris-red
save paris-red.ppm paris-red

green-component paris paris-green
save paris-green.png paris-green

blue-component paris paris-blue
save paris-blue.jpg paris-blue
```

##### Commands to `flip` (Both vertically and horizontally) the file `paris.png`. And save the file in any of the supported formats.

```
horizontal-flip paris paris-horizontal
save paris-horizontal.png paris-horizontal

vertical-flip paris-horizontal paris-horizontal-vertical
save paris-horizontal-vertical.png paris-horizontal-vertical

vertical-flip paris paris-vertical
save paris-vertical.jpg paris-vertical
```

##### Commands to `blur` the file `paris.png`. And save the file in any of the supported formats.

```
blur paris paris-blur
save paris.ppm paris-blur
```

##### Commands to `sharpen` the file `paris.png`. And save the file in any of the supported formats.

```
sharpen paris paris-sharpen
save paris-sharpen.png paris-sharpen
```

##### Commands to apply color transformations to create `sepia-tone` of the file `paris.jpg`. And save the file in any of the supported formats.

```
sepia paris paris-sepia
save paris-sepia.jpg paris-sepia
```

##### Commands to `compress` the file `paris.png`. And save the file in any of the supported formats.

```
compress 50 paris paris-50-compress
compress 90 paris paris-90-compress
save paris-50-compress.png paris-50-compress
save paris-90-compress.png paris-90-compress
```

##### Commands to `color-correct` the file `paris.png`. And save the file in any of the supported formats.

```
color-correct paris paris-cc
save paris-cc.png paris-cc
```


##### Commands to `levels-adjust` the file `paris.png`. And save the file in any of the supported formats.

```
levels-adjust 20 120 255 paris paris-adjust-1
levels-adjust 30 100 240 paris paris-adjust-2
save paris-adjust-1.png paris-adjust-1
save paris-adjust-2.png paris-adjust-2
```

##### Commands to perform `split-view-operations` the file `paris.png`. And save the file in any of the supported formats.

```
sepia paris paris-sepia-split split 50
sharpen paris paris-sharpen-split split 30
save paris-sepia-split.png paris-sepia-split
save paris-sharpen-split.png paris-sharpen-split
```

##### Commands to get histogram of every image generated from `paris.jpg`. And save the file in any of the supported formats.

```
histogram paris paris-histo
save paris-histo.png paris-histo

histogram paris-cc paris-cc-histo
save paris-cc-histo.png paris-cc-histo

histogram paris-adjust-1 paris-adjust-1-histo
save paris-adjust-1-histo.png paris-adjust-1-histo
```

