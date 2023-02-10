package vista;

import javax.swing.JFrame;

import controlador.Controlador;
import modelo.Modelo;


public interface Pantallas {
	void setControlador(Controlador controlador);
	void setModelo(Modelo modelo);
}
