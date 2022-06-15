package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import javax.swing.SwingConstants;

import controlador.Controlador;
import modelo.*;
import modelo.Tabla;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame {

	private JPanel panel;
	private Controlador controlador;
	private Modelo modelo;
	private JScrollPane scrollPane;
	private JTable tableUsuarios;
	private JButton btnEliminar;
	private JButton btnCerrarSesion;
	private JLabel lblNewLabel_User;
	private JButton btnGuardarTabla;
	private JButton btnCargar;

	public Admin() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Administrador");
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(640, 532);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 0, 626, 95);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSportsChoice = new JLabel("SportsChoice");
		lblSportsChoice.setIcon(new ImageIcon(Admin.class.getResource("/imagenes/LogoS (1).png")));
		lblSportsChoice.setFont(new Font("MADE TOMMY", Font.PLAIN, 24));
		lblSportsChoice.setBounds(10, 11, 346, 73);
		panel.add(lblSportsChoice);

		lblNewLabel_User = new JLabel("New label");
		lblNewLabel_User.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_User.setIcon(new ImageIcon(Signup.class.getResource("/imagenes/logo.png")));
		lblNewLabel_User.setBounds(168, 106, 93, 58);
		getContentPane().add(lblNewLabel_User);

		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		btnAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAdmin.setBorder(null);
		btnAdmin.setBackground(SystemColor.controlHighlight);
		btnAdmin.setBounds(408, 28, 193, 36);
		panel.add(btnAdmin);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 175, 476, 243);
		getContentPane().add(scrollPane);

		tableUsuarios = new JTable();
		tableUsuarios.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(tableUsuarios);

		btnEliminar = new JButton("ELiminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.eliminarUsuario();
			}
		});
		btnEliminar.setFont(new Font("Bauhaus 93", Font.PLAIN, 17));
		btnEliminar.setBorder(new LineBorder(SystemColor.controlShadow, 2, true));
		btnEliminar.setBackground(new Color(173, 255, 47));
		btnEliminar.setBounds(348, 430, 161, 36);
		getContentPane().add(btnEliminar);

		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setBackground(new Color(173, 255, 47));
		btnCerrarSesion.setBorder(new LineBorder(SystemColor.controlShadow, 2, true));
		btnCerrarSesion.setFont(new Font("Bauhaus 93", Font.PLAIN, 17));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallas(3, 0);
			}
		});
		btnCerrarSesion.setBounds(125, 430, 161, 36);
		getContentPane().add(btnCerrarSesion);

		JLabel lblNewLabel = new JLabel("Administrador");
		lblNewLabel.setFont(new Font("Bauhaus 93", Font.PLAIN, 32));
		lblNewLabel.setBounds(232, 116, 234, 48);
		getContentPane().add(lblNewLabel);

		btnGuardarTabla = new JButton("");
		btnGuardarTabla.setBorder(null);
		btnGuardarTabla.setBackground(Color.WHITE);
		btnGuardarTabla.setIcon(new ImageIcon(Admin.class.getResource("/imagenes/download1.png")));
		btnGuardarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarTabla();
			}
		});
		btnGuardarTabla.setBounds(547, 287, 79, 58);
		getContentPane().add(btnGuardarTabla);

		btnCargar = new JButton("");
		btnCargar.setBorder(null);
		btnCargar.setBackground(Color.WHITE);
		btnCargar.setIcon(new ImageIcon(Admin.class.getResource("/imagenes/upload1.png")));
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
			}
		});
		btnCargar.setBounds(547, 232, 79, 48);
		getContentPane().add(btnCargar);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableUsuarios.setModel(modelo.getModeloTablaAdmin());
			}
		});
	}

	protected void cargarTabla() {
		File rutaProyecto = new File(System.getProperty("user.dir"));
		JFileChooser fc = new JFileChooser(rutaProyecto);
		int seleccion = fc.showOpenDialog(getContentPane());
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {
				File fichero = fc.getSelectedFile();
				FileInputStream fis = new FileInputStream(fichero);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Tabla tablaUsers = (Tabla) ois.readObject();
				modelo.setTablaAdmin((DefaultTableModel) tablaUsers.getTabla());
				tableUsuarios.setModel(modelo.getModeloTablaAdmin());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	protected void guardarTabla() {
		File rutaProyecto = new File(System.getProperty("user.dir"));
		JFileChooser fc = new JFileChooser(rutaProyecto);
		int seleccion = fc.showSaveDialog(getContentPane());
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = fc.getSelectedFile();
			try {
				FileOutputStream fos = new FileOutputStream(fichero);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				Tabla tablaUsuarios = new Tabla(modelo.getModeloTablaAdmin());
				oos.writeObject(tablaUsuarios);
				fos.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getNombre() {
		int fila = tableUsuarios.getSelectedRow();
		String nombre = (String) modelo.getModeloTablaAdmin().getValueAt(fila, 1);
		return nombre;
	}

	public void actualizar() {
		tableUsuarios.setModel(modelo.getModeloTablaAdmin());
	}
}
