package controlador;

/**
 * Controlador del patrón MVC - intermediario entre el Modelo y la Vista, gestionando el flujo de información 
 * entre ellos y las transformaciones para adaptar los datos a las necesidades de cada uno.
 */
import java.util.Date;
import vista.*;
import javax.swing.JFrame;

import modelo.Modelo;

public class Controlador {
	private Modelo modelo;
	private String user;
	private String email;
	private JFrame[] pantallas;

	/**
	 * Setter de modelo y array pantallas
	 * 
	 * @param modelo, array de pantallas
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setPantallas(JFrame[] pantallas2) {
		pantallas = pantallas2;
	}

	/**
	 * Método para hacer visible una pantalla e invisible otra cuando se pulsa algún
	 * botón de la antigua
	 * 
	 * @param pantalla antigua
	 * @param pantalla nueva que se cambia
	 */
	public void cambioPantallas(int antigua, int nueva) {
		pantallas[antigua].setVisible(false);
		pantallas[nueva].setVisible(true);
		modelo.setNombreCabecera(user);

		// Si la nueva es la de infoevento hay que indicar el evento del que sacar
		// informacion
		if (antigua == 7 && nueva == 4) {
			int codEvento = ((Eventos) pantallas[7]).getCodigoEvento();
			modelo.ponerCamposInfoEvento(codEvento);
		}
		// Si la nueva es el foro, tiene que cambiar en función a qué evento se mete
		if (antigua == 4 && nueva == 9) {
			int codEvento = ((Eventos) pantallas[7]).getCodigoEvento();
			modelo.usarForo(codEvento);
		}
		// Si la nueva es la de gestión de perfil tiene que poner los campos del mismo
		if (nueva == 5) {
			modelo.ponerCamposGestion(user);
		}
	}

	/**
	 * Metodo para sacar el usuario y contraseña metido por pantalla e indicarselo
	 * al controlador
	 * 
	 */
	public void login() {
		user = ((Login) pantallas[0]).getUser();
		String pass = ((Login) pantallas[0]).getPassword();
		modelo.verificarLogin(user, pass);
	}

	/**
	 * Método para recoger los datos del formulario de registro e indicarselos al
	 * modelo
	 */
	public void crearUsuario() {
		String nombre = ((Signup) pantallas[1]).getNombre();
		String email = ((Signup) pantallas[1]).getEmail();
		String pass = ((Signup) pantallas[1]).getPass();
		modelo.crearUsuario(nombre, email, pass);
	}

	/**
	 * Método para recoger los datos del formulario de crear evento e indicarselos
	 * al modelo
	 */
	public void crearEvento() {
		String nombreEvento = ((CrearEvento) pantallas[6]).getNombreEvento();
		String deporte = ((CrearEvento) pantallas[6]).getDeporte();
		String fecha = ((CrearEvento) pantallas[6]).getFecha();
		String lugar = ((CrearEvento) pantallas[6]).getLugar();
		int numParticipantes = ((CrearEvento) pantallas[6]).getNumParticipantes();
		String observaciones = ((CrearEvento) pantallas[6]).getObservaciones();
		modelo.crearEvento(nombreEvento, deporte, fecha, lugar, numParticipantes, observaciones);
	}

	/**
	 * Método para sacar el nombre del evento al que se quiere unir el usuario que
	 * inició sesión e indicarselo al modelo
	 */
	public void unirseaEvento() {
		String nombreEvento = ((InfoEvento) pantallas[4]).getNombreEvento();
		modelo.unirAEvento(nombreEvento);
	}

	/**
	 * Método para recoger el mensaje que se puso en el cuadro del chat e
	 * indicarselo al modelo para que lo publique
	 */
	public void publicarMensaje() {
		String mensaje = ((Foro) pantallas[9]).getMensaje();
		modelo.subirMensaje(mensaje);
	}

	/**
	 * Método para recoger datos del formulario para cambiar datos de conexion de la
	 * BD e indicarselos al modelo
	 */
	public void modificarArchivo() {
		String usuarioBD = ((DatosConex) pantallas[10]).getUsuarioBD();
		String passBD = ((DatosConex) pantallas[10]).getPassBD();
		String rutaBD = ((DatosConex) pantallas[10]).getRutaBD();
		modelo.modificarArchivo(usuarioBD, passBD, rutaBD);
	}

	/**
	 * Método para sacar el nombre del usuario que se quiere eliminar para indicarlo
	 * al modelo
	 */
	public void eliminarUsuario() {
		String nombre = ((Admin) pantallas[8]).getNombre();
		modelo.eliminarUsuario(nombre);
	}

	/**
	 * Método para sacar la información nueva del usuario desde el formulario de
	 * gestion de perfil e indicarlo al modelo para que los actualice
	 */
	public void modificarPerfil() {
		String nombre = ((GestionDePerfil) pantallas[5]).getNombreNuevo();
		String ubicacion = ((GestionDePerfil) pantallas[5]).getUbiNueva();
		String sexo = ((GestionDePerfil) pantallas[5]).getSexo();
		String fecha = ((GestionDePerfil) pantallas[5]).getFecha();
		modelo.modificarPerfil(user, nombre, ubicacion, sexo, fecha);
	}

}
