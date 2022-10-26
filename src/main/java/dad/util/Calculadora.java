package dad.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Implementaci�n de la l�gica de una calculadora.
 * @author Fran Vargas
 */
public class Calculadora {
	
	public static final char IGUAL = '='; 
	public static final char SUMAR = '+'; 
	public static final char RESTAR = '-'; 
	public static final char DIVIDIR = '/'; 
	public static final char MULTIPLICAR = '*';
	
	private static final char COMA = '.'; 
	
	private Double operandoDouble;
	private char operadorChar;
	private Boolean nuevoOperando;
	private StringProperty pantallaStringProperty = new SimpleStringProperty();
	
	public Calculadora() {
		borrar();
	}
	
	/**
	 * Devuelve el contenido de la pantalla de la calculadora.
	 * @return Cadena de texto con el contenido de la pantalla de la calculdora.
	 */
	public String getPantalla() {
		return pantallaStringProperty.get();
	}
	public StringProperty propertyPantalla() {
		return pantallaStringProperty;
	}

	/**
	 * Inicializa por completo la calculadora, borrando la informaci�n que tiene memorizada y la pantalla.
	 */
	public void borrar() {
		operandoDouble = 0.0;
		operadorChar = '=';
		borrarTodo();
	}
	
	/**
	 * Borra lo que hay en la pantalla (el �ltimo operando introducido).
	 */
	public void borrarTodo() {
		nuevoOperando = true;
		pantallaStringProperty.set("0.0");
	}
	
	/**
	 * Indica a la calculadora que realice la operaci�n indicada. 
	 * @param operador Operaci�n a realizar; usar una constante: IGUAL, SUMAR, RESTAR, MULTIPLCIAR, DIVIDIR.
	 */
	public void operar(char operador) {
		nuevoOperando = true;
		double operando2 = Double.parseDouble(getPantalla());
		switch (this.operadorChar) {
			case SUMAR: operandoDouble += operando2; break;
			case RESTAR: operandoDouble -= operando2; break;
			case MULTIPLICAR: operandoDouble *= operando2; break;
			case DIVIDIR: operandoDouble /= operando2; break;
			case IGUAL: operandoDouble = operando2; break;
		}
		this.operadorChar = operador;
		pantallaStringProperty.set("" + operandoDouble);
	}
	
	/**
	 * Inserta una coma en el operando actual (pantalla).
	 */
	public void insertarComa() {
		if (!getPantalla().contains("" + COMA)) {
			pantallaStringProperty.set(getPantalla() + COMA);
		}
	}
	
	/**
	 * Inserta un d�gito en el operando actual (pantalla).
	 * @param digito Digito a introducir en la pantalla.
	 */
	public void insertar(char digito) {
		if (digito >= '0' && digito <= '9') {
			if (nuevoOperando) {
				nuevoOperando = false;
				pantallaStringProperty.set("");
			}
			pantallaStringProperty.set(getPantalla() + digito);
		} else if (digito == COMA) {
			insertarComa();
		}
	}
	
}
