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
            String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, formaPago) VALUES (?, ?, ?, ?)";

            try ( PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setDate(1, reserva.getFechaEntrada());
                statement.setDate(2, reserva.getFechaSalida());
                statement.setString(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());

                statement.executeUpdate();

                try ( ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertada la reserva de Id %s", reserva));
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

            String sql = "SELECT Id, Fecha_entrada, Fecha_salida, Valor, Forma_Pago FROM reservas";
            
            try ( PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.executeUpdate();

                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        Reserva fila = new Reserva(resultSet.getInt("Id"), resultSet.getDate("Fecha_entrada"),
                                resultSet.getDate("Fecha_salida"), resultSet.getString("Valor"),
                                resultSet.getString("Forma_Pago"));

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
            String sql = "DELETE FROM reservas WHERE ID = ?";

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

    public int modificar(Date fecha_entrada, Date fecha_salida, String valor, String forma_Pago, Integer id) {
        try {
            String sql = "UPDATE RESERVAS SET " + " FECHA_ENTRADA = ?, "
					+ " FECHA_SALIDA = ?," + " VALOR = ?," + " FORMA_PAGO = ?" + " WHERE ID = ?";
            

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, (java.sql.Date) fecha_entrada);
                statement.setDate(2, (java.sql.Date) fecha_salida);
                statement.setString(3, valor);
                statement.setString(4, forma_Pago);
                statement.setInt(5, id);
                statement.execute();

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

            String sql = "select ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO from RESERVAS WHERE Id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, busqueda);

                statement.executeUpdate();

                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        Reserva fila = new Reserva(resultSet.getInt("ID"), resultSet.getDate("FECHA_ENTRADA"),
                                resultSet.getDate("FECHA_SALIDA"), resultSet.getString("VALOR"),
                                resultSet.getString("FORMA_PAGO"));

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
