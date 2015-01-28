package es.nbajugones.dbdao.data;

import java.util.ArrayList;
import java.util.List;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.dto.search.SearchCriteria.FilterCriterion;
import es.nbajugones.dto.search.SearchCriteria.FilterCriterion.FilterType;
import es.nbajugones.interfaces.dbdao.exception.dbdao.DaoException;

public class JugadoresDAO extends GenericDAOImpl<Jugadores> {

	public List<Jugadores> getPlantilla(int... jugadores) throws DaoException{
		SearchCriteria criteria = new SearchCriteria();
		List<FilterCriterion> orFilters = new ArrayList<SearchCriteria.FilterCriterion>();
		for (int i=0;i<jugadores.length;i++){
			FilterCriterion filter = new FilterCriterion("idJugador", jugadores[i], FilterType.EQUALS);
			orFilters.add(filter);
		}
		criteria.addOrClause(orFilters);
		return getByCriteria(criteria);
		
	}
	
}
