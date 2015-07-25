package es.nbajugones.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.OrdenDraftDAO;
import es.nbajugones.dbdao.data.RondasDraftDAO;
import es.nbajugones.dto.RondaDTO;
import es.nbajugones.dto.entities.RondasDraft;
import es.nbajugones.dto.entities.pk.OrdenDraftPK;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

public class DraftService {

	@Autowired
	RondasDraftDAO rondasDraftDAO;
	
	@Autowired
	OrdenDraftDAO ordenDraftDAO;
	
	@Transactional
	public List<RondaDTO> getDraft(int y, int round) throws ServiceException{
		try{
			List<RondasDraft> rondas = rondasDraftDAO.getYear(y, round);
			List<RondaDTO> result = new ArrayList<RondaDTO>();
			for (RondasDraft ronda:rondas){
				RondaDTO r = new RondaDTO(ronda);
				OrdenDraftPK pk = new OrdenDraftPK();
				pk.setAno(y);
				pk.setIdEquipo(ronda.getId().getIdEquipo());
				pk.setRonda(round);
				r.setOrden(ordenDraftDAO.getById(pk).getPosicion());
				result.add(r);
			}
			Collections.sort(result, new Comparator<RondaDTO>() {
				public int compare(RondaDTO o1, RondaDTO o2) {					
					return o1.getOrden()-o2.getOrden();
				}
			});
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
}
