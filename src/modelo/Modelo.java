package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import vista.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Modelo {
	private JFrame[] pantallas;
	private String resultadoLogin, resultadoSignup, nombreUsuario, emailUsuario, resultadoCrearEvento;
	private int fallosLogin;
	// Atributos bbdd;
	private String bd, userBD, pwdBD, url, driver;
	private Connection conexion;
	// modelos tabla
	private DefaultTableModel modeloTablaEventos, modeloTablaAdmin, modeloTablaUsuarioXEvento, modeloTablaChat;
	// Atributos eventos
	String nombreEvento, deporte, lugar, observaciones;
	int numParticipantes;
	String fecha;
	private String resultadoUnirse;
	String[] usuariosxevento;
	private int codEvento, codigoUsuario, codigoEvento;
	// Archivo conexion BBDD
	private Properties datosConexion;
	private File ficheroConexion;
	private InputStream entrada;
	private OutputStream salida;
	// Datos de usuario
	private String email, poblacion;

	public Modelo() {
		crearConexion();
		fallosLogin = 0;
		cargarTablaEventos();
		cargarTablaAdmin();
		cargarTablaUsuariosXEvento();
		cargarForo();

	}

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

		cargarTablaEventos();
		cargarTablaAdmin();
		cargarTablaUsuariosXEvento();
		cargarForo();
	}

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

	private int getNumFilas(String consultaEventos) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(consultaEventos);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	private int getNumColumnas(String consultaEventos) {
		int num = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(consultaEventos);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			num = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public DefaultTableModel getModeloTablaEventos() {
		return modeloTablaEventos;
	}

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

	public void verificarLogin(String user, String pass) {
		crearConexion();
		fallosLogin = 0;

		int resultado = 0;
		String queryLogin = "Select * from usuarios where nombre=? AND passwd=?";
		try {
			PreparedStatement pstmtLogin = conexion.prepareStatement(queryLogin);
			pstmtLogin.setString(1, user);
			pstmtLogin.setString(2, pass);
			ResultSet rset = pstmtLogin.executeQuery();
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

	public String getResultadoLogin() {
		return resultadoLogin;
	}

	public void crearUsuario(String nombre, String email, String pass) {
		resultadoSignup = "Correcto";
		// Comprobamos que no exista el nombre
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

		// Comprobamos que sea valida la contraseña
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

	public String getResultadoSignup() {
		return resultadoSignup;
	}

	public void setNombreCabecera(String user) {
		nombreUsuario = user;
		((InicioAPP) pantallas[3]).setNombreCabecera();
		((CrearEvento) pantallas[6]).setNombreCabecera();
		((Eventos) pantallas[7]).setNombreCabecera();
		((InfoEvento) pantallas[4]).setNombreCabecera();
		((Foro) pantallas[9]).setNombreCabecera();
		((Ayuda) pantallas[2]).setNombreCabecera();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getResultadoCrear() {
		return resultadoCrearEvento;
	}

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

	public void unirAEvento(String nombreEvento2) {

		// Sacar codigo de evento
		resultadoUnirse = "Correcto";
		String consultaCodEvento = "select codigoEvento from eventos where nombre =?";
		try {
			PreparedStatement codEvento = conexion.prepareStatement(consultaCodEvento);
			codEvento.setString(1, nombreEvento2);
			ResultSet resultado = codEvento.executeQuery();
			resultado.next();
			codigoEvento = resultado.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
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
		// Comprobar que no est� el usuario en el evento

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

	public String getResultadoUnirse() {
		return resultadoUnirse;
	}

	public void crearEvento(String nombreEvento2, String deporte2, String fecha2, String lugar2, int numParticipantes2,
			String observaciones2) {
		int codigoDeporte = 0;
		resultadoCrearEvento = "Correcto";
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

		// Al crear un evento directamente se crea el foro
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

	public DefaultTableModel getModeloTablaAdmin() {
		return modeloTablaAdmin;
	}

	public void setPantallas(JFrame[] pantallas) {
		this.pantallas = pantallas;
	}

	public TableModel getModeloTablaUsuarioXEvento() {
		return modeloTablaUsuarioXEvento;
	}

	public void subirMensaje(String mensaje) {
		String insertMensaje = "insert into mensaje (mensaje,cod_usuario,cod_foro) values (?,?,?)";
		try {
			PreparedStatement insertM = conexion.prepareStatement(insertMensaje);
			insertM.setString(1, mensaje);
			insertM.setInt(2, codigoUsuario);
			insertM.setInt(3, codigoEvento);
			insertM.executeUpdate();
			insertM.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cargarForo();
		((Foro) pantallas[9]).actualizar();
	}

	private void cargarForo() {
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

	public TableModel getModeloChat() {
		return modeloTablaChat;
	}

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

	public void ponerCamposGestion(String user) {
		String consultaGestion = "select * from usuarios where nombre = \"" + user + "\"";
		try {
			Statement camposGestion = conexion.createStatement();
			ResultSet asignarCampos = camposGestion.executeQuery(consultaGestion);
			asignarCampos.next();
			email = asignarCampos.getString(3);
			poblacion = asignarCampos.getString(4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		((GestionDePerfil) pantallas[5]).actualizar();
	}

	public String getEmail() {
		return email;
	}

	public String getUbicacion() {
		return poblacion;
	}

	public void verArchivo() {

	}

	public String getUserBD() {
		return userBD;
	}

	public String getPassBD() {
		return pwdBD;
	}

	public String getRutaBD() {
		return url;
	}

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

	public void setTablaAdmin(DefaultTableModel tableModel) {
		modeloTablaAdmin = tableModel;

	}

	public void setTablaEventos(DefaultTableModel tabla) {
		modeloTablaEventos = tabla;
	}

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
	
}
