package dad.calculadora;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import dad.main.App;
import dad.util.Calculadora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller implements Initializable {
	
	//Model
	
	private Calculadora calculadora = new Calculadora();
	
	private List<KeyCode> LastKeyCodes = new ArrayList<KeyCode>();

	private List<KeyCode> konamiCode = Arrays.asList(
			KeyCode.UP,KeyCode.DOWN,KeyCode.UP,KeyCode.DOWN,
			KeyCode.LEFT,KeyCode.RIGHT,KeyCode.LEFT,KeyCode.RIGHT,
			KeyCode.B,KeyCode.A);
	
	//View
	
	@FXML
	private GridPane view;
	
	@FXML
	private TextField pantallaTextField;
	
	@FXML
	private Button igualButton;
	
	public Controller() throws IOException {
		//FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalculadoraView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pantallaTextField.textProperty().bind(calculadora.propertyPantalla());
		
		
		igualButton.setDefaultButton(true);
		
	}
	
	public void setSceneKeyEvent(Scene scene) {
		scene.setOnKeyPressed(event -> {
			
//			System.out.println(event.getCode());

			if(event.getCode().isDigitKey() || event.getCode().isKeypadKey()) {
				calculadora.insertar(event.getCode().getName().charAt(event.getCode().getName().length() - 1));
			}
			else {
				switch (event.getCode()) {
					case ADD: calculadora.operar('+'); break;
					case SUBTRACT: calculadora.operar('-'); break;
					case PERIOD: calculadora.insertar('.'); break;
					case DECIMAL: calculadora.insertar('.'); break;
					case MULTIPLY: calculadora.operar('*'); break;
					case DIVIDE: calculadora.operar('/'); break;
					case BACK_SPACE: calculadora.borrarTodo(); break;
					case INSERT: calculadora.operar('='); break;
				}
			}
			
			if(LastKeyCodes.size() > 100) {
				LastKeyCodes.clear();
			}
			
			LastKeyCodes.add(event.getCode());
			
//			if(containsKonamiCode()) {
//				LastKeyCodes.clear();
//				App.createTyroneStage();
//			}
			System.out.println(containsKonamiCode());
			
			
		});
	}
	
	private boolean containsKonamiCode() {return LastKeyCodes.containsAll(konamiCode);}

	@FXML
	public void onInsertarAction(ActionEvent a){
		Button b = (Button)a.getSource();
		calculadora.insertar(b.getText().charAt(0));
//		System.out.println(a.toString());
		
//		System.out.println(a.getEventType());
	}
	@FXML
	public void onOperarAction(ActionEvent a){
		Button b = (Button)a.getSource();
		calculadora.operar(b.getText().charAt(0));
	}
	@FXML
	public void onBorrarAction(ActionEvent a){
		Button b = (Button) a.getSource();
		if(b.getText().equals("ce")) {
			calculadora.borrarTodo();
		}else {
			calculadora.borrar();
		}
	}
	
	public GridPane getView() {return this.view;}

}
