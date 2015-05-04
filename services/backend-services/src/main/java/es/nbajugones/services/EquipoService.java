package es.nbajugones.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.EquipoDAO;
import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dto.CalendarioDTO;
import es.nbajugones.dto.DerechoDTO;
import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.LogDTO;
import es.nbajugones.dto.entities.CalendarioLiga;
import es.nbajugones.dto.entities.Derecho;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Log;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

@Transactional
public class EquipoService {

	@Autowired
	EquipoDAO equipoDAO;

	@Autowired
	JugadoresDAO jugadoresDAO;

	public EquipoDTO getEquipo(String idEquipo) throws ServiceException {

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
	
	public List<CalendarioDTO> getCalendario(String idEquipo)
			throws ServiceException {
		return getCalendario(idEquipo, null);
	}

	public List<CalendarioDTO> getCalendario(String idEquipo, String temporada)
			throws ServiceException {

		Equipo equipo = equipoDAO.getById(idEquipo);
		List<CalendarioDTO> calendario = new ArrayList<CalendarioDTO>();
		for (CalendarioLiga c : equipo.getCalendarioLigas1()) {
			if (c.getId().getTemporada().equals(temporada)){
				calendario.add(new CalendarioDTO(c, false));
			} else {
				if (temporada == null){
					calendario.add(new CalendarioDTO(c, false));
				}
			}
		}
		for (CalendarioLiga c : equipo.getCalendarioLigas2()) {
			if (c.getId().getTemporada().equals(temporada)){
				calendario.add(new CalendarioDTO(c, true));
			} else {
				if (temporada == null){
					calendario.add(new CalendarioDTO(c, false));
				}
			}
		}
		Collections.sort(calendario);
		return calendario;
	}
	
	public List<DerechoDTO> getDerechos(String idEquipo)
			throws ServiceException {

		Equipo equipo = equipoDAO.getById(idEquipo);
		List<DerechoDTO> derechos = new ArrayList<DerechoDTO>();
		for (Derecho d:equipo.getDerechos()){
			derechos.add(new DerechoDTO(d));
		}
		
		return derechos;
	}
	
	public List<LogDTO> getLog(String idEquipo)
			throws ServiceException {

		Equipo equipo = equipoDAO.getById(idEquipo);
		List<LogDTO> log = new ArrayList<LogDTO>();
		for (Log l:equipo.getLog()){
			log.add(new LogDTO(l));
		}
		Collections.sort(log);
		return log;
	}
}
