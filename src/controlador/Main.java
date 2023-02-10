package controlador;

import java.util.Iterator;

import javax.swing.JFrame;

import modelo.Modelo;
import vista.*;

public class Main {
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		// Vistasee
		JFrame[] pantallas;
		pantallas = new JFrame[12];

		Pantallas signup = new Signup();
		Pantallas todosEventos = new Eventos();
		Pantallas crearEvento = new CrearEvento();
		Pantallas ayuda = new Ayuda();
		Pantallas gestionDePerfil = new GestionDePerfil();
		Pantallas infoEvento = new InfoEvento();
		Pantallas inicioApp = new InicioAPP();
		Pantallas login = new Login();
		Pantallas admin = new Admin();
		Pantallas foro = new Foro();
		Pantallas datosConex = new DatosConex();
		Pantallas cambioPass = new CambioPass();
		
		pantallas[0] = (JFrame) login;
		pantallas[1] = (JFrame) signup;
		pantallas[2] = (JFrame) ayuda;
		pantallas[3] = (JFrame) inicioApp;
		pantallas[4] = (JFrame) infoEvento;
		pantallas[5] = (JFrame) gestionDePerfil;
		pantallas[6] = (JFrame) crearEvento;
		pantallas[7] = (JFrame) todosEventos;
		pantallas[8] = (JFrame) admin;
		pantallas[9] = (JFrame) foro;
		pantallas[10] = (JFrame) datosConex;
		pantallas[11] = (JFrame) cambioPass;
		
		pantallas[0].setVisible(true);

		controlador.setPantallas(pantallas);
		controlador.setModelo(modelo);
 
		// Asignar controlador y modelo a las pantallas
		for (JFrame pantalla: pantallas) {
			((Pantallas) pantalla).setModelo(modelo);
			((Pantallas) pantalla).setControlador(controlador);
		}
		
		modelo.setPantallas(pantallas);

	}
}
