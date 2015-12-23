package es.nbajugones.dbdao.data;

import java.util.List;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Playoff;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.dto.search.SearchCriteria.FilterCriterion.FilterType;
import es.nbajugones.exception.dbdao.DaoException;

public class PlayoffDAO extends GenericDAOImpl<Playoff> {


	public List<Playoff> getTemporada(String temporada, int ronda) throws DaoException{
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.addFilter("id.temporada", temporada, FilterType.EQUALS);
		searchCriteria.addFilter("id.ronda", ronda, FilterType.EQUALS);
		return getByCriteria(searchCriteria);
	}

}
