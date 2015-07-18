package es.nbajugones.dbdao.data;

import java.util.List;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.RondasDraft;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.dto.search.SearchCriteria.FilterCriterion.FilterType;
import es.nbajugones.exception.dbdao.DaoException;

public class RondasDraftDAO extends GenericDAOImpl<RondasDraft> {

	public void trade(String idEquipoOrigen, String idEquipoDestino, int ano, int ronda) throws DaoException{
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("idEquipoProp", idEquipoOrigen, FilterType.EQUALS);
		criteria.addFilter("id.ano", ano, FilterType.EQUALS);
		criteria.addFilter("id.ronda", ronda, FilterType.EQUALS);
		RondasDraft rondaDraft = getByCriteria(criteria).get(0);
		rondaDraft.setIdEquipoProp(idEquipoDestino);
		saveOrUpdateEntity(rondaDraft, rondaDraft.getId());
	}
	
	public List<RondasDraft> getAll() throws DaoException{
		SearchCriteria criteria = new SearchCriteria();
		List<RondasDraft> rondas = getByCriteria(criteria);
		return rondas;
	}
	
}
