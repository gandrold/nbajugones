package es.nbajugones.dbdao.data;

import java.util.List;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Historico;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.dto.search.SearchCriteria.FilterCriterion.FilterType;
import es.nbajugones.exception.dbdao.DaoException;

public class HistoricoDAO extends GenericDAOImpl<Historico> {


	public List<Historico> getTemporada(String temporada) throws DaoException{
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.addFilter("id.temporada", temporada, FilterType.EQUALS);
		return getByCriteria(searchCriteria);
	}

}
