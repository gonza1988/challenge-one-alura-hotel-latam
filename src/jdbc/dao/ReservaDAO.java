package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jdbc.model.Reserva;

public class ReservaDAO {

    private Connection connection;

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
}
