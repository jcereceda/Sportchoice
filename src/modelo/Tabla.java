package modelo;

/**
 * Clase Tabla serializable para poder exportar objetos a un archivo, en este caso se exportarán objetos 
 * de la clase con un atribto defaultTableModel. 
 */
import java.io.Serializable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Tabla implements Serializable {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabla;
	/**
	 * Constructor por defecto
	 * @param tablemodel a exportar
	 */
	public Tabla(DefaultTableModel tabla) {
		this.tabla = tabla;
	}
	/**
	 * Getter
	 * @return tablemodel exportado
	 */
	public DefaultTableModel getTabla() {
		return tabla;
	}
	
	/**
	 * Setter
	 * @param tablemodel a establecer
	 */
	public void setTabla(DefaultTableModel t) {
		this.tabla = t;
	}
}
