package dad.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import dad.calculadora.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
	
	
	public static Stage stage;
	
	Controller controller;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		
		controller = new Controller();
		Scene scene = new Scene(controller.getView());
		controller.setSceneKeyEvent(scene);
		
		
		stage.setMinWidth(280);
		stage.setMinHeight(200);
		
		stage.setTitle("Calculadora FXML");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void createTyroneStage() {
		try {
			File imageFile = new File(Controller.class.getResource("/images/fac.gif").getPath());
			Image TyroneImage = new Image(new FileInputStream(imageFile));
			ImageView imageView = new ImageView(TyroneImage);
			Stage congratulations = new Stage();
			congratulations.setTitle("Tyrone te felicita");
			congratulations.setScene(new Scene(new Pane(imageView)));
			congratulations.show();
		} catch (FileNotFoundException e) {
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
