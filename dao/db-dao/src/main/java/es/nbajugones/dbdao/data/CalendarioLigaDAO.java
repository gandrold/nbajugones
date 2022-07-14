package es.nbajugones.dbdao.data;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.CalendarioLiga;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iblanco on 25/10/2016.
 */
public class CalendarioLigaDAO extends GenericDAOImpl<CalendarioLiga> {

	public List<CalendarioLiga> getPastGames(String idEquipo) throws DaoException {
		SearchCriteria searchCriteria = new SearchCriteria();
		List<SearchCriteria.FilterCriterion> criterions = new ArrayList<SearchCriteria.FilterCriterion>();
		criterions.add(new SearchCriteria.FilterCriterion("id.idEquipoCasa", idEquipo, SearchCriteria.FilterCriterion.FilterType.EQUALS));
		criterions.add(new SearchCriteria.FilterCriterion("equipo2.idEquipo", idEquipo, SearchCriteria.FilterCriterion.FilterType.EQUALS));
		searchCriteria.addOrClause(criterions);
		searchCriteria.addFilter("fecha", new Date(), SearchCriteria.FilterCriterion.FilterType.LESS);
		searchCriteria.addFilter("id.temporada", "2017-18", SearchCriteria.FilterCriterion.FilterType.EQUALS);
		return getByCriteria(searchCriteria);
	}

	public List<CalendarioLiga> getFutureGames(String idEquipo) throws DaoException {
		SearchCriteria searchCriteria = new SearchCriteria();
		List<SearchCriteria.FilterCriterion> criterions = new ArrayList<SearchCriteria.FilterCriterion>();
		criterions.add(new SearchCriteria.FilterCriterion("id.idEquipoCasa", idEquipo, SearchCriteria.FilterCriterion.FilterType.EQUALS));
		criterions.add(new SearchCriteria.FilterCriterion("equipo2.idEquipo", idEquipo, SearchCriteria.FilterCriterion.FilterType.EQUALS));
		searchCriteria.addOrClause(criterions);
		searchCriteria.addFilter("fecha", new Date(), SearchCriteria.FilterCriterion.FilterType.MORE);
		searchCriteria.addFilter("id.temporada", "2017-18", SearchCriteria.FilterCriterion.FilterType.EQUALS);
		return getByCriteria(searchCriteria);
	}

}
