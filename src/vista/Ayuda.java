package vista;

/**
 * Pantalla ayuda, méramente informativa para hablar sobre el proyecto, o a quién está dirigido
 */
import controlador.Controlador;
import modelo.Modelo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Cursor;

public class Ayuda extends JFrame {

	private JPanel cabecera;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblEditarPerfil;
	private JLabel lblNombre;
	private AbstractButton btnAtras;

	private Modelo modelo;
	private Controlador controlador;
	private JButton btnOpciones;
	private JLabel lblSportsChoice;
	private JLabel lblFotoMaria;
	private JButton btnEditarPerfil1;
	private JButton btnCerrarSesion;
	private JPanel panelOpciones;
	private JPanel panelOpciones_1;
	/**
	 * Setters MVC
	 * @param controlador y modelo
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * Constructor e instanciación de los elementos de la pantalla
	 *
	 */
	public Ayuda() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones_1.setVisible(false);
			}
		});

		setSize(640, 532);
		setTitle("Ayuda");
		setLocationRelativeTo(null);
		getContentPane().setBackground(SystemColor.window);
		setResizable(false);
		setSize(640, 532);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		JPanel panelOpciones = new JPanel();

		panelOpciones_1 = new JPanel();

		panelOpciones_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		panelOpciones_1.setVisible(false);
		panelOpciones_1.setBackground(Color.BLACK);
		panelOpciones_1.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelOpciones_1.setBounds(394, 66, 168, 90);
		getContentPane().add(panelOpciones_1);
		panelOpciones_1.setLayout(null);

		btnEditarPerfil1 = new JButton("Editar Perfil");
		btnEditarPerfil1.addMouseListener(new MouseAdapter() {

		});
		btnEditarPerfil1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(2, 5);
				panelOpciones_1.setVisible(false);

			}
		});
		btnEditarPerfil1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEditarPerfil1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarPerfil1.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		btnEditarPerfil1.setBackground(Color.WHITE);
		btnEditarPerfil1.setBounds(2, 2, 164, 44);
		panelOpciones_1.add(btnEditarPerfil1);

		btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(2, 0);
				panelOpciones_1.setVisible(false);
			}
		});
		btnCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrarSesion.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		btnCerrarSesion.setBackground(Color.WHITE);
		btnCerrarSesion.setBounds(2, 45, 164, 43);
		panelOpciones_1.add(btnCerrarSesion);

		cabecera = new JPanel();
		cabecera.setBackground(SystemColor.controlHighlight);
		cabecera.setBounds(0, 0, 624, 88);
		getContentPane().add(cabecera);
		cabecera.setLayout(null);
		cabecera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones_1.setVisible(false);
			}
		});

		lblSportsChoice = new JLabel("SportsChoice");
		lblSportsChoice.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblSportsChoice.setIcon(new ImageIcon(Ayuda.class.getResource("/imagenes/LogoS (1).png")));
		lblSportsChoice.setBounds(10, 11, 227, 73);
		cabecera.add(lblSportsChoice);

		lblNombre = new JLabel("");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(411, 21, 146, 23);
		cabecera.add(lblNombre);
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 14));

		btnOpciones = new JButton("Opciones");
		btnOpciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelOpciones_1.setVisible(true);
			}
		});
		btnOpciones.setHorizontalAlignment(SwingConstants.RIGHT);
		btnOpciones.setBorder(null);
		btnOpciones.setBackground(SystemColor.controlHighlight);
		btnOpciones.setBounds(480, 41, 78, 23);
		cabecera.add(btnOpciones);

		btnAtras = new JButton("");
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(2, 3);
				panelOpciones_1.setVisible(false);
			}
		});
		btnAtras.setBorder(null);
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(0, 459, 35, 32);
		getContentPane().add(btnAtras);
		btnAtras.setActionCommand("");
		btnAtras.setIcon(new ImageIcon(InfoEvento.class.getResource("/imagenes/btnAtras.png")));

		lblFotoMaria = new JLabel("");
		lblFotoMaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoMaria.setBounds(564, 21, 52, 52);
		cabecera.add(lblFotoMaria);
		lblFotoMaria.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/4ss.png")));

		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Ayuda.class.getResource("/imagenes/Imagen17.png")));
		lblNewLabel_4.setBounds(-6, 99, 105, 47);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("FAQ\r\n");
		lblNewLabel_5.setFont(new Font("MADE TOMMY", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel_5.setBounds(83, 99, 98, 47);
		getContentPane().add(lblNewLabel_5);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.controlHighlight, 4, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(40, 150, 552, 320);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones_1.setVisible(false);
			}
		});

		JTextPane txtpnParaQue_1 = new JTextPane();
		txtpnParaQue_1.setBounds(10, 11, 532, 29);
		panel_1.add(txtpnParaQue_1);
		txtpnParaQue_1.setEditable(false);
		txtpnParaQue_1.setBackground(SystemColor.controlHighlight);
		txtpnParaQue_1.setForeground(Color.BLACK);
		txtpnParaQue_1.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 18));
		txtpnParaQue_1.setText(" \u00BFPara que sirve SportsChoice?");

		JTextPane txtpnParaQue_2 = new JTextPane();
		txtpnParaQue_2.setBounds(10, 38, 532, 81);
		panel_1.add(txtpnParaQue_2);
		txtpnParaQue_2.setEditable(false);
		txtpnParaQue_2.setBackground(Color.WHITE);
		txtpnParaQue_2.setText(
				"SportsChoice es una aplicacion para los mas deportistas. \r\nSu funcionamiento es sencillo, una vez registrado podr\u00E1s acceder a los \r\ndistintos eventos, generados por los usuarios o crear uno para\r\nque el resto de usuarios pueda unirse.");
		txtpnParaQue_2.setFont(new Font("Century Gothic", Font.PLAIN, 15));

		JTextPane txtpnParaQue_1_1 = new JTextPane();
		txtpnParaQue_1_1.setBounds(10, 122, 532, 29);
		panel_1.add(txtpnParaQue_1_1);
		txtpnParaQue_1_1.setEditable(false);
		txtpnParaQue_1_1.setBackground(SystemColor.controlHighlight);
		txtpnParaQue_1_1.setText(" \u00BFQue deportes puedo encontrar?");
		txtpnParaQue_1_1.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 18));

		JTextPane txtpnParaQue_2_1 = new JTextPane();
		txtpnParaQue_2_1.setBounds(10, 147, 532, 46);
		panel_1.add(txtpnParaQue_2_1);
		txtpnParaQue_2_1.setEditable(false);
		txtpnParaQue_2_1.setText("En SportsChoice podremos encontrar varios deportes como Baloncesto, Futbol o Padel.");
		txtpnParaQue_2_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		txtpnParaQue_2_1.setBackground(Color.WHITE);

		JTextPane txtpnParaQue_1_1_1 = new JTextPane();
		txtpnParaQue_1_1_1.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		txtpnParaQue_1_1_1.setBounds(10, 194, 532, 29);
		panel_1.add(txtpnParaQue_1_1_1);
		txtpnParaQue_1_1_1.setEditable(false);
		txtpnParaQue_1_1_1.setText(" \u00BFComo funciona?");
		txtpnParaQue_1_1_1.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 18));
		txtpnParaQue_1_1_1.setBackground(SystemColor.controlHighlight);

		JTextPane txtpnParaQue_2_1_1 = new JTextPane();
		txtpnParaQue_2_1_1.setBounds(10, 223, 532, 89);
		panel_1.add(txtpnParaQue_2_1_1);
		txtpnParaQue_2_1_1.setText(
				"Esta app tiene un uso muy intuitivo. Cuenta con un dise\u00F1o minamilista, especialmente dise\u00F1ado para las personas que no esten familiarizadas con las nuevas tecnolog\u00EDas. La app te permite crear como unirse a eventos deportivos y organizarlos de la mejor manera.");
		txtpnParaQue_2_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		txtpnParaQue_2_1_1.setEditable(false);
		txtpnParaQue_2_1_1.setBackground(Color.WHITE);
	}
	/**
	 * Poner nombre de cabecera del usuario que inicio sesión
	 */
	public void setNombreCabecera() {
		String nombreUsuario = modelo.getNombreUsuario();
		lblNombre.setText(nombreUsuario);
	}

}
