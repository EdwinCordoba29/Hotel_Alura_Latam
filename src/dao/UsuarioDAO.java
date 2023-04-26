package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import modelo.Usuario;

public class UsuarioDAO {

	private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public Usuario validarUsuario(Usuario usuario) {
		Usuario user = new Usuario();
		String sql = "SELECT id, usuario, clave, tipo_usuario FROM usuarios "
				+ "WHERE usuario = ? AND clave = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, usuario.getUsuario());
				ps.setString(2, usuario.getClave());
				final ResultSet rs = ps.executeQuery();
				try(rs){
					if(rs.next()) {
						user.setId(rs.getInt("id"));
						user.setUsuario(rs.getString("usuario"));
						user.setTipoUsuario(rs.getString("tipo_usuario"));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return user;
	}

	public void guardarUsuario(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios(usuario, clave, tipo_usuario)"
					+ "VALUES(?, ?, ?)";
			final PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			try(ps){
				ps.setString(1, usuario.getUsuario());
				ps.setString(2, usuario.getClave());
				ps.setString(3, usuario.getTipoUsuario());
				ps.execute();
				final ResultSet rs = ps.getGeneratedKeys();
				try(rs){
					if(rs.next()) {
						usuario.setId(rs.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error usuario no registrado", "Error",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e);
		}
	}
	
	public String buscarUsuario(String usuario) {
		String user = null;
		String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
		try {
			final PreparedStatement ps = con.prepareStatement(sql);
			try(ps){
				ps.setString(1, usuario);
				final ResultSet rs = ps.executeQuery();
				try(rs){
					if(rs.next()) {
						user = rs.getString(1);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return user;
	}
}
