package controlador;
/**
 * Clase Main - Instanciación de todos los objetos del patron MVC
 */
import java.util.Iterator;

import javax.swing.JFrame;

import modelo.Modelo;
import vista.*;

public class Main {
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		
		// Vistas
		JFrame[] pantallas;
		pantallas = new JFrame[12];

		Signup signup = new Signup();
		Eventos todosEventos = new Eventos();
		CrearEvento crearEvento = new CrearEvento();
		Ayuda ayuda = new Ayuda();
		GestionDePerfil gestionDePerfil = new GestionDePerfil();
		InfoEvento infoEvento = new InfoEvento();
		InicioAPP inicioApp = new InicioAPP();
		Login login = new Login();
		Admin admin = new Admin();
		Foro foro = new Foro();
		DatosConex datosConex = new DatosConex();
		CambioPass cambiopass = new CambioPass();
		
		pantallas[0] = login;
		pantallas[1] = signup;
		pantallas[2] = ayuda;
		pantallas[3] = inicioApp;
		pantallas[4] = infoEvento;
		pantallas[5] = gestionDePerfil;
		pantallas[6] = crearEvento;
		pantallas[7] = todosEventos;
		pantallas[8] = admin;
		pantallas[9] = foro;
		pantallas[10] = datosConex;
		pantallas[11] = cambiopass;
		
		pantallas[0].setVisible(true);

		// Asignar pantallas y modelo al controlador
		controlador.setPantallas(pantallas);
		controlador.setModelo(modelo);

		// Asignar modelo y controlador  a las pantallas
		((CrearEvento) pantallas[6]).setModelo(modelo);
		((CrearEvento) pantallas[6]).setControlador(controlador);
		((Ayuda) pantallas[2]).setControlador(controlador);
		((Ayuda) pantallas[2]).setModelo(modelo);
		((Eventos) pantallas[7]).setModelo(modelo);
		((Eventos) pantallas[7]).setControlador(controlador);
		((GestionDePerfil) pantallas[5]).setControlador(controlador);
		((GestionDePerfil) pantallas[5]).setModelo(modelo);
		((InfoEvento) pantallas[4]).setControlador(controlador);
		((InfoEvento) pantallas[4]).setModelo(modelo);
		((InicioAPP) pantallas[3]).setModelo(modelo);
		((InicioAPP) pantallas[3]).setControlador(controlador);
		((Login) pantallas[0]).setControlador(controlador);
		((Login) pantallas[0]).setModelo(modelo);
		((Signup) pantallas[1]).setControlador(controlador);
		((Signup) pantallas[1]).setModelo(modelo);
		((Admin) pantallas[8]).setModelo(modelo);
		((Admin) pantallas[8]).setControlador(controlador);
		((Foro) pantallas[9]).setModelo(modelo);
		((Foro) pantallas[9]).setControlador(controlador);
		((DatosConex) pantallas[10]).setControlador(controlador);
		((DatosConex) pantallas[10]).setModelo(modelo);
		((CambioPass)pantallas[11]).setModelo(modelo);
		((CambioPass) pantallas[11]).setControlador(controlador);
		
		// Asignar pantallas al modelo
		modelo.setPantallas(pantallas);

	}
}
