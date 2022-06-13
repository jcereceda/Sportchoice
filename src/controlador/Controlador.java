package controlador;

import java.util.Date;
import vista.*;
import javax.swing.JFrame;

import modelo.Modelo;

public class Controlador {
	private Modelo modelo;
	private String user;
	private String email;
	private JFrame[] pantallas;

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setPantallas(JFrame[] pantallas2) {
		pantallas = pantallas2;
	}

	public void cambioPantallas(int antigua, int nueva) {
		pantallas[antigua].setVisible(false);
		pantallas[nueva].setVisible(true);
		modelo.setNombreCabecera(user);

		if (antigua == 7 && nueva == 4) {
			int codEvento = ((Eventos) pantallas[7]).getCodigoEvento();
			modelo.ponerCamposInfoEvento(codEvento);
		}
		if (antigua == 4 && nueva == 9) {
			int codEvento = ((Eventos) pantallas[7]).getCodigoEvento();
			modelo.usarForo(codEvento);
		}

		if (nueva == 5) {
			modelo.ponerCamposGestion(user);
		}
	}

	public void login() {
		user = ((Login) pantallas[0]).getUser();
		String pass = ((Login) pantallas[0]).getPassword();
		modelo.verificarLogin(user, pass);
	}

	public void crearUsuario() {
		String nombre = ((Signup) pantallas[1]).getNombre();
		String email = ((Signup) pantallas[1]).getEmail();
		String pass = ((Signup) pantallas[1]).getPass();
		modelo.crearUsuario(nombre, email, pass);
	}

	public void crearEvento() {
		String nombreEvento = ((CrearEvento) pantallas[6]).getNombreEvento();
		String deporte = ((CrearEvento) pantallas[6]).getDeporte();
		String fecha = ((CrearEvento) pantallas[6]).getFecha();
		String lugar = ((CrearEvento) pantallas[6]).getLugar();
		int numParticipantes = ((CrearEvento) pantallas[6]).getNumParticipantes();
		String observaciones = ((CrearEvento) pantallas[6]).getObservaciones();
		modelo.crearEvento(nombreEvento, deporte, fecha, lugar, numParticipantes, observaciones);
	}

	public void unirseaEvento() {
		String nombreEvento = ((InfoEvento) pantallas[4]).getNombreEvento();
		modelo.unirAEvento(nombreEvento);
	}

	public void publicarMensaje() {
		String mensaje = ((Foro) pantallas[9]).getMensaje();
		modelo.subirMensaje(mensaje);
	}


	public void modificarArchivo() {
		String usuarioBD = ((DatosConex) pantallas[10]).getUsuarioBD();
		String passBD = ((DatosConex) pantallas[10]).getPassBD();
		String rutaBD = ((DatosConex) pantallas[10]).getRutaBD();
		modelo.modificarArchivo(usuarioBD, passBD, rutaBD);
	}

	public void eliminarUsuario() {
		String nombre = ((Admin) pantallas[8]).getNombre();
		modelo.eliminarUsuario(nombre);
	}

	public void modificarPerfil() {
		String nombre = ((GestionDePerfil) pantallas[5]).getNombreNuevo();
		String ubicacion = ((GestionDePerfil) pantallas[5]).getUbiNueva();
		String sexo = ((GestionDePerfil) pantallas[5]).getSexo();
		String fecha = ((GestionDePerfil) pantallas[5]).getFecha();
		modelo.modificarPerfil(user,nombre, ubicacion, sexo, fecha);
	}

}
