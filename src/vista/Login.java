package vista;

/**
 * Clase pantalla Login - En ella podremos iniciar sesión o ir a la pantalla registro
 */
import java.awt.EventQueue;
import controlador.Controlador;
import modelo.Modelo;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import java.awt.Panel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Cursor;
import javax.swing.JTextArea;

public class Login extends JFrame  implements Pantallas {

	private JTextField txtUser;
	private JPanel panel, panel_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_User;
	private JButton btnLogin;
	private JLabel lblNewLabel_7;
	private Modelo modelo;
	private Controlador controlador;
	private JLabel lblTextoError;
	private JLabel lblcon;
	private JLabel lblNewLabel_NUser;
	private JPasswordField txtPassword;
	private JButton btnDatosConexion;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Setters MVC
	 * 
	 * @param controlador y modelo
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * Constructor por defecto, instanciación de los elementos, así como llamadas al
	 * controlador si se llama a los distintos botones.
	 */
	public Login() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Inicio de sesion");
		setSize(640, 532);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 640, 95);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSportsChoice = new JLabel("SportsChoice");
		lblSportsChoice.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblSportsChoice.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/LogoS (1).png")));
		lblSportsChoice.setBounds(10, 11, 227, 73);
		panel.add(lblSportsChoice);

		lblNewLabel = new JLabel("Datos Conexi\u00F3n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblNewLabel.setBounds(396, 40, 180, 14);
		panel.add(lblNewLabel);

		btnNewButton = new JButton("");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setDefaultCapable(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(0, 10);
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/imagenes/BBDD2.png")));
		btnNewButton.setBounds(541, 0, 89, 95);
		panel.add(btnNewButton);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new LineBorder(SystemColor.controlHighlight, 4, true));
		panel_1.setBounds(188, 127, 259, 335);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtUser = new JTextField();
		txtUser.setForeground(Color.BLACK);
		txtUser.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
		txtUser.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtUser.setBackground(SystemColor.controlHighlight);
		txtUser.setBounds(61, 91, 161, 26);
		panel_1.add(txtUser);
		txtUser.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("INICIAR SESION");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Bauhaus 93", Font.BOLD, 26));
		lblNewLabel_2.setBounds(0, 25, 259, 35);
		panel_1.add(lblNewLabel_2);

		btnLogin = new JButton("Iniciar Sesi\u00F3n");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.login();
			}
		});
		btnLogin.setFont(new Font("Bauhaus 93", Font.PLAIN, 16));
		btnLogin.setBackground(new Color(173, 255, 47));
		btnLogin.setBounds(46, 210, 161, 35);
		panel_1.add(btnLogin);

		lblNewLabel_User = new JLabel("New label");
		lblNewLabel_User.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/logo.png")));
		lblNewLabel_User.setBounds(-29, 71, 99, 58);
		panel_1.add(lblNewLabel_User);

		lblNewLabel_NUser = new JLabel("Nombre de usuario");
		lblNewLabel_NUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_NUser.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblNewLabel_NUser.setBounds(46, 66, 161, 26);
		panel_1.add(lblNewLabel_NUser);

		JLabel lblNewLabel_TextoContrasena = new JLabel("Contrase\u00F1a");
		lblNewLabel_TextoContrasena.setBounds(10, 128, 184, 26);
		panel_1.add(lblNewLabel_TextoContrasena);
		lblNewLabel_TextoContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_TextoContrasena.setFont(new Font("Century Gothic", Font.PLAIN, 13));

		lblcon = new JLabel("New label");
		lblcon.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/Imagen15.png")));
		lblcon.setBounds(-27, 138, 97, 59);
		panel_1.add(lblcon);

		JButton btnSign = new JButton("Registrate");
		btnSign.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(0, 1);
			}
		});
		btnSign.setBackground(SystemColor.controlHighlight);
		btnSign.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnSign.setBounds(82, 280, 94, 23);
		panel_1.add(btnSign);

		lblNewLabel_7 = new JLabel("\u00BFTodav\u00EDa no tienes cuenta? ");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(0, 256, 269, 24);
		panel_1.add(lblNewLabel_7);

		lblTextoError = new JLabel("");
		lblTextoError.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextoError.setForeground(new Color(255, 0, 0));
		lblTextoError.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblTextoError.setBackground(Color.WHITE);
		lblTextoError.setBounds(61, 179, 184, 26);
		panel_1.add(lblTextoError);

		txtPassword = new JPasswordField();
		txtPassword.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtPassword.setBackground(SystemColor.controlHighlight);
		txtPassword.setBounds(61, 153, 161, 26);
		panel_1.add(txtPassword);

		// Al desactivar pantalla se ponen los campos vacios
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				txtPassword.setText("");
				txtUser.setText("");
				lblTextoError.setText("");
			}
		});
	}

	/**
	 * Getters para comprobar usuario y contraseña
	 * 
	 * @return user y passs
	 */
	public String getUser() {
		return txtUser.getText();
	}

	public String getPassword() {
		return String.valueOf(txtPassword.getPassword());
	}

	/**
	 * Método que devuelve el resultado del login: si es exitoso o no, o si el rol es
	 * de admin
	 */
	public void actualizar() {
		String resultado = modelo.getResultadoLogin();
		if (resultado.equals("Correcto")) {
			controlador.cambioPantallas(0, 3);
		} else if (resultado.equals("admin")) {
			controlador.cambioPantallas(0, 8);
		} else if (resultado.equals("Incorrecto")) {
			lblTextoError.setText("Usuario o Contraseña incorrectos");
		} else if (resultado.equals("Cerrado")) {
			System.exit(0);
		} else {
			lblTextoError.setText("Error");
		}
	}
}
