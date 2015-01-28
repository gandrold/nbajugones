package es.nbajugones.services;

import org.springframework.beans.factory.annotation.Autowired;

import es.nbajugones.dbdao.data.EquipoDAO;
import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

public class EquipoService {

	@Autowired
	EquipoDAO equipoDAO;
	
	@Autowired
	JugadoresDAO jugadoresDAO;
	
	
	public EquipoDTO getEquipo(String idEquipo) throws ServiceException{
		EquipoDTO equipo = new EquipoDTO();
		equipo.setEquipo(equipoDAO.getById(idEquipo));
		try {
			equipo.setPlantilla(equipoDAO.getJugadores(equipo.getEquipo()));
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return equipo;
	}
}
