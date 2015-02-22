package es.nbajugones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.EquipoDAO;
import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

@Transactional
public class EquipoService {

	@Autowired
	EquipoDAO equipoDAO;
	
	@Autowired
	JugadoresDAO jugadoresDAO;
	
	
	public EquipoDTO getEquipo(String idEquipo) throws ServiceException{
		
		try {
			Equipo e = equipoDAO.getById(idEquipo);
			List<Jugadores> plantilla = equipoDAO.getJugadores(e);
			System.out.println(plantilla.size());
			EquipoDTO equipo = new EquipoDTO(e, plantilla);
			return equipo;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}
}
