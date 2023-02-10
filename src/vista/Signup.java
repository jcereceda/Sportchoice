package vista;

/**
 * Clase pantalla Signup con un formulario para registrar un usuario nuevo con la informacion basica
 */
import controlador.Controlador;
import modelo.Modelo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Cursor;

public class Signup extends JFrame implements Pantallas{

	private JTextField txtUsuario;
	private JTextField txtCorreo;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblcon;
	private JLabel lblcon_1;
	private JButton btnSignUp;
	private JButton btnLog;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private Modelo modelo;
	private Controlador controlador;
	private JLabel lblError;
	private JPasswordField textContrasena1;
	private JPasswordField textContrasena2;

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
	public Signup() {

		getContentPane().setBackground(Color.WHITE);
		setTitle("Registro Sportchoice");
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

		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/Imagen4.png")));
		lblNewLabel_2.setBounds(502, 17, 122, 55);
		panel.add(lblNewLabel_2);

		JLabel lblUsername = new JLabel("Usuario");
		lblUsername.setBounds(475, 36, 92, 16);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Century Gothic", Font.PLAIN, 16));

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(227, 227, 227), 4, true));
		panel_1.setBackground(SystemColor.textHighlightText);
		panel_1.setBounds(185, 104, 269, 380);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/logo.png")));
		lblNewLabel_4.setBounds(-31, 60, 91, 47);
		panel_1.add(lblNewLabel_4);

		txtUsuario = new JTextField();
		txtUsuario.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtUsuario.setBackground(SystemColor.controlHighlight);
		txtUsuario.setBounds(61, 69, 184, 26);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/Imagen13.png")));
		lblNewLabel_5.setBounds(-31, 114, 101, 47);
		panel_1.add(lblNewLabel_5);

		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtCorreo.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setBounds(61, 124, 184, 26);
		panel_1.add(txtCorreo);
		txtCorreo.setColumns(10);

		lblcon = new JLabel("New label");
		lblcon.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/Imagen15.png")));
		lblcon.setBounds(-27, 163, 97, 45);
		panel_1.add(lblcon);

		lblcon_1 = new JLabel("New label");
		lblcon_1.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/Imagen15.png")));
		lblcon_1.setBounds(-27, 212, 97, 45);
		panel_1.add(lblcon_1);

		btnSignUp = new JButton("REGISTRATE");
		btnSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.crearUsuario();
			}
		});
		btnSignUp.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
		btnSignUp.setBackground(new Color(173, 255, 47));
		btnSignUp.setBounds(72, 278, 135, 33);
		panel_1.add(btnSignUp);

		btnLog = new JButton("ENTRA");
		btnLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(1, 0);
				;
			}
		});
		btnLog.setBackground(SystemColor.controlHighlight);
		btnLog.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnLog.setBounds(97, 346, 78, 23);
		panel_1.add(btnLog);

		lblNewLabel_6 = new JLabel("REGISTRO");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Bauhaus 93", Font.PLAIN, 30));
		lblNewLabel_6.setBounds(41, 11, 184, 47);
		panel_1.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("\u00BFYa tienes cuenta? Entra.");
		lblNewLabel_7.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(0, 315, 269, 33);
		panel_1.add(lblNewLabel_7);

		JLabel lblNewLabel_NUser = new JLabel("Nombre de usuario");
		lblNewLabel_NUser.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_NUser.setBounds(61, 54, 201, 14);
		panel_1.add(lblNewLabel_NUser);

		JLabel lblNewLabel_7_1_1 = new JLabel("Correo electronico");
		lblNewLabel_7_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_7_1_1.setBounds(61, 106, 201, 14);
		panel_1.add(lblNewLabel_7_1_1);

		JLabel lblNewLabel_TextoContrasena = new JLabel("Contrase\u00F1a");
		lblNewLabel_TextoContrasena.setBounds(61, 149, 201, 26);
		panel_1.add(lblNewLabel_TextoContrasena);
		lblNewLabel_TextoContrasena.setFont(new Font("Century Gothic", Font.PLAIN, 12));

		JLabel lblNewLabel_7_1_1_1_1 = new JLabel("Confirmar contrase\u00F1a");
		lblNewLabel_7_1_1_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_7_1_1_1_1.setBounds(64, 209, 198, 14);
		panel_1.add(lblNewLabel_7_1_1_1_1);

		lblError = new JLabel("");
		lblError.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblError.setBounds(61, 251, 208, 26);
		panel_1.add(lblError);

		textContrasena1 = new JPasswordField();
		textContrasena1.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		textContrasena1.setBounds(61, 173, 184, 25);
		panel_1.add(textContrasena1);
		textContrasena1.setBackground(SystemColor.controlHighlight);

		textContrasena2 = new JPasswordField();
		textContrasena2.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		textContrasena2.setBackground(SystemColor.controlHighlight);
		textContrasena2.setBounds(61, 226, 184, 26);
		panel_1.add(textContrasena2);
	}

	/**
	 * Getter llamado por el controlador para crear un nuevo usuarios
	 * 
	 * @return nombre, email, pass
	 */
	public String getNombre() {
		return txtUsuario.getText();
	}

	public String getEmail() {
		return txtCorreo.getText();
	}

	// Comprobacion de que sean iguales las contraseñas
	public String getPass() {
		if ((String.valueOf(textContrasena1.getPassword())).equals(String.valueOf(textContrasena2.getPassword()))) {
			return String.valueOf(textContrasena2.getPassword());
		} else {
			return "";
		}

	}

	/**
	 * Método para saber si el registro es correcto o no
	 */
	public void actualizar() {
		String resultadoSignup = modelo.getResultadoSignup();
		if (resultadoSignup.equals("Correcto")) {
			lblError.setForeground(SystemColor.black);
			lblError.setText("Usuario Creado");
			txtCorreo.setText("");
			txtUsuario.setText("");
			textContrasena1.setText("");
			textContrasena2.setText("");
		} else if (resultadoSignup.equals("passwd no coincidentes")) {
			lblError.setForeground(SystemColor.RED);
			lblError.setText("Las contraseñas no coinciden");
		} else {
			lblError.setForeground(SystemColor.RED);
			lblError.setText("Usuario o correo no valido");
		}
	}
}
