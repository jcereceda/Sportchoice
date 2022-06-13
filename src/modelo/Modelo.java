package modelo;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import vista.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Patrón MVC - MODELO Encargado de gestionar el almacenamiento y recuperación
 * de datos
 * 
 */
public class Modelo {
	private JFrame[] pantallas;
	private String resultadoLogin, resultadoSignup, nombreUsuario, emailUsuario, resultadoCrearEvento;
	private int fallosLogin;
	// Atributos bbdd;
	private String bd, userBD, pwdBD, url, driver;
	private Connection conexion;
	// Archivo conexion BBDD
	private Properties datosConexion;
	private File ficheroConexion;
	private InputStream entrada;
	private OutputStream salida;
	// modelos tabla
	private DefaultTableModel modeloTablaEventos, modeloTablaAdmin, modeloTablaUsuarioXEvento, modeloTablaChat;
	// Atributos eventos
	String nombreEvento, deporte, lugar, observaciones;
	int numParticipantes;
	String fecha;
	private String resultadoUnirse;
	private int codEvento, codigoUsuario, codigoEvento, numeroUsuarios;
	// Datos de usuario para gestion de perfil
	private String email, poblacion, sexo;
	private Date fechaNac;

	/**
	 * Método constructor por defecto
	 */
	public Modelo() {
		fallosLogin = 0;
	}

	/**
	 * Setter para asignar las pantallas al modelo (Patron MVC)
	 * 
	 * @param array de pantallas de la clase JFrame
	 */
	public void setPantallas(JFrame[] pantallas) {
		this.pantallas = pantallas;
	}

	/**
	 * Método para crear el atributo conexion con la BBDD - se llama desde el botón
	 * login retrasando la conexion Se realiza mediante la lectura del fichero
	 * ficheroConexion.ini. Mediante el podremos cargar tablas y realizar todas las
	 * consultas necesarias.
	 */

