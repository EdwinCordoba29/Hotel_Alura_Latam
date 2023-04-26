package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Reserva;

public class ReservaDAO {
	private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}
	public void guardarReserva(Reserva reserva) {
		String sql = "INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_de_pago) "
				+ "VALUES(?,?,?,?)";
		try {
			final PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			try(ps){
				ps.setDate(1, reserva.getFechaEntrada());
				ps.setDate(2, reserva.getFechaSalida());
				ps.setFloat(3, reserva.getValor());
				ps.setString(4, reserva.getFormaDePago());
				ps.execute();
				final ResultSet rs = ps.getGeneratedKeys();
				try(rs){
					if(rs.next()) {
						reserva.setId(rs.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al guardar la reserva","Error",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}
}
