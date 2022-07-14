package es.nbajugones.dbdao.data;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.ScheduleOrder;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;

import java.util.List;

/**
 * Created by iblanco on 17/11/2016.
 */
public class ScheduleOrderDAO extends GenericDAOImpl<ScheduleOrder> {

	public List<ScheduleOrder> getGame(int game, String season) throws DaoException {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.addFilter("id.match", game, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		searchCriteria.addFilter("id.season", season, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		return getByCriteria(searchCriteria);
	}

	public List<ScheduleOrder> getGamesByScheduleId(int scheduleId) throws DaoException {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.addFilter("scheduleId", scheduleId, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		return getByCriteria(searchCriteria);
	}


}
