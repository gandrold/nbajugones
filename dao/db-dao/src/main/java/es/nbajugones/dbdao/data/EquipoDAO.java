package es.nbajugones.dbdao.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Plantilla;
import es.nbajugones.interfaces.dbdao.exception.dbdao.DaoException;

public class EquipoDAO extends GenericDAOImpl<Equipo> {

	@Autowired
	JugadoresDAO jugadoresDAO;
	
	public List<Jugadores> getJugadores(String idEquipo) throws DaoException{
		Equipo equipo = getById(idEquipo);
		int[] jugadores = new int[equipo.getPlantilla().size()];
		int i=0;
		for (Plantilla p:equipo.getPlantilla()){
			jugadores[i] = p.getId().getIdJugador();
		}
		return jugadoresDAO.getPlantilla(jugadores);
	}
	
}
