package vista;

/**
 * Clase pantalla foro - asociado a cada evento - pedirá una tabla al modelo con los 
 * mensajes de ese foro - evento en concreto con las fechas de sus envios
 
 */
import java.awt.EventQueue;

import java.awt.Font;
import controlador.Controlador;
import modelo.Modelo;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;

public class Foro extends JFrame implements Pantallas {

	private JButton btnAyuda;
	private JLabel lblLogo;
	private JButton btnOpciones, btnAtras;
	private JLabel lblNombre;
	private JPanel panel_3;
	private JLabel lblNombreEvento;
	private JPanel cabecera;
	private Modelo modelo;
	private Controlador controlador;
	private DefaultTableModel modeloTabla;
	private String nombreEvento;
	private JButton btnEditarPerfil1;
	private JButton btnCerrarSesion;
	private JLabel lblFotoMaria;
	private JLabel fotoLogoEvento;
	private JTextArea textAreaEscribirMensaje;
	private JButton btnPublicar;
	private JLabel lblNuevoEvento;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable table_1;
	private JPanel panelOpciones;

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
	public Foro() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});

		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Info. del evento");
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(640, 532);
		setLocationRelativeTo(null);

		panelOpciones = new JPanel();
		panelOpciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panelOpciones.setVisible(false);
		panelOpciones.setBackground(Color.BLACK);
		panelOpciones.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panelOpciones.setBounds(394, 66, 168, 90);
		getContentPane().add(panelOpciones);
		panelOpciones.setLayout(null);

		btnEditarPerfil1 = new JButton("Editar Perfil");
		btnEditarPerfil1.addMouseListener(new MouseAdapter() {

		});
		btnEditarPerfil1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(9, 5);
				panelOpciones.setVisible(false);

			}
		});
		btnEditarPerfil1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEditarPerfil1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarPerfil1.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		btnEditarPerfil1.setBackground(Color.WHITE);
		btnEditarPerfil1.setBounds(2, 2, 164, 44);
		panelOpciones.add(btnEditarPerfil1);

		btnCerrarSesion = new JButton("Cerrar Sesiï¿½n");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(9, 0);
				panelOpciones.setVisible(false);
			}
		});
		btnCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrarSesion.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		btnCerrarSesion.setBackground(Color.WHITE);
		btnCerrarSesion.setBounds(2, 45, 164, 43);
		panelOpciones.add(btnCerrarSesion);

		cabecera = new JPanel();
		cabecera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		cabecera.setBackground(SystemColor.controlHighlight);
		cabecera.setBounds(0, 0, 626, 95);
		getContentPane().add(cabecera);
		cabecera.setLayout(null);

		lblNombre = new JLabel("");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(411, 21, 146, 23);
		cabecera.add(lblNombre);
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 14));

		btnOpciones = new JButton("Opciones");
		btnOpciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelOpciones.setVisible(true);
			}
		});
		btnOpciones.setHorizontalAlignment(SwingConstants.RIGHT);
		btnOpciones.setBorder(null);
		btnOpciones.setBackground(SystemColor.controlHighlight);
		btnOpciones.setBounds(480, 41, 78, 23);
		cabecera.add(btnOpciones);

		lblLogo = new JLabel("SportsChoice");
		lblLogo.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblLogo.setIcon(new ImageIcon(InfoEvento.class.getResource("/Imagenes/LogoS (1).png")));
		lblLogo.setBounds(10, 11, 227, 73);
		cabecera.add(lblLogo);

		lblFotoMaria = new JLabel("");
		lblFotoMaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoMaria.setBounds(564, 21, 52, 52);
		cabecera.add(lblFotoMaria);
		lblFotoMaria.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/4ss.png")));

		btnAyuda = new JButton("");
		btnAyuda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		btnAyuda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(9, 2);
			}
		});
		btnAyuda.setBorder(null);
		btnAyuda.setBackground(new Color(255, 255, 255));
		btnAyuda.setBounds(591, 94, 35, 32);
		getContentPane().add(btnAyuda);
		btnAyuda.setActionCommand("");
		btnAyuda.setIcon(new ImageIcon(InfoEvento.class.getResource("/imagenes/ayuddddda.png")));

		btnAtras = new JButton("");
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(9, 4);
			}
		});
		btnAtras.setBorder(null);
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(0, 459, 35, 32);
		getContentPane().add(btnAtras);
		btnAtras.setActionCommand("");
		btnAtras.setIcon(new ImageIcon(InfoEvento.class.getResource("/imagenes/btnAtras.png")));

		fotoLogoEvento = new JLabel("");
		fotoLogoEvento.setBounds(228, 106, 60, 53);
		getContentPane().add(fotoLogoEvento);
		fotoLogoEvento.setHorizontalAlignment(SwingConstants.CENTER);
		fotoLogoEvento.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/Eventos.png")));

		lblNewLabel = new JLabel("FORO");
		lblNewLabel.setFont(new Font("MADE TOMMY", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel.setBounds(286, 106, 98, 50);
		getContentPane().add(lblNewLabel);

		textAreaEscribirMensaje = new JTextArea();
		textAreaEscribirMensaje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		textAreaEscribirMensaje.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
		textAreaEscribirMensaje.setBackground(SystemColor.controlHighlight);
		textAreaEscribirMensaje.setBounds(28, 416, 407, 32);
		getContentPane().add(textAreaEscribirMensaje);

		btnPublicar = new JButton("Publicar");
		btnPublicar.setVerticalAlignment(SwingConstants.TOP);
		btnPublicar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPublicar.setFont(new Font("Bauhaus 93", Font.PLAIN, 19));
		btnPublicar.setBackground(new Color(173, 255, 47));
		btnPublicar.setBorder(new LineBorder(SystemColor.controlShadow, 3, true));
		btnPublicar.addMouseListener(new MouseAdapter() {
		});
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.publicarMensaje();
				panelOpciones.setVisible(false);
			}
		});
		btnPublicar.setBounds(450, 416, 141, 32);
		getContentPane().add(btnPublicar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 167, 563, 231);
		getContentPane().add(scrollPane);

		table_1 = new JTable();
		table_1.setShowHorizontalLines(false);
		table_1.setShowVerticalLines(false);
		table_1.setEnabled(false);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				table_1.setModel(modelo.getModeloChat());
			}
		});
		scrollPane.setViewportView(table_1);

	}

	/**
	 * Devuelve al controlador el mensaje escrito
	 * 
	 * @return mensaje escrito en el textarea
	 */
	public String getMensaje() {
		return textAreaEscribirMensaje.getText();
	}

	/**
	 * Método para actualizar la pantalla tras insertar el mensaje y borrar el
	 * textarea a modo de app de chat
	 */
	public void actualizar() {
		table_1.setModel(modelo.getModeloChat());
		textAreaEscribirMensaje.setText("");
	}
	/**
	 * Poner nombre de cabecera del usuario que inicio sesión
	 */
	public void setNombreCabecera() {
		String nombreUsuario = modelo.getNombreUsuario();
		lblNombre.setText(nombreUsuario);
	}
}
