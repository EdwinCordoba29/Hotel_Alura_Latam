package controlador;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedController {
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
	}
	
	public void guardarHuesped(Huesped huesped) {
		huespedDAO.guardarHuesped(huesped);
	}
}
