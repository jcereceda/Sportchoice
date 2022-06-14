package vista;

/**
 * Pantalla con un formulario para modificar los datos de conexion 
 */
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import controlador.Controlador;
import modelo.Modelo;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class DatosConex extends JFrame {

	private JFrame frame;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JTextField txtRuta;
	private Controlador controlador;
	private Modelo modelo;
	private JButton btnVer;
	private JPanel panel_1;
	private JLabel lblUsuario;
	private JLabel lblLogoSP;
	private JPanel cabeceraDatos;
	private JButton btnModificar;
	private JLabel lblActualizacion;
	private JButton btnAtras;
	private JLabel lblUser;
	private JLabel lblPasword;
	private JLabel lblRuta;

	/**
	 * Constructor por defecto, instanciación de los elementos, así como llamadas al
	 * controlador si se llama a los distintos botones.
	 */
	public DatosConex() {

		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setSize(640, 532);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		cabeceraDatos = new JPanel();
		cabeceraDatos.setBackground(SystemColor.controlHighlight);
		cabeceraDatos.setBounds(0, 0, 626, 95);
		getContentPane().add(cabeceraDatos);
		cabeceraDatos.setLayout(null);

		lblLogoSP = new JLabel("SportsChoice");
		lblLogoSP.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblLogoSP.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/LogoS (1).png")));
		lblLogoSP.setBounds(10, 11, 227, 73);
		cabeceraDatos.add(lblLogoSP);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(227, 227, 227), 4, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(146, 117, 347, 353);
		getContentPane().add(panel_1);

		JLabel lblAccesoFichero = new JLabel("Datos de conexion BBDD");
		lblAccesoFichero.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccesoFichero.setFont(new Font("Bauhaus 93", Font.PLAIN, 24));
		lblAccesoFichero.setBounds(0, 22, 347, 46);
		panel_1.add(lblAccesoFichero);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblUsuario.setBounds(85, 79, 125, 15);
		panel_1.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtUsuario.setBackground(SystemColor.controlHighlight);
		txtUsuario.setBounds(85, 95, 220, 25);
		panel_1.add(txtUsuario);

		JLabel lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblContrasena.setBounds(85, 126, 138, 25);
		panel_1.add(lblContrasena);

		lblUser = new JLabel("");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/logo.png")));
		lblUser.setBounds(22, 78, 53, 47);
		panel_1.add(lblUser);

		JLabel lblUser3 = new JLabel("");
		lblUser3.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/ruta2.png")));
		lblUser3.setBounds(22, 192, 53, 47);
		panel_1.add(lblUser3);

		lblPasword = new JLabel("");
		lblPasword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasword.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/Imagen15.png")));
		lblPasword.setBounds(10, 136, 73, 45);
		panel_1.add(lblPasword);

		lblRuta = new JLabel("Ruta");
		lblRuta.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblRuta.setBounds(85, 185, 45, 20);
		panel_1.add(lblRuta);
		// Al cargar la pagina se muestra el contenido del archivo de conexion
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				verArchivo();
			}
		});

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblError.setBounds(31, 293, 274, 19);
		panel_1.add(lblError);

		txtContrasena = new JTextField();
		txtContrasena.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtContrasena.setColumns(10);
		txtContrasena.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtContrasena.setBackground(SystemColor.controlHighlight);
		txtContrasena.setBounds(85, 148, 220, 25);
		panel_1.add(txtContrasena);

		txtRuta = new JTextField();
		txtRuta.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtRuta.setColumns(10);
		txtRuta.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtRuta.setBackground(SystemColor.controlHighlight);
		txtRuta.setBounds(85, 205, 220, 25);
		panel_1.add(txtRuta);

		btnModificar = new JButton("Modificar");
		btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.modificarArchivo();
			}
		});
		btnModificar.setFont(new Font("Bauhaus 93", Font.PLAIN, 17));
		btnModificar.setBorder(new LineBorder(SystemColor.controlShadow, 2, true));
		btnModificar.setBackground(new Color(173, 255, 47));
		btnModificar.setBounds(142, 275, 98, 25);
		panel_1.add(btnModificar);

		lblActualizacion = new JLabel("");
		lblActualizacion.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblActualizacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizacion.setBounds(63, 235, 242, 13);
		panel_1.add(lblActualizacion);

		btnAtras = new JButton("");
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(10, 0);
			}
		});
		btnAtras.setBorder(null);
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(0, 459, 35, 32);
		getContentPane().add(btnAtras);
		btnAtras.setActionCommand("");
		btnAtras.setIcon(new ImageIcon(GestionDePerfil.class.getResource("/imagenes/btnAtras.png")));

	}

	/**
	 * Setters MVC
	 * 
	 * @param controlador y modelo
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModelo(Modelo modelo2) {
		modelo = modelo2;
	}

	/**
	 * Método para mostrar en los campos de texto el contenido del archivo con los
	 * datos de conexion
	 */
	protected void verArchivo() {

		txtUsuario.setText(modelo.getUserBD());
		txtContrasena.setText(modelo.getPassBD());
		txtRuta.setText(modelo.getRutaBD());

	}

	/**
	 * Getters pedidos por el controlador para modificar el archivo con los datos de
	 * conexion
	 * 
	 * @return usuario, pass y ruta de la BD
	 */
	public String getUsuarioBD() {
		return txtUsuario.getText();
	}

	public String getPassBD() {
		return txtContrasena.getText();
	}

	public String getRutaBD() {
		return txtRuta.getText();
	}

	/**
	 * Resultado de actualizar el fichero , llamado por el modelo
	 */
	public void actualizar() {
		lblActualizacion.setText("Fichero actualizado");
		txtContrasena.setText("");
		txtUsuario.setText("");
		txtRuta.setText("");
	}
}
