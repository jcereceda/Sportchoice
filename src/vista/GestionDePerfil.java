package vista;

import java.awt.EventQueue;
import controlador.Controlador;
import modelo.Modelo;
//@author: Antonio Soler Maria

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
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class GestionDePerfil extends JFrame {

	private JTextField txtNombre;
	private JTextField txtUbicacion;
	private JButton btnAtras;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblNewLabel_5;
	private JPanel panel_1;
	private JLabel lblNewLabel_6_1_2;
	private JLabel lblNewLabel_6_1_2_1;
	private JLabel lblNewLabel_6_1_2_1_1;
	private JLabel lblNewLabel_6_1_2_1_1_1;
	private JSpinner spinnerSexo;
	private JSpinner spinnerFecha;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNombre;
	private JLabel lblEmail;
	private JButton btnNewButton;
	private JButton btnAyuda;
	private Modelo modelo;
	private Controlador controlador;
	private JLabel lblSportsChoice;
	private JLabel lblModif;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public GestionDePerfil() {

		getContentPane().setBackground(SystemColor.textHighlightText);
		setTitle("Gestion de perfil");
		setBounds(100, 100, 640, 532);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		setResizable(false);
		setSize(640, 532);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 626, 95);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblSportsChoice = new JLabel("SportsChoice");
		lblSportsChoice.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblSportsChoice.setIcon(new ImageIcon(InicioAPP.class.getResource("/imagenes/LogoS (1).png")));
		lblSportsChoice.setBounds(10, 11, 227, 73);
		panel.add(lblSportsChoice);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(SystemColor.controlHighlight, 4, true));
		panel_2.setBounds(77, 121, 467, 341);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(215, 26, 223, 286);
		panel_2.add(panel_1);
		panel_1.setBorder(new LineBorder(new Color(227, 227, 227), 4, true));
		panel_1.setBackground(SystemColor.textHighlightText);
		panel_1.setLayout(null);

		lblNewLabel_6_1_2 = new JLabel("Nombre Usuario:");
		lblNewLabel_6_1_2.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblNewLabel_6_1_2.setBounds(10, 26, 111, 35);
		panel_1.add(lblNewLabel_6_1_2);

		lblNewLabel_6_1_2_1 = new JLabel("Ubicacion:");
		lblNewLabel_6_1_2_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblNewLabel_6_1_2_1.setBounds(10, 90, 73, 25);
		panel_1.add(lblNewLabel_6_1_2_1);

		txtNombre = new JTextField();
		txtNombre.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtNombre.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		txtNombre.setBounds(10, 59, 146, 19);
		panel_1.add(txtNombre);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setColumns(10);

		txtUbicacion = new JTextField();
		txtUbicacion.setBorder(new LineBorder(SystemColor.scrollbar, 2, true));
		txtUbicacion.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		txtUbicacion.setBounds(10, 125, 146, 19);
		panel_1.add(txtUbicacion);
		txtUbicacion.setBackground(SystemColor.controlHighlight);
		txtUbicacion.setColumns(10);

		lblNewLabel_6_1_2_1_1 = new JLabel("Sexo:");
		lblNewLabel_6_1_2_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblNewLabel_6_1_2_1_1.setBounds(10, 154, 39, 19);
		panel_1.add(lblNewLabel_6_1_2_1_1);

		lblNewLabel_6_1_2_1_1_1 = new JLabel("Nacimiento:");
		lblNewLabel_6_1_2_1_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblNewLabel_6_1_2_1_1_1.setBounds(10, 212, 82, 19);
		panel_1.add(lblNewLabel_6_1_2_1_1_1);

		spinnerSexo = new JSpinner();
		spinnerSexo.setModel(new SpinnerListModel(new String[] { "Hombre", "Mujer", "Otro" }));
		spinnerSexo.setBounds(10, 183, 95, 20);
		panel_1.add(spinnerSexo);

		spinnerFecha = new JSpinner();
		spinnerFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DATE));
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd");
		spinnerFecha.setEditor(de);
		spinnerFecha.setBounds(10, 241, 95, 20);
		panel_1.add(spinnerFecha);

		lblNewLabel_6 = new JLabel("Editar Perfil:");
		lblNewLabel_6.setBounds(57, 32, 148, 35);
		panel_2.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Bauhaus 93", Font.PLAIN, 21));

		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(GestionDePerfil.class.getResource("/imagenes/User (1).png")));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(-16, 10, 76, 64);
		panel_2.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(GestionDePerfil.class.getResource("/imagenes/EditPerfil (1).png")));
		lblNewLabel_3.setBounds(67, 94, 94, 64);
		panel_2.add(lblNewLabel_3);

		lblNombre = new JLabel("");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNombre.setBounds(57, 156, 104, 35);
		panel_2.add(lblNombre);

		lblEmail = new JLabel("");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		lblEmail.setBounds(10, 176, 208, 35);
		panel_2.add(lblEmail);

		btnNewButton = new JButton("Guardar");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new LineBorder(SystemColor.controlShadow, 2, true));
		btnNewButton.setFont(new Font("Bauhaus 93", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.modificarPerfil();
			}
		});
		btnNewButton.setBackground(new Color(173, 255, 47));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(65, 266, 103, 26);
		panel_2.add(btnNewButton);
		
		lblModif = new JLabel("");
		lblModif.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblModif.setHorizontalAlignment(SwingConstants.CENTER);
		lblModif.setBounds(44, 302, 143, 29);
		panel_2.add(lblModif);

		btnAtras = new JButton("");
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(5, 3);
			}
		});
		btnAtras.setBorder(null);
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(0, 459, 35, 32);
		getContentPane().add(btnAtras);
		btnAtras.setActionCommand("");
		btnAtras.setIcon(new ImageIcon(GestionDePerfil.class.getResource("/imagenes/btnAtras.png")));

		btnAyuda = new JButton("");
		btnAyuda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(5, 2);
			}
		});
		btnAyuda.setBorder(null);
		btnAyuda.setBackground(new Color(255, 255, 255));
		btnAyuda.setBounds(591, 94, 35, 32);
		getContentPane().add(btnAyuda);
		btnAyuda.setActionCommand("");
		btnAyuda.setIcon(new ImageIcon(CrearEvento.class.getResource("/imagenes/ayuddddda.png")));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				lblModif.setText("");
			}
		});
	}

	public void actualizar() {
		String nombre = modelo.getNombreUsuario();
		lblNombre.setText(nombre);
		txtNombre.setText(nombre);
		String ubicacion = modelo.getUbicacion();
		txtUbicacion.setText(ubicacion);
		String email = modelo.getEmail();
		lblEmail.setText(email);
		String sexo = modelo.getSexo();
		spinnerSexo.setValue(sexo);
		Date fechaNac = modelo.getFechaNac();
		if (fechaNac == null) {
			spinnerFecha.setValue(new Date());
		} else {
			spinnerFecha.setValue(fechaNac);
		}
		
	}

	public String getNombreNuevo() {
		return txtNombre.getText();
	}

	public String getUbiNueva() {
		return txtUbicacion.getText();
	}

	public String getSexo() {
		return (String) spinnerSexo.getValue();
	}

	public String getFecha() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String fecha = dateFormat.format(spinnerFecha.getValue());  
		return fecha;
	}

	public void perfilActualizado() {
		lblModif.setText("Perfil Actualizado");
	}
	
	
}