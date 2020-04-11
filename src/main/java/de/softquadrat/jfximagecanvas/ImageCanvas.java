package de.softquadrat.jfximagecanvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * <p>Instances of this class are used to render an image in a canvas.</p>
 * 
 * <p>The class inherits a canvas and calculates width and height in order to render a rotated and scaled image inside.</p>
 */
public class ImageCanvas extends Canvas {
	/**
	 * Creates a new instance with a given image.
	 * @param image the image.
	 */
	public ImageCanvas(Image image) {
		this(image, 0.0, 1.0);
	}

	/**
	 * Creates a new instance with a given image and a rotation angle in degrees.
	 * @param image the image.
	 * @param angleDegree the rotation angle in degrees.
	 */
	public ImageCanvas(Image image, double angleDegree) {
		this(image, angleDegree, 1.0);
	}

	/**
	 * Creates a new instance with a given image, a rotation angle in degrees and a scale factor.
	 * @param image the image.
	 * @param angleDegree the rotation angle in degrees.
	 * @param scaleFactor the scale factor
	 */
	public ImageCanvas(Image image, double angleDegree, double scaleFactor) {
		// reduce angle to a value between 0.0 (included) and 360.0 (excluded)
		double n = Math.floor(angleDegree / 360.0);
		angleDegree = angleDegree - n * 360.0;
		double angleRad = angleDegree * Math.PI / 180.0;
		// get imahe boundaries for later use
		double height = image.getHeight();
		double width = image.getWidth();
		// set canvas size calculated by rotated and scaled image
		double scaledHeight = height * scaleFactor;
		double scaledWidth = width * scaleFactor;
		double h = Math.abs(scaledWidth * Math.sin(angleRad)) + Math.abs(scaledHeight * Math.cos(angleRad));
		double w = Math.abs(scaledHeight * Math.sin(angleRad)) + Math.abs(scaledWidth * Math.cos(angleRad));
		setWidth(w);
		setHeight(h);
		// rotation and scale the graphi context used to draw the image
		GraphicsContext gc = getGraphicsContext2D();
		Affine affine = new Affine();
		Rotate rotate = new Rotate(angleDegree, 0.0, 0.0);
		affine.append(rotate);
		Scale scale = new Scale(scaleFactor, scaleFactor);
		affine.append(scale);
		// add translation to center image in canvas
		double moveX = 0.0;
		double moveY = 0.0;
		if (angleDegree > 0.0 && angleDegree <= 90.0) {
			moveX = height * Math.sin(angleRad) * Math.cos(angleRad);
			moveY = -height * Math.sin(angleRad) * Math.sin(angleRad);
		} else if (angleDegree > 90.0 && angleDegree <= 180.0) {
			moveX = -width * Math.cos(angleRad) * Math.cos(angleRad);
			moveY = -height + width * Math.cos(angleRad) * Math.sin(angleRad);
		} else if (angleDegree > 180.0 && angleDegree <= 270.0) {
			moveX = -width - height * Math.sin(angleRad) * Math.cos(angleRad);
			moveY = -height + height * Math.sin(angleRad) * Math.sin(angleRad);
		} else {
			moveX = -width + width * Math.cos(angleRad) * Math.cos(angleRad);
			moveY = -width * Math.cos(angleRad) * Math.sin(angleRad);
		}
		Translate translate = new Translate(moveX, moveY);
		// apply all transformations to graphic context
		affine.append(translate);
		gc.setTransform(affine);
		// draw image on graphic context of canvas
		gc.drawImage(image, 0.0, 0.0);
	}
}
