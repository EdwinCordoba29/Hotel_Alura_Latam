package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private Connection con;

	public Connection recuperaConexion() {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC",
					"root",
					"1234");
			return con;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
