
package jdbc.model;

import java.sql.Date;


public class Reserva {
    
    private Integer id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String valor;
    private String formaPago;

    public Reserva(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }
    
    
    
}
