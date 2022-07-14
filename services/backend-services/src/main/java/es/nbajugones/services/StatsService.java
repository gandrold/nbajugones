/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.services;

import es.nbajugones.datagetter.BoxScoreGetter;
import es.nbajugones.datagetter.EventGetter;
import es.nbajugones.datagetter.beans.boxscore.BoxScore;
import es.nbajugones.datagetter.beans.boxscore.PlayerStats;
import es.nbajugones.datagetter.beans.events.Event;
import es.nbajugones.datagetter.beans.events.EventsList;
import es.nbajugones.dbdao.data.*;
import es.nbajugones.dto.BoxScoreDTO;
import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.JugadorDTO;
import es.nbajugones.dto.PlayerStatsDTO;
import es.nbajugones.dto.ReduccionDTO;
import es.nbajugones.dto.SeasonStatsDTO;
import es.nbajugones.dto.StatsDTO;
import es.nbajugones.dto.entities.*;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author iblanco
 */
@Transactional
public class StatsService {

	Logger logger = LoggerFactory.getLogger(StatsService.class.getName());

	@Autowired
	ScheduleDAO scheduleDAO;

	@Autowired
	StatsDAO statsDAO;

	@Autowired
	SeasonDAO seasonDAO;

	@Autowired
	DerechosDAO derechosDAO;

	@Autowired
	EquipoService equipoService;

	@Autowired
	JugadorService jugadorService;

	@Autowired
	PlayersDAO jugadoresDAO;

	@Autowired
	JugadoresDAO jugadorDAO;

	@Autowired
	RosterDAO rosterDAO;

	@Autowired
	ScheduleOrderDAO scheduleOrderDAO;

	@Autowired
	TokenDAO tokenDAO;

