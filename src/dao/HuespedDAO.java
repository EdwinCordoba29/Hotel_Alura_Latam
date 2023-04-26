package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Huesped;

public class HuespedDAO {
	private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public void guardarHuesped(Huesped huesped) {
		String sql = "INSERT INTO huespedes(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, huesped.getNombre());
				ps.setString(2, huesped.getApellido());
				ps.setDate(3, huesped.getFechaNacimiento());
				ps.setString(4, huesped.getNacionalidad());
				ps.setString(5, huesped.getTelefono());
				ps.setInt(6, huesped.getIdReserva());
				ps.execute();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al guardar el huesped","Error",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}
}
