
package jdbc.controller;

import java.sql.Date;
import java.util.List;
import jdbc.dao.HuespedDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Huesped;


public class HuespedController {
    
    private HuespedDAO huespedDAO;

	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperarConexion());
	}

        
	public List<Huesped> listarHuespedes() {
		return huespedDAO.listarHuespedes();
	}
	
	public List<Huesped> listarBusquedaHuesped(String busqueda) {
		return huespedDAO.listarBusquedaHuesped(busqueda);
	}
	
	public void guardar(Huesped huesped, int reserva) {
		huespedDAO.guardarHuesped(huesped, reserva);
	}

	public int modificarHuesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, int id_reserva, Integer id) {
		return huespedDAO.modificarHuesped(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva, id);
	}

	public int eliminarHuesped(Integer id) {
		return huespedDAO.eliminarHuesped(id);
	}
}
