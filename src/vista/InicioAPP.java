package vista;

/**
 * Clase pantalla de inicio, sale tras iniciar sesión, es la raiz del resto de las pantallas. Igual que en el resto de pantallas,
 * pero siendo la primera saldrá el nombre de usuario, el panel opciones para cerrar sesióno ir a la pantalla de gestión de perfil,
 * también el botón de ayuda.
 */
import java.awt.EventQueue;
import controlador.Controlador;
import modelo.Modelo;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class InicioAPP extends JFrame implements Pantallas{

	private JLabel lblInicio;
	private JLabel lblCr7;
	private JButton btnEventos;
	private JButton btnCrear;
	private JLabel lblFotoMaria;
	private JLabel lblSportsChoice;
	private JLabel lblNombre;
	private JButton btnEditarPerfil;
	private JButton btnAyuda;
	private JPanel cabecera;
	private Modelo modelo;
	private Controlador controlador;
	private JButton btnOpciones;
	private JButton btnEditarPerfil1;
	private JButton btnCerrarSesion;
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
	public InicioAPP() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});

		getContentPane().setBackground(Color.WHITE);
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(640, 532);
		setLocationRelativeTo(null);

		lblInicio = new JLabel("");
		lblInicio.setBackground(new Color(255, 255, 255));
		lblInicio.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/AHHHHHHHHHHHHHHHHHH.png")));
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblInicio.setEnabled(false);
		lblInicio.setBounds(280, 147, 321, 95);
		getContentPane().add(lblInicio);

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
				controlador.cambioPantallas(3, 5);
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
				controlador.cambioPantallas(3, 0);
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

		lblFotoMaria = new JLabel("");
		lblFotoMaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoMaria.setBounds(564, 21, 52, 52);
		cabecera.add(lblFotoMaria);
		lblFotoMaria.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/4ss.png")));

		lblSportsChoice = new JLabel("SportsChoice");
		lblSportsChoice.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblSportsChoice.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/LogoS (1).png")));
		lblSportsChoice.setBounds(10, 11, 227, 73);
		cabecera.add(lblSportsChoice);

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

		lblCr7 = new JLabel("New label");
		lblCr7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelOpciones.setVisible(false);
			}
		});
		lblCr7.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		lblCr7.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/CR7DONDEESTAAAA.png")));
		lblCr7.setBounds(-227, 113, 497, 414);
		getContentPane().add(lblCr7);

		btnEventos = new JButton("");
		btnEventos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(3, 7);
				panelOpciones.setVisible(false);
			}
		});
		btnEventos.setBackground(Color.WHITE);
		btnEventos.setBorder(null);
		btnEventos.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/EventosCompleto.png")));
		btnEventos.setBounds(289, 268, 155, 156);
		getContentPane().add(btnEventos);

		btnCrear = new JButton("");
		btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(3, 6);
				panelOpciones.setVisible(false);
			}
		});
		btnCrear.setBackground(Color.WHITE);
		btnCrear.setBorder(null);
		btnCrear.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/CrearCompleto.png")));
		btnCrear.setBounds(454, 267, 162, 158);
		getContentPane().add(btnCrear);

		btnAyuda = new JButton("");
		btnAyuda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(3, 2);
				panelOpciones.setVisible(false);
			}
		});
		btnAyuda.setBorder(null);
		btnAyuda.setBackground(new Color(255, 255, 255));
		btnAyuda.setBounds(591, 94, 35, 32);
		getContentPane().add(btnAyuda);
		btnAyuda.setActionCommand("");
		btnAyuda.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/ayuddddda.png")));
	}

	/**
	 * Poner nombre de cabecera del usuario que inicio sesión
	 */
	public void setNombreCabecera() {
		String nombreUsuario = modelo.getNombreUsuario();
		lblNombre.setText(nombreUsuario);
	}
}
