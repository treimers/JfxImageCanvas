package de.softquadrat.jfximagecanvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Do some tests with JfxImageCanvas.
 */
public class JfxImageCanvasTest extends Application {
	@Override
	public void start(Stage stage) {
		stage.setTitle("JfxImageCanvas Test");
		Image image = new Image("https://upload.wikimedia.org/wikipedia/de/b/bb/Png-logo.png");
		FlowPane root = new FlowPane(5.0, 5.0);
		// display several instances
		ImageCanvas[] canvases = new ImageCanvas[] {
				new ImageCanvas(image, 0.0, 0.1),
				new ImageCanvas(image, 20.0, 0.2),
				new ImageCanvas(image, 90.0, 0.2),
				new ImageCanvas(image, 135.0, 0.1),
				new ImageCanvas(image, 180.0, 0.12),
				new ImageCanvas(image, 200.0, 0.18),
				new ImageCanvas(image, 270.0, 0.1),
				new ImageCanvas(image, 340.0, 0.05),
		};
		for (ImageCanvas imageCanvas : canvases) {
			root.getChildren().addAll(imageCanvas);
		}
		StackPane frame = new StackPane();
		frame.getChildren().add(root);
		stage.setScene(new Scene(frame));
		stage.centerOnScreen();
		stage.setWidth(800.0);
		stage.setHeight(600.0);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
