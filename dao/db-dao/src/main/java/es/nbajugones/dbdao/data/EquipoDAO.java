package es.nbajugones.dbdao.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Plantilla;
import es.nbajugones.exception.dbdao.DaoException;

public class EquipoDAO extends GenericDAOImpl<Equipo> {

	@Autowired
	JugadoresDAO jugadoresDAO;
	
	public List<Jugadores> getJugadores(Equipo equipo) throws DaoException{
		List<Integer> jugadores = new ArrayList<Integer>();
		int i=0;
		for (Plantilla p:equipo.getPlantilla()){
			jugadores.add(p.getId().getIdJugador());
		}
		return jugadoresDAO.getPlantilla(jugadores);
	}
	
}
