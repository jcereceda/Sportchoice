package vista;

/**
 * Clase Eventos, Mostará una tabla recogida del modelo con los eventos de la BD, podremos seleccionarlos para verlos y 
 * mostrarnos su respectiva pantalla infoevento. También tiene la función de guardar una tabla y cargarla.
 */
import controlador.Controlador;
import modelo.Modelo;
import modelo.Tabla;

/**
 * @autor GonzaloPlaza
 */
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import java.awt.Panel;
import java.awt.SystemColor;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Eventos extends JFrame implements Pantallas {

	private JPanel cabecera;
	private JLabel lblTitulo, lblAyuda, lblFotoMaria, lblEditPerfil, lblNombreU, lblLogo;
	private JButton btnAtras;
	private Modelo modelo;
	private Controlador controlador;
	private JButton btnEditarPerfil;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JScrollPane scrollPane;
	private JButton btnAyuda;
	private JButton btnEntrarEvento;
	int fila;
	private JLabel lblSportsChoice;
	private JLabel lblTodosEventos;
	private JLabel LogoDeEventos;
	private JPanel panelOpciones;
	private JButton btnEditarPerfil1;
	private JButton btnCerrarSesion;
	private JButton btnOpciones;

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
	public Eventos() {
		setResizable(false);
		setTitle("Todos los eventos");
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

		btnEditarPerfil1 = new JButton("Editar Perfil");
		btnEditarPerfil1.addMouseListener(new MouseAdapter() {

		});
		btnEditarPerfil1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(7, 5);
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
				controlador.cambioPantallas(7, 0);
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
		cabecera.setBounds(0, 0, 640, 95);
		getContentPane().add(cabecera);
		cabecera.setLayout(null);

		lblNombreU = new JLabel("");
		lblNombreU.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreU.setBounds(448, 25, 110, 16);
		cabecera.add(lblNombreU);
		lblNombreU.setFont(new Font("Verdana", Font.PLAIN, 14));

		lblFotoMaria = new JLabel("");
		lblFotoMaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoMaria.setBounds(564, 21, 52, 52);
		cabecera.add(lblFotoMaria);
		lblFotoMaria.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/4ss.png")));

		lblSportsChoice = new JLabel("SportsChoice");
		lblSportsChoice.setBounds(10, 11, 227, 73);
		cabecera.add(lblSportsChoice);
		lblSportsChoice.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblSportsChoice.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/LogoS (1).png")));

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
				controlador.cambioPantallas(7, 2);
				panelOpciones.setVisible(false);
			}
		});
		btnAyuda.setBorder(null);
		btnAyuda.setBackground(new Color(255, 255, 255));
		btnAyuda.setBounds(591, 94, 35, 32);
		getContentPane().add(btnAyuda);
		btnAyuda.setActionCommand("");
		btnAyuda.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/ayuddddda.png")));

		btnAtras = new JButton("");
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setBackground(Color.WHITE);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(7, 3);
				habilitarBoton();
				panelOpciones.setVisible(false);
			}
		});
		btnAtras.setBorder(null);
		btnAtras.setIcon(new ImageIcon(Eventos.class.getResource("/imagenes/btnAtras.png")));
		btnAtras.setBounds(0, 459, 35, 32);
		getContentPane().add(btnAtras);

		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
				fila = table.getSelectedRow();
				habilitarBoton();
				table.getSelectionModel().clearSelection();
			}
		});
		scrollPane.setBorder(new LineBorder(SystemColor.controlHighlight, 4, true));
		scrollPane.setBounds(62, 162, 495, 269);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionBackground(new Color(204, 255, 102));
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				fila = table.getSelectedRow();
				habilitarBoton();

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		table.setRowHeight(28);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				table.setModel(modelo.getModeloTablaEventos());
			}
		});

		scrollPane.setViewportView(table);
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				table.getSelectionModel().clearSelection();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
				fila = table.getSelectedRow();
				habilitarBoton();
			}
		});

		lblTodosEventos = new JLabel("Eventos disponibles");
		lblTodosEventos.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		lblTodosEventos.setBounds(70, 117, 349, 30);
		getContentPane().add(lblTodosEventos);

		LogoDeEventos = new JLabel("");
		LogoDeEventos.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/Eventos.png")));
		LogoDeEventos.setBounds(-19, 106, 275, 45);
		getContentPane().add(LogoDeEventos);

		btnEntrarEvento = new JButton("Entrar");
		btnEntrarEvento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		btnEntrarEvento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrarEvento.setEnabled(false);
		btnEntrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fila = table.getSelectedRow();
				habilitarBoton();
				controlador.cambioPantallas(7, 4);
				panelOpciones.setVisible(false);
			}
		});
		btnEntrarEvento.setBounds(250, 442, 125, 34);
		btnEntrarEvento.setBorder(new LineBorder(SystemColor.controlShadow, 2, true));
		btnEntrarEvento.setFont(new Font("Bauhaus 93", Font.PLAIN, 17));
		btnEntrarEvento.setBackground(new Color(173, 255, 47));
		getContentPane().add(btnEntrarEvento);

		JButton btnCargar = new JButton("");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
			}
		});
		btnCargar.setBorder(null);
		btnCargar.setBackground(Color.WHITE);
		btnCargar.setIcon(new ImageIcon(Eventos.class.getResource("/imagenes/upload1.png")));
		btnCargar.setBounds(555, 228, 71, 57);
		getContentPane().add(btnCargar);

		JButton btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarTabla();
			}
		});
		btnGuardar.setIcon(new ImageIcon(Eventos.class.getResource("/imagenes/download1.png")));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBorder(null);
		btnGuardar.setBounds(555, 285, 71, 62);
		getContentPane().add(btnGuardar);
	}

	/**
	 * Método para cargar una tabla guardada en un archivo a modo de Backup
	 */
	protected void cargarTabla() {

		File rutaProyecto = new File(System.getProperty("user.dir"));
		JFileChooser fc = new JFileChooser(rutaProyecto);
		int seleccion = fc.showOpenDialog(getContentPane());
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {
				File fichero = fc.getSelectedFile();
				FileInputStream fis = new FileInputStream(fichero);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Tabla tablaEventos = (Tabla) ois.readObject();
				modelo.setTablaEventos((DefaultTableModel) tablaEventos.getTabla());
				table.setModel(modelo.getModeloTablaEventos());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Método para guardar un objeto tabla en un archivo
	 */
	protected void guardarTabla() {
		File rutaProyecto = new File(System.getProperty("user.dir"));
		JFileChooser fc = new JFileChooser(rutaProyecto);
		int seleccion = fc.showSaveDialog(getContentPane());
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = fc.getSelectedFile();
			try {
				FileOutputStream fos = new FileOutputStream(fichero);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				Tabla tablaEventos = new Tabla(modelo.getModeloTablaEventos());
				oos.writeObject(tablaEventos);
				fos.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Metodo para habilitar o desabilitar botón en caso de tener seleccionada
	 * alguna fila de la tabla o no
	 */
	protected void habilitarBoton() {
		if (fila == -1) {
			btnEntrarEvento.setEnabled(false);
		} else {
			btnEntrarEvento.setEnabled(true);
		}
	}

	/**
	 * Poner nombre de cabecera del usuario que inicio sesión
	 */
	public void setNombreCabecera() {
		String nombreUsuario = modelo.getNombreUsuario();
		lblNombreU.setText(nombreUsuario);
	}

	/**
	 * Getter para el controlador
	 * 
	 * @return fila seleccionada que coincide con el codigo de evento + 1 en la BD
	 */
	public int getCodigoEvento() {
		return fila + 1;
	}
}
