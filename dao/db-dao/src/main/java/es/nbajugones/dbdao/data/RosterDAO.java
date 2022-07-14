package es.nbajugones.dbdao.data;

import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Roster;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;
import org.hibernate.Session;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by iblanco on 18/11/2016.
 */
public class RosterDAO extends GenericDAOImpl<Roster> {

	public List<Roster> getTeamGame(String season, String team, int game) throws DaoException {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.addFilter("id.season", season, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		searchCriteria.addFilter("id.idEquipo", team, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		searchCriteria.addFilter("id.game", game, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		List<Roster> results = getByCriteria(searchCriteria);
		Collections.sort(results, new Comparator<Roster>() {
			public int compare(Roster o1, Roster o2) {
				return o1.getSpot().compareTo(o2.getSpot());
			}
		});
		return results;
	}

	public List<Roster> freeze(String season, String team, int game) throws DaoException {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.addFilter("id.season", season, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		searchCriteria.addFilter("id.game", game, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		searchCriteria.addFilter("id.idEquipo", team, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		List<Roster> results = getByCriteria(searchCriteria);
		for (Roster r:results){
			r.getRosterPK().setGame(r.getRosterPK().getGame()+1);
			saveOrUpdateEntity(r, null);
		}
		return results;
	}

	public List<Roster> saveRoster(List<Roster> roster) throws DaoException {
		for (Roster r:roster){
			saveOrUpdateEntity(r, r.getRosterPK());
		}
		return roster;
	}
}
