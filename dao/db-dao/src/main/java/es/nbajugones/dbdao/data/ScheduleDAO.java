/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dbdao.data;

import es.nbajugones.datagetter.beans.events.Event;
import es.nbajugones.datagetter.beans.events.EventsList;
import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.entities.Schedule;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author iblanco
 */
public class ScheduleDAO extends GenericDAOImpl<Schedule> {

	@Autowired
	EquipoDAO equipoDAO;


	public void saveDaySchedule(EventsList events, String season) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for (Event event : events.getEvents()) {
			Schedule schedule = new Schedule();
			String eventId = event.getEventId();
			SearchCriteria criteria = new SearchCriteria();
			criteria.addFilter("eventid", eventId, SearchCriteria.FilterCriterion.FilterType.EQUALS);
			List<Schedule> results = getByCriteria(criteria);
			if (results.isEmpty()) {
				schedule.setSeason(season);
				schedule.setStartdate(sdf.parse(eventId.substring(0, eventId.indexOf("-"))));
				schedule.setAwayteam(equipoDAO.getByNombre(event.getAwayTeam().getFullName()).getIdEquipo());
				schedule.setHometeam(equipoDAO.getByNombre(event.getHomeTeam().getFullName()).getIdEquipo());
				schedule.setEventid(event.getEventId());
				if (event.getSeasonType().equals("post")){
					schedule.setType("PLAYOFF");
				} else {
					schedule.setType("RS");
				}
				schedule.setProcessed(0);
			} else {
				schedule = results.get(0);
			}
			if (event.getHomeScore().intValue() > 0) {
				schedule.setHomescore(event.getHomeScore());
				schedule.setAwayscore(event.getAwayScore());
			}
			if (results.isEmpty()) {
				saveOrUpdateEntity(schedule, null);
			} else {
				saveOrUpdateEntity(schedule, schedule.getId());
			}
		}
	}

	public List<Schedule> getSeasonSchedule(String season) throws DaoException{
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("season", season, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		return getByCriteria(criteria);
	}

	public List<Schedule> getSchedule(Date day) throws DaoException{
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("startdate", day, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		return getByCriteria(criteria);
	}

	public List<Schedule> getTeamSchedule(String team, String season) throws DaoException{
		SearchCriteria searchCriteria = new SearchCriteria();
		List<SearchCriteria.FilterCriterion> orClauses = new ArrayList<SearchCriteria.FilterCriterion>();
		orClauses.add(new SearchCriteria.FilterCriterion("hometeam", team, SearchCriteria.FilterCriterion.FilterType.EQUALS));
		orClauses.add(new SearchCriteria.FilterCriterion("awayteam", team, SearchCriteria.FilterCriterion.FilterType.EQUALS));
		searchCriteria.addOrClause(orClauses);
		searchCriteria.addFilter("season", season, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		List<Schedule> results = getByCriteria(searchCriteria);
		Collections.sort(results, new Comparator<Schedule>() {
			public int compare(Schedule o1, Schedule o2) {
				return o1.getStartdate().compareTo(o2.getStartdate());
			}
		});
		return results;
	}

	public List<Schedule> getFirst6MatchesWithoutStats() throws DaoException {
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("processed", 0, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		criteria.addFilter("homescore", 0, SearchCriteria.FilterCriterion.FilterType.NOT_EQUALS);
		criteria.addOrder("startdate", SearchCriteria.OrderByCriterion.OrderDirection.DESC);
		criteria.setBatchSize(6);
		List<Schedule> results = getByCriteria(criteria);
		List<Schedule> r = new ArrayList<Schedule>();
		for (Schedule s : results) {
			r.add(s);
			if (r.size() == 6){
				break;
			}
		}
		return r;
	}

	private int getIndex(Schedule s, String team) throws DaoException {
		SearchCriteria searchCriteria = new SearchCriteria();
		List<SearchCriteria.FilterCriterion> orClauses = new ArrayList<SearchCriteria.FilterCriterion>();
		orClauses.add(new SearchCriteria.FilterCriterion("hometeam", team, SearchCriteria.FilterCriterion.FilterType.EQUALS));
		orClauses.add(new SearchCriteria.FilterCriterion("awayteam", team, SearchCriteria.FilterCriterion.FilterType.EQUALS));
		searchCriteria.addFilter("season", s.getSeason(), SearchCriteria.FilterCriterion.FilterType.EQUALS);
		searchCriteria.addOrder("startdate", SearchCriteria.OrderByCriterion.OrderDirection.ASC);
		searchCriteria.addOrClause(orClauses);
		List<Schedule> home = getByCriteria(searchCriteria);
		int index = 1;
		for (Schedule s1 : home) {
			if (s1.getId().intValue() == s.getId().intValue()) {
				break;
			} else {
				index++;
			}
		}
		return index;
	}

}
