# JfxImageCanvas
JfxImageCanvas is a JavaFX Class that can be used to render a scaled and rotated image in a canvas.

The image can be rotated by multiples of 90 degree and can be sized by a scale factor. The canvas size will be set according to these transformations.
Examples

ImageCanvas canvas = new ImageCanvas(image) - create an ImageCanvas directly from an image.
ImageCanvas canvas = new ImageCanvas(image, 1) - create an ImageCanvas from an image rotated by 90 degrees.
ImageCanvas canvas = new ImageCanvas(image, 2, 0.5) - create an ImageCanvas from an image rotated by 180 degrees with half size.

Based on ideas from: https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
