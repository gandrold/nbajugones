package es.nbajugones.dbdao.data;

import java.util.List;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.OrdenDraft;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.dto.search.SearchCriteria.FilterCriterion.FilterType;
import es.nbajugones.exception.dbdao.DaoException;

public class OrdenDraftDAO extends GenericDAOImpl<OrdenDraft>{

	public List<OrdenDraft> getYearDraft(int y, int round) throws DaoException{
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("id.ano", y, FilterType.EQUALS);
		criteria.addFilter("id.ronda", round, FilterType.EQUALS);
		return getByCriteria(criteria);
	}
}
