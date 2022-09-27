package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Reserva;

public class ReservaDAO {

    final private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void guardar(Reserva reserva) {

        try {
            String sql = "INSERT INTO reservas (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?, ?, ?, ?)";

            try ( PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setDate(1, reserva.getFechaEntrada());
                statement.setDate(2, reserva.getFechaSalida());
                statement.setString(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());

                statement.executeUpdate();

                try ( ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertada la reserva de id %s", reserva));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> listarReservas() {

        List<Reserva> resultado = new ArrayList<>();

        try {

            try ( PreparedStatement statement = connection
                    .prepareStatement("SELECT id, FechaEntrada, FechaSalida, valor, formaPago FROM reservas")) {

                statement.execute();

                try ( ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fechaEntrada"),
                                resultSet.getDate("fechaSalida"), resultSet.getString("valor"),
                                resultSet.getString("formaPago"));

                        resultado.add(fila);

                    }
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try {
            String sql = "DELETE FROM reservas WHERE id = ?";

            try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO PUEDE ELIMINAR UNA RESERVA QUE TENGA ASOCIADOS HUÉSPEDES");
            return 0;
        }
    }

    public int modificar(Date fechEntrada, Date fechaSalida, String valor, String formaPago, Integer id) {
        try {
            String sql = "UPDATE reservas SET " + " fechaEntrada = ?, "
                    + " fechaSalida = ?," + " valor = ?," + " formaPago = ?" + " WHERE id = ?";

            try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, (java.sql.Date) fechEntrada);
                statement.setDate(2, (java.sql.Date) fechaSalida);
                statement.setString(3, valor);
                statement.setString(4, formaPago);
                statement.setInt(5, id);
                
                statement.executeUpdate();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> listarBusqueda(String busqueda) {
        List<Reserva> resultado = new ArrayList<>();

        try {

            String sql = "select id, fechaEntrada, fechaSalida, valor, formaPago from reservas where id = ? ";

            try ( PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, busqueda);

                statement.execute();

                try ( ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fechaEntrada"),
                                resultSet.getDate("fechaSalida"), resultSet.getString("valor"),
                                resultSet.getString("formaPago"));

                        resultado.add(fila);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
