package vista;

import controlador.Controlador;
import modelo.Modelo;
/**
 * @author JavierCereceda
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.SimpleDateFormat;

import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class CrearEvento extends JFrame {

	private JPanel cabecera, panelCrearEvento;
	private JLabel lblNombrePerfil, lblEditarPerfil, lblFotoMaria, lblLogoSP, fotoEventos, lblNuevoEvento;
	private JLabel lblFecha, lblLugar, lblObservaciones, lblNombre, lblDeporte, lblNumParticipantes;
	private static JTextField txtNombreEvento;
	private static JTextField textLugar;
	private static JTextArea textAreaObservaciones;
	private static JSpinner spinnerFecha;
	private static JSpinner spinnerNumParticipantes;
	private JScrollPane scrollPaneObservaciones;
	private JButton btnCrear, btnAtras;
	private JButton btnAyuda;
	private JButton btnOpciones;
	private static JComboBox cmbbxSelectDeporte;
	private Modelo modelo;
	private Controlador controlador;
	private JLabel lblError;
	private JPanel panelOpciones;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public CrearEvento() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		setResizable(false);
		setTitle("Crear Evento");
		getContentPane().setBackground(Color.WHITE);
		setSize(640, 532);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		
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

		JButton btnEditarPerfil1 = new JButton("Editar Perfil");
		btnEditarPerfil1.addMouseListener(new MouseAdapter() {

		});
		btnEditarPerfil1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(6, 5);
				panelOpciones.setVisible(false);

			}
		});
		btnEditarPerfil1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEditarPerfil1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarPerfil1.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		btnEditarPerfil1.setBackground(Color.WHITE);
		btnEditarPerfil1.setBounds(2, 2, 164, 44);
		panelOpciones.add(btnEditarPerfil1);

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(6, 0);
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

		lblNombrePerfil = new JLabel("");
		lblNombrePerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombrePerfil.setBounds(411, 21, 146, 23);
		cabecera.add(lblNombrePerfil);
		lblNombrePerfil.setFont(new Font("Verdana", Font.PLAIN, 14));

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
		

		lblFotoMaria = new JLabel("");
		lblFotoMaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoMaria.setBounds(564, 21, 52, 52);
		cabecera.add(lblFotoMaria);
		lblFotoMaria.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/4ss.png")));

		lblLogoSP = new JLabel("SportsChoice");
		lblLogoSP.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblLogoSP.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/LogoS (1).png")));
		lblLogoSP.setBounds(10, 11, 227, 73);
		cabecera.add(lblLogoSP);

		panelCrearEvento = new JPanel();
		panelCrearEvento.setBackground(Color.WHITE);
		panelCrearEvento.setBorder(new LineBorder(new Color(227, 227, 227), 4, true));
		panelCrearEvento.setBounds(134, 113, 347, 353);
		getContentPane().add(panelCrearEvento);
		panelCrearEvento.setLayout(null);

		fotoEventos = new JLabel("");
		fotoEventos.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/Eventos.png")));
		fotoEventos.setBounds(21, 0, 98, 77);
		panelCrearEvento.add(fotoEventos);

		lblNuevoEvento = new JLabel("Nuevo Evento : ");
		lblNuevoEvento.setFont(new Font("Bauhaus 93", Font.PLAIN, 24));
		lblNuevoEvento.setBounds(118, 21, 194, 46);
		panelCrearEvento.add(lblNuevoEvento);

		lblNombre = new JLabel("Nombre de evento: ");
		lblNombre.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNombre.setBounds(31, 77, 125, 15);
		panelCrearEvento.add(lblNombre);

		txtNombreEvento = new JTextField();
		txtNombreEvento.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtNombreEvento.setBackground(SystemColor.controlHighlight);
		txtNombreEvento.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		txtNombreEvento.setBounds(31, 100, 274, 19);
		panelCrearEvento.add(txtNombreEvento);
		txtNombreEvento.setColumns(10);

		lblDeporte = new JLabel("Deporte: ");
		lblDeporte.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblDeporte.setBounds(31, 117, 73, 25);
		panelCrearEvento.add(lblDeporte);

		lblFecha = new JLabel("Fecha: ");
		lblFecha.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblFecha.setBounds(191, 124, 53, 13);
		panelCrearEvento.add(lblFecha);

		spinnerFecha = new JSpinner();
		spinnerFecha.setBorder(new LineBorder(SystemColor.scrollbar, 2));
		spinnerFecha.setBackground(SystemColor.controlHighlight);
		spinnerFecha.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		spinnerFecha.setModel(new SpinnerDateModel(new Date(1652047200000L), null, null, Calendar.DAY_OF_YEAR));
		spinnerFecha.setBounds(191, 141, 114, 22);
		panelCrearEvento.add(spinnerFecha);

		lblLugar = new JLabel("Lugar: ");
		lblLugar.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblLugar.setBounds(35, 174, 45, 20);
		panelCrearEvento.add(lblLugar);

		cmbbxSelectDeporte = new JComboBox();
		cmbbxSelectDeporte.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		cmbbxSelectDeporte.setForeground(Color.BLACK);
		cmbbxSelectDeporte.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		cmbbxSelectDeporte.setModel(new DefaultComboBoxModel(new String[] { "Futbol", "Baloncesto", "Padel" }));
		cmbbxSelectDeporte.setBackground(SystemColor.controlHighlight);
		cmbbxSelectDeporte.setBounds(31, 141, 136, 22);
		panelCrearEvento.add(cmbbxSelectDeporte);

		textLugar = new JTextField();
		textLugar.setBackground(SystemColor.controlHighlight);
		textLugar.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		textLugar.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		textLugar.setBounds(31, 197, 136, 20);
		panelCrearEvento.add(textLugar);
		textLugar.setColumns(10);

		lblNumParticipantes = new JLabel("N\u00BA de participantes: ");
		lblNumParticipantes.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNumParticipantes.setBounds(191, 179, 151, 13);
		panelCrearEvento.add(lblNumParticipantes);

		spinnerNumParticipantes = new JSpinner();
		spinnerNumParticipantes.setForeground(SystemColor.controlHighlight);
		spinnerNumParticipantes.setModel(new SpinnerNumberModel(2, 2, 20, 1));
		spinnerNumParticipantes.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		spinnerNumParticipantes.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		spinnerNumParticipantes.setBackground(SystemColor.controlHighlight);
		spinnerNumParticipantes.setBounds(191, 197, 114, 20);
		panelCrearEvento.add(spinnerNumParticipantes);

		lblObservaciones = new JLabel("Observaciones: ");
		lblObservaciones.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblObservaciones.setBounds(31, 224, 109, 13);
		panelCrearEvento.add(lblObservaciones);

		scrollPaneObservaciones = new JScrollPane();
		scrollPaneObservaciones.setBounds(31, 247, 274, 46);
		panelCrearEvento.add(scrollPaneObservaciones);

		textAreaObservaciones = new JTextArea();
		textAreaObservaciones.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		textAreaObservaciones.setBorder(new LineBorder(SystemColor.scrollbar, 1, true));
		textAreaObservaciones.setBackground(SystemColor.controlHighlight);
		scrollPaneObservaciones.setViewportView(textAreaObservaciones);

		btnCrear = new JButton("Crear");
		btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.crearEvento();
				limpiarCampos();
			}
		});
		btnCrear.setBorder(new LineBorder(SystemColor.controlShadow, 2, true));
		btnCrear.setFont(new Font("Bauhaus 93", Font.PLAIN, 17));
		btnCrear.setBackground(new Color(173, 255, 47));
		btnCrear.setBounds(118, 312, 98, 25);
		panelCrearEvento.add(btnCrear);

		lblError = new JLabel("");
		lblError.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(31, 293, 274, 19);
		panelCrearEvento.add(lblError);

		btnAtras = new JButton("");
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(6, 3);
			}
		});
		btnAtras.setBorder(null);
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(0, 459, 35, 32);
		getContentPane().add(btnAtras);
		btnAtras.setActionCommand("");
		btnAtras.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/btnAtras.png")));

		btnAyuda = new JButton("");
		btnAyuda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(6, 2);
			}
		});
		btnAyuda.setBorder(null);
		btnAyuda.setBackground(new Color(255, 255, 255));
		btnAyuda.setBounds(591, 94, 35, 32);
		getContentPane().add(btnAyuda);
		btnAyuda.setActionCommand("");
		btnAyuda.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/ayuddddda.png")));

	}

	public static String getNombreEvento() {
		return txtNombreEvento.getText();
	}

	protected void limpiarCampos() {
		txtNombreEvento.setText(null);
		textAreaObservaciones.setText(null);
		textLugar.setText(null);

	}

	public void setNombreCabecera() {
		String nombreUsuario = modelo.getNombreUsuario();
		lblNombrePerfil.setText(nombreUsuario);

	}

	public static String getDeporte() {
		return (String) cmbbxSelectDeporte.getSelectedItem();
	}

	public static String getFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaComoCadena = sdf.format(spinnerFecha.getValue());
		return fechaComoCadena;
	}

	public static String getLugar() {
		return textLugar.getText();
	}

	public static int getNumParticipantes() {
		return (Integer) spinnerNumParticipantes.getValue();
	}

	public static String getObservaciones() {
		return textAreaObservaciones.getText();
	}

	public void actualizar() {
		String resultado = modelo.getResultadoCrear();
		if (resultado.equals("Correcto")) {
			lblError.setForeground(SystemColor.black);
			lblError.setText("Evento Creado");
		} else {
			lblError.setForeground(SystemColor.red);
			lblError.setText("Nombre invalido, vuelve a intentarlo");
		}
	}
}
