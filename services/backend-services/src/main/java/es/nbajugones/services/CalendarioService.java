/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.services;

import es.nbajugones.dbdao.data.ScheduleDAO;
import es.nbajugones.dbdao.data.ScheduleOrderDAO;
import es.nbajugones.dbdao.data.SeasonDAO;
import es.nbajugones.dto.KeyValue;
import es.nbajugones.dto.MatchDTO;
import es.nbajugones.dto.ScheduleDTO;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Schedule;
import es.nbajugones.dto.entities.ScheduleOrder;
import es.nbajugones.dto.entities.Season;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author iblanco
 */
@Transactional
public class CalendarioService {

	@Autowired
	ScheduleDAO scheduleDAO;

	@Autowired
	ScheduleOrderDAO scheduleOrderDAO;

	@Autowired
	SeasonDAO seasonDAO;

	@Autowired
	EquipoService equipoService;

	@Autowired
	StatsService statsService;

	public List<KeyValue> getSeasons() throws ServiceException {
		try {
			List<Season> seasons = seasonDAO.getSeasons();
			List<KeyValue> result = new ArrayList<KeyValue>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (Season s : seasons) {
				result.add(new KeyValue(sdf.format(s.getEnd()), s.getSeason()));
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public void populateMatchOrders() throws ServiceException, DaoException {
		List<KeyValue> equipos = equipoService.getEquipos();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Season season = seasonDAO.getCurrentSeason(sdf.format(new Date()));
		for (KeyValue equipo : equipos) {
			List<Schedule> schedules = scheduleDAO.getTeamSchedule(equipo.getKey(), season.getSeason());
			int i = 1;
			for (Schedule schedule : schedules) {
				ScheduleOrder order = new ScheduleOrder(i, equipo.getKey(), season.getSeason());
				order.setScheduleId(schedule.getId());
				scheduleOrderDAO.saveOrUpdateEntity(order, null);
				i++;
			}
		}

	}

	public void recalculateMatchOrders(String s) throws ServiceException, DaoException {
		List<KeyValue> equipos = equipoService.getEquipos();
		Season season = seasonDAO.getById(s);
		for (KeyValue equipo : equipos) {
			List<Schedule> schedules = scheduleDAO.getTeamSchedule(equipo.getKey(), season.getSeason());
			int i = 1;
			for (Schedule schedule : schedules) {
				ScheduleOrder order = new ScheduleOrder(i, equipo.getKey(), season.getSeason());
				order.setScheduleId(schedule.getId());
				scheduleOrderDAO.saveOrUpdateEntity(order, null);
				i++;
			}
		}

	}

	public List<ScheduleDTO> getSchedule(String season) throws ServiceException {
		try {
			List<Schedule> sc = scheduleDAO.getSeasonSchedule(season);
			List<ScheduleDTO> results = new ArrayList<ScheduleDTO>();
			for (Schedule s : sc) {
				results.add(new ScheduleDTO(s, equipoService.getEquipo(s.getHometeam()), equipoService.getEquipo(s.getAwayteam())));
			}
			return results;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<MatchDTO> getWidgetDay(Date day) throws ServiceException {
		try {
			List<Schedule> sc = scheduleDAO.getSchedule(day);
			List<MatchDTO> results = new ArrayList<MatchDTO>();
			for (Schedule s : sc) {
				System.out.println(s);
				results.add(new MatchDTO(statsService.getBoxScore(s.getId()), new ScheduleDTO(s, equipoService.getEquipo(s.getHometeam()), equipoService.getEquipo(s.getAwayteam()))));
			}
			return results;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<ScheduleDTO> getDay(Date day) throws ServiceException {
		try {
			List<Schedule> sc = scheduleDAO.getSchedule(day);
			List<ScheduleDTO> results = new ArrayList<ScheduleDTO>();
			for (Schedule s : sc) {
				if (s != null && s.getAwayscore() != null && s.getAwayscore() > 0 && s.getHomescore() != null && s.getHomescore() > 0) {
					results.add(new ScheduleDTO(s, equipoService.getEquipo(s.getHometeam()), equipoService.getEquipo(s.getAwayteam())));
				}
			}
			return results;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Season getSeason(String season) {
		return seasonDAO.getById(season);
	}

}
