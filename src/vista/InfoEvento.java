package vista;

/**
 * Clase pantalla infoevento, en ella se puede ver toda la información del evento al que hemos accedido, unirnos, ver los participantes
 * y acceder al foro de el evento
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;

public class InfoEvento extends JFrame implements Pantallas{

	private JButton btnAyuda;
	private JLabel lblLogo;
	private JButton btnOpciones, btnAtras;
	private JLabel lblNombre;
	private JPanel panel_3;
	private JLabel lblInfoEvento;
	private JButton btnUnirse;
	private JPanel panelInfoEvento1;
	private JLabel lblTextoFecha;
	private JLabel lblTextoLugar;
	private JLabel lblObservaciones;
	private JPanel cabecera;
	private Modelo modelo;
	private Controlador controlador;
	private JLabel lblFecha;
	private JLabel lblLugar;
	private JTextArea taObservaciones;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblDeporte;
	private JLabel lblDeporteEscrito;
	private JScrollPane scrollPane_1;
	private DefaultTableModel modeloTabla;
	private String nombreEvento;
	private JLabel lblError;
	private JButton btnEditarPerfil1;
	private JButton btnCerrarSesion;
	private JLabel lblFotoMaria;
	private JButton btnForo;
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
	public InfoEvento() {
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
				controlador.cambioPantallas(4, 5);
				panelOpciones.setVisible(false);

			}
		});
		btnEditarPerfil1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEditarPerfil1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarPerfil1.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		btnEditarPerfil1.setBackground(Color.WHITE);
		btnEditarPerfil1.setBounds(2, 2, 164, 44);
		panelOpciones.add(btnEditarPerfil1);

		btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(4, 0);
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
		cabecera.setBackground(SystemColor.controlHighlight);
		cabecera.setBounds(0, 0, 626, 95);
		getContentPane().add(cabecera);
		cabecera.setLayout(null);
		cabecera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});

		lblNombre = new JLabel("");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(448, 25, 110, 16);
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
		btnAyuda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(4, 2);
				panelOpciones.setVisible(true);
			}
		});
		btnAyuda.setBorder(null);
		btnAyuda.setBackground(new Color(255, 255, 255));
		btnAyuda.setBounds(591, 94, 35, 32);
		getContentPane().add(btnAyuda);
		btnAyuda.setActionCommand("");
		btnAyuda.setIcon(new ImageIcon(InfoEvento.class.getResource("/imagenes/ayuddddda.png")));

		btnAtras = new JButton("");
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(4, 7);
				panelOpciones.setVisible(false);
			}
		});
		btnAtras.setBorder(null);
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(0, 459, 35, 32);
		getContentPane().add(btnAtras);
		btnAtras.setActionCommand("");
		btnAtras.setIcon(new ImageIcon(InfoEvento.class.getResource("/imagenes/btnAtras.png")));

		panelInfoEvento1 = new JPanel();
		panelInfoEvento1.setBackground(Color.WHITE);
		panelInfoEvento1.setBorder(new LineBorder(new Color(227, 227, 227), 4, true));
		panelInfoEvento1.setBounds(22, 133, 583, 325);
		getContentPane().add(panelInfoEvento1);
		panelInfoEvento1.setLayout(null);

		JLabel fotoInfoEventos = new JLabel("");
		fotoInfoEventos.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/Eventos.png")));
		fotoInfoEventos.setBounds(-26, 0, 98, 77);
		panelInfoEvento1.add(fotoInfoEventos);

		lblInfoEvento = new JLabel("");
		lblInfoEvento.setFont(new Font("Bauhaus 93", Font.PLAIN, 24));
		lblInfoEvento.setBounds(65, 21, 218, 46);
		panelInfoEvento1.add(lblInfoEvento);

		btnUnirse = new JButton("Unirse");
		btnUnirse.setVerticalAlignment(SwingConstants.TOP);
		btnUnirse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.unirseaEvento();

			}
		});
		btnUnirse.setBackground(new Color(173, 255, 47));
		btnUnirse.setBorder(new LineBorder(new Color(160, 160, 160), 2, true));
		btnUnirse.setFont(new Font("Bauhaus 93", Font.PLAIN, 17));
		btnUnirse.setBounds(117, 277, 121, 25);
		panelInfoEvento1.add(btnUnirse);

		lblTextoFecha = new JLabel("Fecha:");
		lblTextoFecha.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblTextoFecha.setBounds(21, 84, 49, 35);
		panelInfoEvento1.add(lblTextoFecha);

		lblTextoLugar = new JLabel("Lugar: ");
		lblTextoLugar.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblTextoLugar.setBounds(21, 105, 51, 46);
		panelInfoEvento1.add(lblTextoLugar);

		lblObservaciones = new JLabel("Observaciones :");
		lblObservaciones.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblObservaciones.setBounds(21, 175, 143, 35);
		panelInfoEvento1.add(lblObservaciones);

		lblFecha = new JLabel("");
		lblFecha.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblFecha.setBounds(75, 84, 185, 35);
		panelInfoEvento1.add(lblFecha);

		lblLugar = new JLabel("");
		lblLugar.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblLugar.setBounds(69, 114, 226, 33);
		panelInfoEvento1.add(lblLugar);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 209, 301, 58);
		panelInfoEvento1.add(scrollPane_1);

		taObservaciones = new JTextArea();
		taObservaciones.setEditable(false);
		taObservaciones.setBorder(new LineBorder(new Color(160, 160, 160), 2, true));
		scrollPane_1.setViewportView(taObservaciones);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(377, 42, 178, 233);
		panelInfoEvento1.add(scrollPane);

		table = new JTable();
		table.setRowHeight(19);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				table.setModel(modelo.getModeloTablaUsuarioXEvento());
			}
		});

		scrollPane.setViewportView(table);
		lblDeporte = new JLabel("Deporte: ");
		lblDeporte.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblDeporte.setBounds(21, 140, 65, 35);
		panelInfoEvento1.add(lblDeporte);

		lblDeporteEscrito = new JLabel("");
		lblDeporteEscrito.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblDeporteEscrito.setBounds(89, 142, 206, 33);
		panelInfoEvento1.add(lblDeporteEscrito);

		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblError.setBounds(89, 300, 178, 25);
		panelInfoEvento1.add(lblError);

		btnForo = new JButton("Foro");
		btnForo.setVerticalTextPosition(SwingConstants.TOP);
		btnForo.setVerticalAlignment(SwingConstants.TOP);
		btnForo.setFont(new Font("Bauhaus 93", Font.PLAIN, 16));
		btnForo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnForo.setBackground(new Color(173, 255, 47));
		btnForo.setBorder(new LineBorder(SystemColor.controlShadow, 2, true));
		btnForo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(4, 9);
				panelOpciones.setVisible(false);

			}
		});
		btnForo.setBounds(377, 282, 178, 23);
		panelInfoEvento1.add(btnForo);

	}

	/**
	 * Poner nombre de cabecera del usuario que inicio sesión
	 */
	public void setNombreCabecera() {
		String nombre = modelo.getNombreUsuario();
		lblNombre.setText(nombre);
	}

	/**
	 * Método para el que al cambiar de evento en la pantalla eventos cambie los
	 * campos con los datos del nuevo evento seleccionado
	 */
	public void actualizar() {
		nombreEvento = modelo.getNombreEvento();
		String deporte = modelo.getDeporte();
		String fecha = modelo.getFecha();
		String lugar = modelo.getLugar();
		int numParticipantes = modelo.getNumParticipantes();
		String observaciones = modelo.getObservaciones();
		lblInfoEvento.setText(nombreEvento);
		lblFecha.setText(fecha);
		lblLugar.setText(lugar);
		taObservaciones.setText(observaciones);
		lblDeporteEscrito.setText(deporte);
	}

	/**
	 * Metodo que al intentar unirse al evento nos indica si nos hemos unido o no
	 * nos deja
	 */
	public void actualizarUsers() {
		String usuario = modelo.getNombreUsuario();
		String resultado = modelo.getResultadoUnirse();
		if (resultado.equals("Correcto")) {
			lblError.setForeground(SystemColor.black);
			lblError.setText("Unido al evento");
		} else if (resultado.equals("fallo")) {
			lblError.setForeground(SystemColor.red);
			lblError.setText("Error, ya estás dentro");
		} else {
			lblError.setForeground(SystemColor.red);
			lblError.setText("Error, maxima ocupación");
		}
	}

	/**
	 * Getter
	 * @return nombre del evento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}

	/**
	 * Método para poner modelo de la tabla de los usuarios x evento cuando se une un usuario con el usuario nuevo
	 */
	public void ponerModelo() {
		table.setModel(modelo.getModeloTablaUsuarioXEvento());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				lblError.setText("");
			}
		});
	}
}
