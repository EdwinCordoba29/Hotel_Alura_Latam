package controlador;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}
	
	public void guardarReserva(Reserva reserva) {
		reservaDAO.guardarReserva(reserva);
	}
	
	
}
