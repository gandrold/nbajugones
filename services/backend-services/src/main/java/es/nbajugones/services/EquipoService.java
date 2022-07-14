package es.nbajugones.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.nbajugones.dbdao.data.*;
import es.nbajugones.dto.*;
import es.nbajugones.dto.entities.pk.CopaPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dto.entities.CalendarioLiga;
import es.nbajugones.dto.entities.Copa;
import es.nbajugones.dto.entities.Derecho;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Log;
import es.nbajugones.dto.entities.Plantilla;
import es.nbajugones.dto.entities.RondasDraft;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

@Transactional
public class EquipoService {

	@Autowired
	EquipoDAO equipoDAO;

	@Autowired
	JugadoresDAO jugadoresDAO;

	@Autowired
	RondasDraftDAO rondasDraftDAO;

	@Autowired
	CopaDAO copaDAO;

	@Autowired
	StatsService statsService;

	@Autowired
	CalendarioLigaDAO calendarioLigaDAO;


	public EquipoDTO getEquipo(String idEquipo) throws ServiceException {

		try {
			Equipo e = equipoDAO.getById(idEquipo);
			List<Integer> jugadores = new ArrayList<Integer>();
			for (Plantilla p : e.getPlantilla()) {
				jugadores.add(p.getId().getIdJugador());
			}
			List<Jugadores> plantilla = jugadoresDAO.getPlantilla(jugadores);
			EquipoDTO equipo = new EquipoDTO(e, plantilla, calendarioLigaDAO.getPastGames(idEquipo), calendarioLigaDAO.getFutureGames(idEquipo));
			//for (JugadorDTO dto : equipo.getPlantilla()) {
			//	if (dto.getPlayerId() != null) {
			//		PlayerStatsDTO stats = statsService.getPlayerStats(dto.getPlayerId());
			//		if (stats != null) {
			//			dto.setPuntos(stats.getHoopsAvg());
			//			dto.setPromedio(stats.getFppmAvg());
			//			if (stats.getLastSeasonAvg() != null && stats.getLastSeasonAvg().getSeason().equals("2016-17")) {
			//				dto.setJugados(stats.getLastSeasonAvg().getJugados().intValue());
			//				dto.setMinutos(stats.getLastSeasonAvg().getMinutos().doubleValue());
			//			}
			//		}
			//	}
			//}
			Collections.sort(equipo.getPlantilla());
			return equipo;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	public EquipoDTO getEquipoLite(String idEquipo) throws ServiceException {
		Equipo e = equipoDAO.getById(idEquipo);
		EquipoDTO equipo = new EquipoDTO(e.getIdEquipo(), e.getLogoDraft(), e.getNombre());
		return equipo;
	}

	public String getIdEquipo(int idJugador) throws ServiceException {
		try {
			KeyValue k = equipoDAO.getEquipo(idJugador);
			if (k != null) {
				return k.getKey();
			} else {
				return "FA";
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	public List<KeyValue> getEquipos() throws ServiceException {
		try {
			return equipoDAO.getEquipos();
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
			if (c.getId().getTemporada().equals(temporada)) {
				calendario.add(new CalendarioDTO(c, false));
			} else {
				if (temporada == null) {
					calendario.add(new CalendarioDTO(c, false));
				}
			}
		}
		for (CalendarioLiga c : equipo.getCalendarioLigas2()) {
			if (c.getId().getTemporada().equals(temporada)) {
				calendario.add(new CalendarioDTO(c, true));
			} else {
				if (temporada == null) {
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
		for (Derecho d : equipo.getDerechos()) {
			derechos.add(new DerechoDTO(d));
		}

		return derechos;
	}

	public List<RondaDTO> getRondas() throws ServiceException {
		try {
			List<RondasDraft> rondas = rondasDraftDAO.getAll();
			List<RondaDTO> result = new ArrayList<RondaDTO>();
			for (RondasDraft ronda : rondas) {
				if (ronda.getJugador() == null && ronda.getIdJugador() == null) {
					result.add(new RondaDTO(ronda));
				}
			}
			Collections.sort(result, new Comparator<RondaDTO>() {

				public int compare(RondaDTO o1, RondaDTO o2) {
					if (o1.getEquipo().equals(o2.getEquipo())) {
						if (o1.getAno() == o2.getAno()) {
							return o1.getRonda() - o2.getRonda();
						} else {
							return o1.getAno() - o2.getAno();
						}
					} else {
						return o1.getEquipo().compareTo(o2.getEquipo());
					}
				}
			});
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<LogDTO> getLog(String idEquipo)
			throws ServiceException {

		Equipo equipo = equipoDAO.getById(idEquipo);
		List<LogDTO> log = new ArrayList<LogDTO>();
		for (Log l : equipo.getLog()) {
			log.add(new LogDTO(l));
		}
		Collections.sort(log);
		return log;
	}

	public List<EvaluacionDTO> evaluar() throws ServiceException {
		try {
			return equipoDAO.evaluar();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public EvaluacionDTO evaluar(String id) throws ServiceException {
		try {
			return equipoDAO.evaluar(id).get(0);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<CopaDTO> getCopa(String temporada) throws ServiceException {
		try {
			List<CopaDTO> result = new ArrayList<CopaDTO>();
			for (int ronda = 1; ronda <= 5; ronda++) {
				List<Copa> r = copaDAO.getRondaCopa(temporada, ronda);
				for (Copa c : r) {
					result.add(new CopaDTO(c, getEquipo(c.getIdEquipoCasa()), getEquipo(c.getIdEquipoFuera())));
				}
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<BoxScoreDTO> getBoxscoresRondaCopa(String temporada, int ronda)  throws ServiceException {
		try {
			List<Copa> partidos = copaDAO.getRondaCopa(temporada, ronda);
			List<BoxScoreDTO> results= new ArrayList<BoxScoreDTO>();
			for (Copa match:partidos){
				BoxScoreDTO boxScoreDTO = statsService.getBoxScore(temporada, match.getIdEquipoCasa(), match.getIdEquipoFuera(), 6 * ronda);
				boxScoreDTO.setIdPartido(match.getId().getPartido());
				results.add(boxScoreDTO);
			}
			return results;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public BoxScoreDTO getCopaBoxscore(String temporada, int ronda, int partido) throws ServiceException {
		Copa match = copaDAO.getById(new CopaPK(temporada, ronda, partido));
		BoxScoreDTO boxScoreDTO = statsService.getBoxScore(temporada, match.getIdEquipoCasa(), match.getIdEquipoFuera(), 6 * ronda);
		boxScoreDTO.setIdPartido(partido);
		return boxScoreDTO;
	}

	public List<CopaDTO> getRondaCopa(String temporada, int ronda) throws ServiceException {
		try {
			List<CopaDTO> result = new ArrayList<CopaDTO>();

			List<Copa> r = copaDAO.getRondaCopa(temporada, ronda);
			for (Copa c : r) {
				CopaDTO copa = new CopaDTO(c, getEquipoLite(c.getIdEquipoCasa()), getEquipoLite(c.getIdEquipoFuera()));
				copa.setJson("/jugonesws/copa/"+temporada+"/"+ronda+"/"+c.getId().getPartido());
				result.add(copa);
			}

			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
