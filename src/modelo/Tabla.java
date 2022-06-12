package modelo;

import java.io.Serializable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Tabla implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabla;
	
	public Tabla(DefaultTableModel tabla) {
		this.tabla = tabla;
	}

	public DefaultTableModel getTabla() {
		return tabla;
	}

	public void setTabla(DefaultTableModel t) {
		this.tabla = t;
	}
}