	public PlayerStatsDTO getPlayerStats(int id) throws ServiceException {
		try {
			JugadorDTO jugador = jugadorService.getByPlayerId(id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Season s = seasonDAO.getCurrentSeason(sdf.format(new Date()));
			PlayerStatsDTO stats = new PlayerStatsDTO();
			stats.setJugador(jugador);
			stats.setCareerStatsDTO(statsDAO.getAverageStats(id));
			List<Stats> seasonStats = statsDAO.getSeasonStats(s.getSeason(), id);
			List<StatsDTO> r = new ArrayList<StatsDTO>();
			for (Stats stat : seasonStats) {
				StatsDTO dto = new StatsDTO(stat, jugador);
				List<ScheduleOrder> scheduleOrders = scheduleOrderDAO.getGamesByScheduleId(stat.getStatsPK().getIdPartido());
				for (ScheduleOrder scheduleOrder : scheduleOrders) {
					if (scheduleOrder.getScheduleOrderPK().getTeam().equals(stat.getStatsPK().getIdEquipo())) {
						dto.setMatch(scheduleOrder.getScheduleOrderPK().getMatch());
						break;
					}
				}
				r.add(dto);
			}
			stats.setCurrentSeasonStats(r);
			return stats;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	public PlayerStatsDTO getPlayerStats(int id, String season) throws ServiceException {
		try {
			JugadorDTO jugador = jugadorService.getByPlayerId(id);
			Season s = seasonDAO.getSeason(season);
			PlayerStatsDTO stats = new PlayerStatsDTO();
			stats.setJugador(jugador);
			stats.setCareerStatsDTO(statsDAO.getAverageStats(id));
			List<Stats> seasonStats = statsDAO.getSeasonStats(s.getSeason(), id);
			List<StatsDTO> r = new ArrayList<StatsDTO>();
			for (Stats stat : seasonStats) {
				r.add(new StatsDTO(stat, jugador));
			}
			stats.setCurrentSeasonStats(r);
			return stats;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	public boolean isTodayInSeason() throws ServiceException {
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if (seasonDAO.getCurrentSeason(sdf.format(c.getTime())) == null) {
				c.add(Calendar.DATE, -2);
				return seasonDAO.getCurrentSeason(sdf.format(c.getTime())) != null;
			} else {
				return true;
			}
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	public PlayerStatsDTO getPlayerStats(JugadorDTO jugador) throws ServiceException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Season s = seasonDAO.getCurrentSeason(sdf.format(new Date()));
			PlayerStatsDTO stats = new PlayerStatsDTO();
			stats.setJugador(jugador);
			stats.setCareerStatsDTO(statsDAO.getAverageStats(jugador.getPlayerId()));
			List<Stats> seasonStats = statsDAO.getSeasonStats(s.getSeason(), jugador.getPlayerId());
			List<StatsDTO> r = new ArrayList<StatsDTO>();
			for (Stats stat : seasonStats) {
				r.add(new StatsDTO(stat, jugador));
			}
			stats.setCurrentSeasonStats(r);
			return stats;
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	public List<ReduccionDTO> getReduccionMinutos() throws ServiceException {
		List<SeasonStatsDTO> current = statsDAO.getSeasonStats("2017-18");
		List<SeasonStatsDTO> past = statsDAO.getSeasonStats("2016-17");
		List<ReduccionDTO> result = new ArrayList<ReduccionDTO>();
		for (SeasonStatsDTO statPast : past) {
			try {
				JugadorDTO j = jugadorService.getByPlayerId(statPast.getIdJugador());

				SeasonStatsDTO selectedStats = new SeasonStatsDTO();
				selectedStats.setMinutos(BigDecimal.ZERO);
				selectedStats.setJugados(BigInteger.ZERO);
				for (SeasonStatsDTO statsCurrent : current) {
					if (statPast.getIdJugador() == statsCurrent.getIdJugador()) {
						selectedStats = statsCurrent;
					}
				}
				ReduccionDTO dto = new ReduccionDTO();
				if (j != null && j.getIdJugador() != null && j.getSalario() != null && j.getSalario() > 1.99 && !j.getYears().equals("-") && Integer.parseInt(j.getYears()) > 1) {
					dto.setNombre(j.getNombre());
					dto.setContrato(j.getSalario() + "-" + j.getYears());
					dto.setEquipo(equipoService.getIdEquipo(j.getIdJugador()));
					double mA = selectedStats.getMinutos().doubleValue();
					double mP = statPast.getMinutos().doubleValue();
					dto.setMinutosActual(mA);
					dto.setMinutosPasada(mP);
					dto.setPartidosActual(selectedStats.getJugados().intValue());
					dto.setPartidosPasada(statPast.getJugados().intValue());
					if (dto.getResultado() > 12) {
						result.add(dto);
					}
				}
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
		return result;
	}

	public ReduccionDTO getReduccionMinutos(int idJugador) throws ServiceException {
		List<SeasonStatsDTO> current = statsDAO.getSeasonStats("2017-18");
		List<SeasonStatsDTO> past = statsDAO.getSeasonStats("2016-17");
		ReduccionDTO result = null;
		try {
			JugadorDTO j = jugadorService.getByPlayerId(idJugador);
			SeasonStatsDTO selectedStatsCurrent = new SeasonStatsDTO();
			selectedStatsCurrent.setMinutos(BigDecimal.ZERO);
			selectedStatsCurrent.setJugados(BigInteger.ZERO);
			SeasonStatsDTO selectedStatsPast = new SeasonStatsDTO();
			selectedStatsPast.setMinutos(BigDecimal.ZERO);
			selectedStatsPast.setJugados(BigInteger.ZERO);
			for (SeasonStatsDTO statsCurrent : current) {
				if (idJugador == statsCurrent.getIdJugador()) {
					selectedStatsCurrent = statsCurrent;
				}
			}
			for (SeasonStatsDTO statPast : past) {
				if (idJugador == statPast.getIdJugador()) {
					selectedStatsPast = statPast;
				}
			}
			ReduccionDTO dto = new ReduccionDTO();
			if (j != null) {
				dto.setNombre(j.getNombre());
				dto.setContrato(j.getSalario() + "-" + j.getYears());
				double mA = selectedStatsCurrent.getMinutos().doubleValue();
				double mP = selectedStatsPast.getMinutos().doubleValue();
				dto.setMinutosActual(mA);
				dto.setMinutosPasada(mP);
				dto.setPartidosActual(selectedStatsCurrent.getJugados().intValue());
				dto.setPartidosPasada(selectedStatsPast.getJugados().intValue());
				result = dto;
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public List<SeasonStatsDTO> getSeasonStats(String season) {
		return statsDAO.getSeasonStats(season);
	}

	public BoxScoreDTO getBoxScore(int id) throws ServiceException {
		Schedule sc = scheduleDAO.getById(id);
		EquipoDTO home = equipoService.getEquipo(sc.getHometeam());
		EquipoDTO away = equipoService.getEquipo(sc.getAwayteam());
		List<StatsDTO> homeStarters = new ArrayList<StatsDTO>();
		List<StatsDTO> awayStarters = new ArrayList<StatsDTO>();
		List<StatsDTO> homeBench = new ArrayList<StatsDTO>();
		List<StatsDTO> awayBench = new ArrayList<StatsDTO>();
		try {
			List<Stats> stats = statsDAO.getMatchStats(id);
			for (Stats s : stats) {
				StatsDTO dto = new StatsDTO(s, jugadorService.getByPlayerId(s.getStatsPK().getIdJugador()));
				if (s.getStatsPK().getIdEquipo().equals(sc.getHometeam())) {
					if (dto.isStarter()) {
						homeStarters.add(dto);
					} else {
						homeBench.add(dto);
					}
				} else {
					if (dto.isStarter()) {
						awayStarters.add(dto);
					} else {
						awayBench.add(dto);
					}
				}
			}
			return new BoxScoreDTO(home, away, sc.getHomescore(), sc.getAwayscore(), homeStarters, awayStarters, homeBench, awayBench);
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	public BoxScoreDTO getBoxScore(String season, String home, String away, int match) throws ServiceException {
		List<StatsDTO> homeStats = new ArrayList<StatsDTO>();
		List<StatsDTO> awayStats = new ArrayList<StatsDTO>();
		try {
			List<ScheduleOrder> order = scheduleOrderDAO.getGame(match, season);
			List<Roster> rosterHome = rosterDAO.getTeamGame(season, home, match);
			for (Roster r : rosterHome) {
				Jugadores j = jugadorDAO.getById(r.getRosterPK().getIdJugador());
				j.setPosicion(r.getPosicion());
				boolean found = false;
				for (ScheduleOrder o : order) {
					List<Stats> stats = statsDAO.getMatchStats(o.getScheduleId());
					for (Stats s : stats) {
						if (s.getPlayers().getId().equals(j.getPlayerId())) {
							//Tengo que revisar que el partido es el que realmente nos importa
							boolean valid = false;
							for (ScheduleOrder o2 : order) {
								if (o2.getScheduleOrderPK().getTeam().equals(s.getStatsPK().getIdEquipo())) {
									valid = o2.getScheduleId() == s.getStatsPK().getIdPartido();
									break;
								}
							}
							if (valid) {
								StatsDTO dto = new StatsDTO(s, j.getJugador(), j.getPosicion(), j.getIdJugador(), j.getPlayerId());
								dto.setPosicionHoops(r.getPosicion());
								homeStats.add(dto);
								found = true;
								break;
							}
						}
					}
					if (found) {
						break;
					}
				}
				if (!found) {
					StatsDTO dto = new StatsDTO(j.getJugador(), j.getPosicion(), j.getIdJugador(), j.getPlayerId()==null?0:j.getPlayerId());
					dto.setPosicionHoops(r.getPosicion());
					homeStats.add(dto);
				}
			}
			List<Roster> rosterAway = rosterDAO.getTeamGame(season, away, match);
			for (Roster r : rosterAway) {
				Jugadores j = jugadorDAO.getById(r.getRosterPK().getIdJugador());
				boolean found = false;
				for (ScheduleOrder o : order) {
					List<Stats> stats = statsDAO.getMatchStats(o.getScheduleId());
					for (Stats s : stats) {
						if (s.getPlayers().getId().equals(j.getPlayerId())) {
							//Tengo que revisar que el partido es el que realmente nos importa
							boolean valid = false;
							for (ScheduleOrder o2 : order) {
								if (o2.getScheduleOrderPK().getTeam().equals(s.getStatsPK().getIdEquipo())) {
									valid = o2.getScheduleId() == s.getStatsPK().getIdPartido();
									break;
								}
							}
							if (valid) {
								StatsDTO dto = new StatsDTO(s, j.getJugador(), j.getPosicion(), j.getIdJugador(), j.getPlayerId());
								dto.setPosicionHoops(r.getPosicion());
								awayStats.add(dto);
								found = true;
								break;
							}
						}
					}
					if (found) {
						break;
					}
				}
				if (!found) {
					StatsDTO dto = new StatsDTO(j.getJugador(), j.getPosicion(), j.getIdJugador(), j.getPlayerId());
					dto.setPosicionHoops(r.getPosicion());
					awayStats.add(dto);
				}
			}
			return new BoxScoreDTO(equipoService.getEquipoLite(home), equipoService.getEquipoLite(away), 0, 0, homeStats, awayStats, null, null);
		} catch (DaoException ex) {
			throw new ServiceException(ex);
		}
	}

	public void saveSchedule(String day, String season) throws ServiceException {
		EventGetter eventGetter = new EventGetter();
		try {
			EventsList events = eventGetter.getEvents(day, tokenDAO.getById("nba").getToken());
			scheduleDAO.saveDaySchedule(events, season);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public boolean checkPreviousDay() throws ServiceException {
		try {

			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			Date day = c.getTime();
			boolean result = getDay(day);
			checkPastDays(c);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	private void checkPastDays(Calendar c) throws ServiceException {
		try {
			for (int i = 0; i <= 5; i++) {
				c.add(Calendar.DATE, -1);
				Date day = c.getTime();
				getDay(day);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	private boolean getDay(Date day) throws DaoException, IOException, Exception {
		List<Schedule> schedule = scheduleDAO.getSchedule(day);
		boolean isProcessed = true;
		boolean result = false;
		for (Schedule s : schedule) {
			if (s.getHomescore() == null || s.getHomescore() == 0) {
				isProcessed = false;
				result = true;
				break;
			}
		}
		if (!isProcessed) {
			EventGetter eventGetter = new EventGetter();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			EventsList events = eventGetter.getEvents(sdf.format(day), tokenDAO.getById("nba").getToken());
			boolean allFinished = true;
			for (Event e : events.getEvents()) {
				if (!e.getStatus().equals("completed") && !e.getStatus().equals("postponed")) {
					allFinished = false;
					result = false;
					break;
				}
			}
			if (allFinished) {
				scheduleDAO.saveDaySchedule(events, seasonDAO.getCurrentSeason(sdf.format(day)).getSeason());
			}
		}
		return result;
	}

	public void getPastCalendar() throws ParseException, DaoException, ServiceException {
		Season s = seasonDAO.getFirstUnfinishedSeason();
		if (s != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String end = sdf.format(s.getLastprocessed() == null ? s.getStart() : s.getLastprocessed());
			logger.info("Getting data for season " + s.getSeason() + " from " + end);
			end = getCalendar(end, s.getSeason());
			s.setLastprocessed(sdf.parse(end));
			seasonDAO.saveOrUpdateEntity(s, s.getSeason());
		}
	}

	public String getCalendar(String startDate, String season) throws ParseException, ServiceException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String day = startDate;
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(day));
		for (int i = 1; i <= 5; i++) {
			logger.info("Getting " + day);
			saveSchedule(day, season == null ? seasonDAO.getSeason(day).getSeason() : season);
			c.add(Calendar.DATE, 1);
			day = sdf.format(c.getTime());
		}
		return day;
	}

	public boolean saveStats() throws ServiceException {
		BoxScoreGetter boxScoreGetter = new BoxScoreGetter();
		Tokens token = tokenDAO.getById("nba");
		try {
			if (!token.getToken().equals("")) {
				List<Schedule> schedules = scheduleDAO.getFirst6MatchesWithoutStats();
				for (Schedule s : schedules) {
					logger.info(s.getEventid());
					BoxScore boxScore = boxScoreGetter.getBoxScore(s.getEventid(), token.getToken());
					int starters = 0;
					for (PlayerStats stats : boxScore.getAwayStats()) {
						if (stats.isStarter()) {
							starters++;
						}
					}
					for (PlayerStats stats : boxScore.getHomeStats()) {
						if (stats.isStarter()) {
							starters++;
						}
					}
					if (starters == 10) {
						for (PlayerStats stats : boxScore.getAwayStats()) {
							statsDAO.saveStats(stats, s.getAwayteam(), s.getId());
							Players p = jugadoresDAO.getPlayer(stats.getFirstName(), stats.getLastName(), stats.getDisplayName(), stats.getPosition());
							PlayerStatsDTO dto = getPlayerStats(p.getId());
							Jugadores j = jugadorDAO.get(p.getId(), stats.getDisplayName());
							if (j == null && derechosDAO.getByName(p.getDisplayname()) == null) {
								String position = "";
								if (p.getCanPlayG() == 1) {
									position = position + "G";
								}
								if (p.getCanPlayF() == 1) {
									position = position + "F";
								}
								if (p.getCanPlayC() == 1) {
									position = position + "C";
								}
								try {
									j = jugadorService.crearJugador(stats.getDisplayName(), position);
									j.setPlayerId(p.getId());
								} catch (Exception e) {
									System.err.println(stats.getDisplayName() + " with id " + p.getId() + " is a troublemaker");
								}
							}
							if (j != null) {
								j.setPlayerId(p.getId());
								j.setPuntos(dto.getHoopsAvg());
								j.setPromedio(dto.getFppmAvg());
								if (dto.getLastSeasonAvg() != null) {
									j.setJugados(dto.getLastSeasonAvg().getJugados().intValue());
									j.setMinutos(dto.getLastSeasonAvg().getMinutos().doubleValue());
								}
								j.setActivo(1);
								jugadorDAO.saveOrUpdateEntity(j, j.getIdJugador());
							}

						}
						for (PlayerStats stats : boxScore.getHomeStats()) {
							statsDAO.saveStats(stats, s.getHometeam(), s.getId());
							Players p = jugadoresDAO.getPlayer(stats.getFirstName(), stats.getLastName(), stats.getDisplayName(), stats.getPosition());
							PlayerStatsDTO dto = getPlayerStats(p.getId());
							Jugadores j = jugadorDAO.get(p.getId(), p.getDisplayname());
							if (j == null && derechosDAO.getByName(p.getDisplayname()) == null) {
								String position = "";
								if (p.getCanPlayG() == 1) {
									position = position + "G";
								}
								if (p.getCanPlayF() == 1) {
									position = position + "F";
								}
								if (p.getCanPlayC() == 1) {
									position = position + "C";
								}
								j = jugadorService.crearJugador(p.getDisplayname(), position);
								j.setPlayerId(p.getId());
							}
							if (j != null) {
								j.setPlayerId(p.getId());
								j.setPuntos(dto.getHoopsAvg());
								j.setPromedio(dto.getFppmAvg());
								if (dto.getLastSeasonAvg() != null) {
									j.setJugados(dto.getLastSeasonAvg().getJugados().intValue());
									j.setMinutos(dto.getLastSeasonAvg().getMinutos().doubleValue());
								}
								j.setActivo(1);
								jugadorDAO.saveOrUpdateEntity(j, j.getIdJugador());
							}
						}
						s.setProcessed(1);
						scheduleDAO.saveOrUpdateEntity(s, s.getId());
					} else {
						logger.info("Match " + s.getEventid() + " is not complete");
					}
				}
				return !schedules.isEmpty();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		System.out.println("Save stats has finished");
		return true;
	}

	public void checkStats(Date day) throws DaoException, ServiceException {
		List<Schedule> schedule = scheduleDAO.getSchedule(day);
		for (Schedule s : schedule) {
			List<Stats> stats = statsDAO.getMatchStats(s.getId());
			for (Stats stat : stats) {
				Players p = jugadoresDAO.getById(stat.getStatsPK().getIdJugador());
				PlayerStatsDTO dto = getPlayerStats(p.getId());
				Jugadores j = jugadorDAO.getByName(dto.getJugador().getNombre());
				if (j == null && derechosDAO.getByName(p.getDisplayname()) == null) {
					String position = "";
					if (p.getCanPlayG() == 1) {
						position = position + "G";
					}
					if (p.getCanPlayF() == 1) {
						position = position + "F";
					}
					if (p.getCanPlayC() == 1) {
						position = position + "C";
					}
					j = jugadorService.crearJugador(p.getDisplayname(), position);
					j.setPlayerId(p.getId());
				}
				if (j != null) {
					logger.info(j.getJugador());
					j.setPlayerId(p.getId());
					j.setPuntos(dto.getHoopsAvg());
					j.setPromedio(dto.getFppmAvg());
					if (dto.getLastSeasonAvg() != null) {
						j.setJugados(dto.getLastSeasonAvg().getJugados().intValue());
						j.setMinutos(dto.getLastSeasonAvg().getMinutos().doubleValue());
					}
					j.setActivo(1);
					jugadorDAO.saveOrUpdateEntity(j, j.getIdJugador());
				}
			}

		}
	}

}
