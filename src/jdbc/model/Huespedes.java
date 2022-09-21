
package jdbc.model;

import java.util.Date;
import java.util.List;


public class Huespedes {
    
    private Integer id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private List<Reserva> reservas;

    public Huespedes(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, List<Reserva> reservas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reservas = reservas;
    }
    
    
    
}
