package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.model.Huesped;
import jdbc.model.Reserva;

public class HuespedDAO {

    private Connection connection;

    public HuespedDAO(Connection connection) {
        this.connection = connection;
    }

    public void guardarHuesped(Huesped huesped, int nroReserva) {

        try {
            String sql = "INSERT INTO huespedes (nombre, apellido, fechaNacimiento, nacionalidad, telefono, id_reservas) VALUES (?, ?, ?, ?, ?, ?)";

            try ( PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, (nroReserva));

                statement.executeUpdate();

                try ( ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        huesped.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertada la reserva de Id %s", huesped));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> listarHuespedes() {

        List<Huesped> resultado = new ArrayList<>();

        try {

            String sql = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, id_reservas FROM huespedes";

            try ( PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.executeUpdate();

                try ( ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        Huesped huesped = new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
                                resultSet.getString("apellido"), resultSet.getDate("fechaNacimiento"),
                                resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
                                resultSet.getInt("id_reservas"));
                        resultado.add(huesped);

                    }
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminarHuesped(Integer id) {
        try {
            String sql = "DELETE FROM huespedes WHERE ID = ?";

            try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificarHuesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, int id_reserva, Integer id) {
        try {
            String sql = "UPDATE huespedes SET " + " nombre = ?, " + " apellido = ?," + " fechaNacimiento = ?,"
							+ " nacionalidad = ?," + " telefono = ?," + " id_reserva = ?" + " WHERE id = ?";

            try ( PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, id_reserva);
				statement.setInt(7, id);
				statement.execute();


                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> listarBusquedaHuesped(String busqueda) {
        List<Huesped> resultado = new ArrayList<>();

        try {

            String sql = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE nombre = ? OR apellido = ? OR Busqueda = ?";

            try ( PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, busqueda);
                statement.setString(2, busqueda);
                statement.setString(3, busqueda);

                statement.executeUpdate();

                try ( ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        Huesped fila = new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
                                resultSet.getString("apellido"), resultSet.getDate("fechaNacimiento"),
                                resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
                                resultSet.getInt("id_reserva"));

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