	private void crearConexion() {
		datosConexion = new Properties();
		try {
			ficheroConexion = new File("ficheroConexion.ini");
			if (ficheroConexion.exists()) {
				entrada = new FileInputStream(ficheroConexion);
				datosConexion.load(entrada);
			} else {
				System.err.println("Fichero no encontrado");
				System.exit(1);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// Conexion BBDD
		userBD = datosConexion.getProperty("user");
		pwdBD = datosConexion.getProperty("pasword");
		url = datosConexion.getProperty("url");
		driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, userBD, pwdBD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Carga tablas de las distintas vistas
		cargarTablaEventos();
		cargarTablaAdmin();
		cargarTablaUsuariosXEvento();
		cargarForo();
	}

	/**
	 * CARGA DE TABLAS Carga el modelo de tabla de los usuarios que hay en cada
	 * evento, se despliega en la pantalla infoEvento.
	 */

	private void cargarTablaUsuariosXEvento() {
		String consultaUsuariosXevento = "select u.nombre from usuarios u inner join usuario_eventos ue ON u.codigo_usuario = ue.cod_usuario AND ue.cod_evento = "
				+ codEvento + "";
		int numColumnas = getNumColumnas(consultaUsuariosXevento);
		int numFilas = getNumFilas(consultaUsuariosXevento);
		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(consultaUsuariosXevento);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				cabecera[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		modeloTablaUsuarioXEvento = new DefaultTableModel(contenido, cabecera);
	}

	/**
	 * Getter
	 * 
	 * @return modelo de tabla de usuarios por cada evento
	 */
	public DefaultTableModel getModeloTablaUsuarioXEvento() {
		return modeloTablaUsuarioXEvento;
	}

	/**
	 * Carga el modelo de tabla de usuarios desplegado en la pantalla del admin,
	 * muestra los datos de todos los usuarios de la BD
	 */

	private void cargarTablaAdmin() {
		String consultaUsuarios = "select * from usuarios";
		int numColumnas = getNumColumnas(consultaUsuarios);
		int numFilas = getNumFilas(consultaUsuarios);
		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(consultaUsuarios);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				cabecera[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		modeloTablaAdmin = new DefaultTableModel(contenido, cabecera);
	}

	/**
	 * Getter
	 * 
	 * @return modelo de la tabla de usuarios admins
	 */
	public DefaultTableModel getModeloTablaAdmin() {
		return modeloTablaAdmin;
	}

	/**
	 * Setter para poner un modelo de tabla guardado en la tabla admin
	 * 
	 * @param modelo de tabla recuperado
	 */
	public void setTablaAdmin(DefaultTableModel tableModel) {
		modeloTablaAdmin = tableModel;
	}

	/**
	 * Carga el modelo de tabla de eventos, desplegado en la pantalla eventos. Saca
	 * la informacion relevante de cada evento de la BD.
	 */

	private void cargarTablaEventos() {
		String consultaEventos = "select e.nombre, e.fecha, d.nombre, e.lugar, e.numeroUsuarios from eventos e INNER JOIN deportes d ON e.cod_deporte = d.cod_deporte;";
		int numColumnas = getNumColumnas(consultaEventos);
		int numFilas = getNumFilas(consultaEventos);
		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(consultaEventos);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				if (i == 2) {
					cabecera[i] = "Deporte";
				} else {
					cabecera[i] = rsmd.getColumnName(i + 1);
				}
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		modeloTablaEventos = new DefaultTableModel(contenido, cabecera);
	}

	/**
	 * Getter
	 * 
	 * @return tabla con todos los eventos de la BD
	 */
	public DefaultTableModel getModeloTablaEventos() {
		return modeloTablaEventos;
	}
	/**
	 * Método para asignar tablemodel externo a la tabla eventos
	 * @param tablemodel recuperado
	 */
	public void setTablaEventos(DefaultTableModel tabla) {
		modeloTablaEventos = tabla;
	}

	/**
	 * Carga el modelo de tabla de foro, desplegado en cada tabla foro, asociado a
	 * un solo evento
	 */
	private void cargarForo() {
		// El codigo de foro hace referencia al codigo de evento porque siempre va a ser
		// el mismo
		String selectForo = "select CONCAT(u.nombre,': ', m.mensaje) from mensaje m INNER JOIN usuarios u ON u.codigo_usuario = m.cod_usuario where cod_foro = "
				+ codigoEvento + "";
		int numColumnas = 1;
		int numFilas = getNumFilas(selectForo);
		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];
		try {
			Statement seleccionarForo = conexion.createStatement();
			ResultSet rset = seleccionarForo.executeQuery(selectForo);
			ResultSetMetaData rsmd = rset.getMetaData();
			cabecera[0] = "FORO DEL EVENTO";
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		modeloTablaChat = new DefaultTableModel(contenido, cabecera);
	}

	/**
	 * Getter
	 * 
	 * @return modelo de tabla de cada chat de cada evento
	 */
	public DefaultTableModel getModeloChat() {
		return modeloTablaChat;
	}

	/**
	 * Devuelve el numero de filas que sacará una consulta que pasamos por parámetro
	 * 
	 * @param consulta select a una tabla de una base de datos
	 * @return numFilas - numero de filas que devolverá la consulta
	 */

	private int getNumFilas(String consulta) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(consulta);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	/**
	 * Devuelve el numero de columnas que sacará una consulta que pasamos por
	 * parámetro
	 * 
	 * @param consulta select a una tabla de una base de datos
	 * @return numColumnas - numero de columnas que devolverá la consulta
	 */

	private int getNumColumnas(String consulta) {
		int num = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(consulta);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			num = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * ´Método para comprobar en la BD si un usuario y su contraseña existen, para
	 * poder acceder a la aplicación funcional.
	 * 
	 * @param user introducido
	 * @param pass introducida
	 */
	public void verificarLogin(String user, String pass) {
		crearConexion(); // Se retrasa, en caso de que no esté correcto el archivo no dará error al
							// iniciar
		fallosLogin = 0;

		int resultado = 0;
		String queryLogin = "Select * from usuarios where nombre=? AND passwd=?";
		try {
			PreparedStatement pstmtLogin = conexion.prepareStatement(queryLogin);
			pstmtLogin.setString(1, user);
			pstmtLogin.setString(2, pass);
			ResultSet rset = pstmtLogin.executeQuery();
			// Si no existe no habrá next
			if (!rset.next()) {
				resultado = 0;
			} else {
				resultado = 1;
			}
			pstmtLogin.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultado == 0) {
			fallosLogin++;
			// A los 3 fallos cerrará el programa
			if (fallosLogin == 3) {
				resultadoLogin = "Cerrado";
			} else {
				resultadoLogin = "Incorrecto";
			}
		} else {
			if (user.equals("admin")) {
				resultadoLogin = "admin";
			} else {
				resultadoLogin = "Correcto";
			}
			fallosLogin = 0;
		}

		((Login) pantallas[0]).actualizar();
	}

	/**
	 * Getter
	 * 
	 * @return resultadoLogin si ha sido correcto o no
	 */
	public String getResultadoLogin() {
		return resultadoLogin;
	}

	/**
	 * Método de registro para insertar un nuevo usuario en la BD con la informacion
	 * básica de usuario.
	 * 
	 * @param nombre
	 * @param email
	 * @param pass
	 */
	public void crearUsuario(String nombre, String email, String pass) {
		resultadoSignup = "Correcto";
		// Comprobacion de que no exista el nombre ya
		String existe = "Select nombre,email from usuarios";
		try {
			PreparedStatement comprobarExistencia = conexion.prepareStatement(existe);
			ResultSet existencia = comprobarExistencia.executeQuery();
			while (existencia.next()) {
				if (existencia.getString(1).equals(nombre) || existencia.getString(2).equals(email)) {
					resultadoSignup = "dato repetido";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Comprobamos que sea valida la contraseña (controlador devolvera "" si no
		// coinciden)
		if (pass.equals("")) {
			resultadoSignup = "passwd no coincidentes";
		}

		// Insertamos
		String insertar = "Insert into usuarios (nombre,email,passwd) VALUES (?,?,?)";
		if (resultadoSignup.equals("Correcto")) {
			try {
				PreparedStatement insercion = conexion.prepareStatement(insertar);
				insercion.setString(1, nombre);
				insercion.setString(2, email);
				insercion.setString(3, pass);
				insercion.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		((Signup) pantallas[1]).actualizar();
		cargarTablaAdmin();

	}

	/**
	 * Getter
	 * 
	 * @return resultado si el registro ha sido correcto o no y por qué
	 */
	public String getResultadoSignup() {
		return resultadoSignup;
	}

	/**
	 * Método llamado por el controlador para indicar a cada pantalla el nombre de
	 * usuario que poner en su cabecera
	 * 
	 * @param user - nombre de usuario que inició sesión
	 */
	public void setNombreCabecera(String user) {
		nombreUsuario = user;
		((InicioAPP) pantallas[3]).setNombreCabecera();
		((CrearEvento) pantallas[6]).setNombreCabecera();
		((Eventos) pantallas[7]).setNombreCabecera();
		((InfoEvento) pantallas[4]).setNombreCabecera();
		((Foro) pantallas[9]).setNombreCabecera();
		((Ayuda) pantallas[2]).setNombreCabecera();
	}

	/**
	 * getter
	 * 
	 * @return nombre de usuario para cabeceras
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Método para recoger toda la información del evento para la pantalla
	 * infoevento que se accede desde eventos
	 * 
	 * @param codigo de evento al que se accede
	 */
	public void ponerCamposInfoEvento(int codEvento2) {
		String consutlaEvento = "select e.nombre,e.fecha,d.nombre,e.lugar,e.numeroUsuarios,e.observaciones from eventos e inner join deportes d on e.cod_deporte = d. cod_deporte where codigoEvento =?";
		this.codEvento = codEvento2;
		try {
			PreparedStatement evento = conexion.prepareStatement(consutlaEvento);
			evento.setInt(1, codEvento);
			ResultSet camposEvento = evento.executeQuery();
			camposEvento.next();
			nombreEvento = camposEvento.getString(1);
			deporte = camposEvento.getString(3);
			lugar = camposEvento.getString(4);
			numParticipantes = camposEvento.getInt(5);
			observaciones = camposEvento.getString(6);
			fecha = camposEvento.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		((InfoEvento) pantallas[4]).actualizar();
		cargarTablaUsuariosXEvento();
	}

	/**
	 * Métodos getter para los atributos de cada evento llamados por la vista
	 * infoEvento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}

	public String getDeporte() {
		return deporte;
	}

	public String getLugar() {
		return lugar;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public int getNumParticipantes() {
		return numParticipantes;
	}

	public String getFecha() {
		return fecha;
	}

	/**
	 * Metodo para insertar en la base de datos tu usuario asociado a un evento (al
	 * que te apuntas)
	 * 
	 * @param nombreEvento al que te vas a unir
	 */

	public void unirAEvento(String nombreEvento2) {
		// Sacar codigo de evento
		resultadoUnirse = "Correcto";
		String consultaCodEvento = "select codigoEvento,numeroUsuarios from eventos where nombre =?";
		try {
			PreparedStatement codEvento = conexion.prepareStatement(consultaCodEvento);
			codEvento.setString(1, nombreEvento2);
			ResultSet resultado = codEvento.executeQuery();
			resultado.next();
			codigoEvento = resultado.getInt(1);
			numeroUsuarios = resultado.getInt(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int dentro = modeloTablaUsuarioXEvento.getRowCount();

		// Comprobar que no supera el maximo indicado al crear - campo numeroUsuarios
		if (dentro >= numeroUsuarios) {
			resultadoUnirse = "Maximo";
		}

		// Sacar codigo de Usuario
		String consultaCodUsuario = "select codigo_usuario from usuarios where nombre = \"" + nombreUsuario + "\"";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet resultadoUser = stmt.executeQuery(consultaCodUsuario);
			resultadoUser.next();
			codigoUsuario = resultadoUser.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Comprobar que no esta el usuario en el evento

		String comprobacion = "Select cod_usuario,cod_evento from usuario_eventos";
		try {
			PreparedStatement existe = conexion.prepareStatement(comprobacion);
			ResultSet estaIn = existe.executeQuery();
			while (estaIn.next()) {
				if (estaIn.getInt(1) == codigoUsuario && estaIn.getInt(2) == codigoEvento) {
					resultadoUnirse = "fallo";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Insertar usuario
		String insertar = "insert into usuario_eventos (cod_usuario,cod_evento) VALUES (?,?)";
		if (resultadoUnirse.equals("Correcto")) {
			PreparedStatement usuarioEventos;
			try {
				usuarioEventos = conexion.prepareStatement(insertar);
				usuarioEventos.setInt(1, codigoUsuario);
				usuarioEventos.setInt(2, codigoEvento);
				usuarioEventos.executeUpdate();
				usuarioEventos.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		cargarTablaUsuariosXEvento();

		((InfoEvento) pantallas[4]).actualizarUsers();
		((InfoEvento) pantallas[4]).ponerModelo();
	}

	/**
	 * Getter
	 * 
	 * @return resultado de unirse si es exitoso o no y por qué
	 */
	public String getResultadoUnirse() {
		return resultadoUnirse;
	}

	/**
	 * Método para insertar un evento nuevo en la BD con toda la información sobre
	 * el mismo
	 * 
	 * @param nombreEvento2
	 * @param deporte2
	 * @param fecha2
	 * @param lugar2
	 * @param numParticipantes2
	 * @param observaciones2
	 */
	public void crearEvento(String nombreEvento2, String deporte2, String fecha2, String lugar2, int numParticipantes2,
			String observaciones2) {
		int codigoDeporte = 0;
		resultadoCrearEvento = "Correcto";

		// Comprobar si existe un evento con el mismo nombre
		String existe = "Select nombre from eventos";
		try {
			PreparedStatement comprobarExistencia = conexion.prepareStatement(existe);
			ResultSet existencia = comprobarExistencia.executeQuery();
			while (existencia.next()) {
				if (existencia.getString(1).equals(nombreEvento2)) {
					resultadoCrearEvento = "dato repetido";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Sacar el codigo del deporte introducido
		String sacarDeporte = "select cod_deporte from deportes where nombre = \"" + deporte2 + "\"";
		try {
			PreparedStatement sacarCodDeporte = conexion.prepareStatement(sacarDeporte);
			ResultSet codDeporte = sacarCodDeporte.executeQuery();
			codDeporte.next();
			codigoDeporte = codDeporte.getInt(1);
			codDeporte.close();
			sacarCodDeporte.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Insertar evento
		String insertEvento = "INSERT INTO eventos (nombre,fecha,cod_deporte,lugar,numeroUsuarios,observaciones) VALUES (?,?,?,?,?,?)";
		if (resultadoCrearEvento.equals("Correcto")) {
			try {
				PreparedStatement insercion = conexion.prepareStatement(insertEvento);
				insercion.setString(1, nombreEvento2);
				insercion.setString(2, fecha2);
				insercion.setInt(3, codigoDeporte);
				insercion.setString(4, lugar2);
				insercion.setInt(5, numParticipantes2);
				insercion.setString(6, observaciones2);
				insercion.executeUpdate();
				insercion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		((CrearEvento) pantallas[6]).actualizar();
		cargarTablaEventos();
		cargarTablaUsuariosXEvento();

		// Al crear un evento directamente se crea el foro con su mismo código
		int codigoEvento = 0;
		String selectCodEvento = "Select codigoEvento from eventos where nombre = ?";
		try {
			PreparedStatement selectCodEv = conexion.prepareStatement(selectCodEvento);
			selectCodEv.setString(1, nombreEvento2);
			ResultSet resultadoCod = selectCodEv.executeQuery();
			resultadoCod.next();
			codigoEvento = resultadoCod.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Insertar foro
		String insertForo = "Insert into foro (codigo_foro, codigo_evento) VALUES (?,?)";
		try {
			PreparedStatement insertF = conexion.prepareStatement(insertForo);
			insertF.setInt(1, codigoEvento);
			insertF.setInt(2, codigoEvento);
			insertF.executeUpdate();
			insertF.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Getter
	 * 
	 * @return resultado de crear si es exitoso o no
	 */
	public String getResultadoCrear() {
		return resultadoCrearEvento;
	}

	/**
	 * Método para insertar mensajes en el chat de un evento en concreto
	 * 
	 * @param mensaje a poner en el chat
	 */
	public void subirMensaje(String mensaje) {
		String insertMensaje = "insert into mensaje (mensaje,cod_usuario,cod_foro) values (?,?,?)";
		try {
			PreparedStatement insertM = conexion.prepareStatement(insertMensaje);
			insertM.setString(1, mensaje);
			insertM.setInt(2, codigoUsuario); // El cual tiene iniciada la sesion
			insertM.setInt(3, codigoEvento); // Al cual pertenece el foro
			insertM.executeUpdate();
			insertM.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cargarForo();
		((Foro) pantallas[9]).actualizar();
	}

	/**
	 * Método para cambiar de foro en función que se cambia de pantalla de evento
	 * También saca el codigo de usuario por si envía mensaje
	 * 
	 * @param codEvento2 del nuevo evento al cual se cambia
	 */
	public void usarForo(int codEvento2) {
		codigoEvento = codEvento2;
		String consultaCodUsuario = "select codigo_usuario from usuarios where nombre = \"" + nombreUsuario + "\"";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet resultadoUser = stmt.executeQuery(consultaCodUsuario);
			resultadoUser.next();
			codigoUsuario = resultadoUser.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cargarForo();
	}

	/**
	 * Método para recuperar todos los campos de usuario que usa la pantalla gestión
	 * de perfil
	 * 
	 * @param user que inició sesión
	 */
	public void ponerCamposGestion(String user) {
		String consultaGestion = "select * from usuarios where nombre = \"" + user + "\"";
		try {
			Statement camposGestion = conexion.createStatement();
			ResultSet asignarCampos = camposGestion.executeQuery(consultaGestion);
			asignarCampos.next();
			email = asignarCampos.getString(3);
			poblacion = asignarCampos.getString(4);
			sexo = asignarCampos.getString(7);
			if (asignarCampos.getString(5) == null) {
				fechaNac = new Date();
			} else {
				fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(asignarCampos.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		((GestionDePerfil) pantallas[5]).actualizar();
	}

	/**
	 * Getter de los atributos de usuario para la pantalla gestion de perfil
	 * 
	 * @return parámetros del usuario que inició sesión
	 */
	public String getEmail() {
		return email;
	}

	public String getUbicacion() {
		return poblacion;
	}

	public String getSexo() {
		return sexo;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	/**
	 * Getter de los campos de la pantalla datosconexión, usuario, contraseña y ruta
	 * de la base de datos a la que nos conectamos
	 * 
	 * @return usuario, pass y ruta de la BD
	 */
	public String getUserBD() {
		return userBD;
	}

	public String getPassBD() {
		return pwdBD;
	}

	public String getRutaBD() {
		return url;
	}

	/**
	 * Método para modificar el archivo que se lee con los datos de conexion de la
	 * BD
	 * 
	 * @param usuarioBD
	 * @param passBD
	 * @param rutaBD
	 */
	public void modificarArchivo(String usuarioBD, String passBD, String rutaBD) {

		try {
			datosConexion.setProperty("user", usuarioBD);
			datosConexion.setProperty("pasword", passBD);
			datosConexion.setProperty("url", rutaBD);
			salida = new FileOutputStream(ficheroConexion);
			try {
				datosConexion.store(salida, "Operacion: Actualizado");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		crearConexion();
		((DatosConex) pantallas[10]).actualizar();
	}

	/**
	 * Método para eliminar usuario de la BD
	 * @param nombre de usuario a eliminar
	 */
	public void eliminarUsuario(String nombre) {
		String eliminar = "delete from usuarios where nombre = ?";
		try {
			PreparedStatement eliminacion = conexion.prepareStatement(eliminar);
			eliminacion.setString(1, nombre);
			eliminacion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cargarTablaAdmin();
		((Admin) pantallas[8]).actualizar();
	}

	/**
	 * Método para modificar los campos de un perfil de usuario, los parámetros son los nuevos campos
	 * @param user
	 * @param nombre
	 * @param ubicacion
	 * @param sexo
	 * @param fecha
	 */
	public void modificarPerfil(String user, String nombre, String ubicacion, String sexo, String fecha) {
		String modificar = "update usuarios set nombre = ?, poblacion = ?, sexo = ?, fecha_nacimiento = ? where nombre = ?";

		try {
			PreparedStatement pstmt = conexion.prepareStatement(modificar);
			// Se actualizarán aunque sean el mismo que había
			pstmt.setString(1, nombre);
			pstmt.setString(2, ubicacion);
			pstmt.setString(3, sexo);
			pstmt.setString(4, fecha);
			pstmt.setString(5, user);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		((GestionDePerfil) pantallas[5]).perfilActualizado();

	}
}
