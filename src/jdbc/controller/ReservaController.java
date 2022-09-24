package jdbc.controller;

import java.util.Date;
import java.util.List;
import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Reserva;

public class ReservaController {

    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConnectionFactory().RecuperarConexion());
    }

    public void guardar(Reserva reserva) {
        this.reservaDAO.guardar(reserva);
    }

    public List<Reserva> listarReservas() {
        return this.reservaDAO.listarReservas();
    }

    public List<Reserva> listarBusqueda(String busqueda){
        return this.reservaDAO.listarBusqueda(busqueda);
    }
    
    public int eliminar(Integer id){
        return this.reservaDAO.eliminar(id);
    }
    
    public int modificar(Date fechaEntrada, Date fechaSalida, String valor, String formaPago, Integer id){
        return this.reservaDAO.modificar(fechaEntrada, fechaSalida, valor, formaPago, id);
    }
}
