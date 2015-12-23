/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.services;

import es.nbajugones.dbdao.data.ConferenciaDAO;
import es.nbajugones.dbdao.data.DivisionDAO;
import es.nbajugones.dbdao.data.EquipoDAO;
import es.nbajugones.dbdao.data.HistoricoDAO;
import es.nbajugones.dbdao.data.PlayoffDAO;
import es.nbajugones.dto.ConferenciaDTO;
import es.nbajugones.dto.DivisionDTO;
import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.HistoricoDTO;
import es.nbajugones.dto.PlayoffDTO;
import es.nbajugones.dto.entities.Conferencia;
import es.nbajugones.dto.entities.Divisione;
import es.nbajugones.dto.entities.Historico;
import es.nbajugones.dto.entities.Playoff;
import es.nbajugones.dto.entities.pk.ConferenciaPK;
import es.nbajugones.dto.entities.pk.DivisionePK;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author iblanco
 */
@Transactional
public class HistoricoService {

	@Autowired
	EquipoDAO equipoDAO;

	@Autowired
	HistoricoDAO historicoDAO;

	@Autowired
	PlayoffDAO playoffDAO;

	@Autowired
	ConferenciaDAO conferenciaDAO;

	@Autowired
	DivisionDAO divisionDAO;

	@Autowired
	EquipoService equipoService;

	public List<ConferenciaDTO> getResultados(String temporada) throws ServiceException{
		try {
			List<Historico> resultados = historicoDAO.getTemporada(temporada);
			List<ConferenciaDTO> result = new ArrayList<ConferenciaDTO>();
			for (Historico h:resultados){
				Conferencia c = conferenciaDAO.getById(new ConferenciaPK(h.getConferencia(), temporada));
				Divisione d = divisionDAO.getById(new DivisionePK(h.getDivision(), temporada));
				ConferenciaDTO conferencia = null;
				for (ConferenciaDTO conf: result){
					if (conf.getNombre().equals(c.getNombre())){
						conferencia = conf;
						break;
					}
				}
				if (conferencia == null){
					conferencia = new ConferenciaDTO(c.getNombre(), new ArrayList<DivisionDTO>());
					result.add(conferencia);
				}
				conferencia.addHistorico(new HistoricoDTO(h, equipoDAO.getById(h.getId().getIdEquipo()).getNombre()), d.getNombre());
			}
			for (ConferenciaDTO conf: result){
				conf.ordenar();
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<PlayoffDTO> getPlayOff(String temporada, int ronda) throws ServiceException{
		try {
			List<Playoff> p = playoffDAO.getTemporada(temporada, ronda);
			List<PlayoffDTO> result = new ArrayList<PlayoffDTO>();
			for (Playoff playoff:p){
				result.add(new PlayoffDTO(playoff, equipoService.getEquipo(playoff.getEquipo1()), equipoService.getEquipo(playoff.getEquipo2())));
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}


}
