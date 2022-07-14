/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dbdao.data;

import es.nbajugones.datagetter.beans.boxscore.PlayerStats;
import es.nbajugones.dbdao.base.GenericDAOImpl;
import es.nbajugones.dto.SeasonStatsDTO;
import es.nbajugones.dto.StatsDTO;
import es.nbajugones.dto.entities.Players;
import es.nbajugones.dto.entities.Stats;
import es.nbajugones.dto.search.SearchCriteria;
import es.nbajugones.exception.dbdao.DaoException;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author iblanco
 */
public class StatsDAO extends GenericDAOImpl<Stats> {

	@Autowired
	PlayersDAO jugadoresDAO;

	public List<Stats> getMatchStats(int matchId) throws DaoException {
		SearchCriteria criteria = new SearchCriteria();
		criteria.addFilter("id.idPartido", matchId, SearchCriteria.FilterCriterion.FilterType.EQUALS);
		return getByCriteria(criteria);
	}

	public List<Stats> getSeasonStats(String season, int playerId) throws DaoException {
		Query query = entityManager.createNamedQuery("Stats.findBySeason");
		query = query.setParameter("idJugador", playerId);
		query = query.setParameter("season", season);
		query = query.setParameter("type", "RS");
		return query.getResultList();
	}

	public void saveStats(PlayerStats playerStats, String team, int idPartido) throws DaoException {
		Players j = jugadoresDAO.getPlayer(playerStats.getFirstName(), playerStats.getLastName(), playerStats.getDisplayName(), playerStats.getPosition());
		if (j == null) {
			throw new DaoException(playerStats.getDisplayName() + " doesn't exist!!");
		} else {
			Stats stats = new Stats(j.getId(), team, idPartido);
			if (getById(stats.getStatsPK()) == null) {
				stats.setAsistencias(playerStats.getAssists());
				stats.setFaltas(playerStats.getPf());
				stats.setFga(playerStats.getFga());
				stats.setFgm(playerStats.getFgm());
				stats.setFta(playerStats.getFta());
				stats.setFtm(playerStats.getFtm());
				stats.setMinutos(playerStats.getMinutes());
				stats.setPerdidas(playerStats.getTurnovers());
				stats.setPuntos(playerStats.getPoints());
				stats.setRebDef(playerStats.getDefensiveRebounds());
				stats.setRebOf(playerStats.getOffensiveRebounds());
				stats.setTapones(playerStats.getBlocks());
				stats.setTpa(playerStats.getTpa());
				stats.setTpm(playerStats.getTpm());
				stats.setRobos(playerStats.getSteals());
				if (playerStats.isStarter()) {
					stats.setStarter(1);
				} else {
					stats.setStarter(0);
				}
				saveOrUpdateEntity(stats, null);
			}
		}
	}

	public List<SeasonStatsDTO> getAverageStats(int playerId) {
		String sqlQuery = "SELECT s.season, count(*) as jugados, sum(minutos)/count(*) as minutos, sum(puntos)/count(*) as puntos,"
				+ "sum(asistencias)/count(*) as asistencias, sum(perdidas)/count(*) as perdidas,"
				+ "sum(robos)/count(*) as robos, sum(tapones)/count(*) as tapones,"
				+ "sum(rebDef)/count(*) as rebDef, sum(rebOf)/count(*) as rebOf, "
				+ "sum(faltas)/count(*) as faltas, sum(fgm)/count(*) as fgm, sum(fga)/count(*) as fga, "
				+ "sum(3pm)/count(*) as tpm, sum(3pa)/count(*) as tpa, sum(ftm)/count(*) as ftm, "
				+ "sum(fta)/count(*) as fta, ((sum(fgm)-sum(3pm))/(sum(fga)-sum(3pa)))*100 as 'fgPerc',"
				+ "(sum(3pm)/sum(3pa))*100 as 'tpPerc', (sum(ftm)/sum(fta))*100 as 'ftPerc'"
				+ " FROM stats st "
				+ "join schedule s on (st.id_partido = s.id) "
				+ "where st.id_jugador = " + playerId
				+ " and s.type='RS'"
				+ " group by s.season order by s.season desc";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(SeasonStatsDTO.class));
		return (List<SeasonStatsDTO>) query.list();
	}

	public List<SeasonStatsDTO> getSeasonStats(String season) {
		String sqlQuery = "SELECT p.id as idJugador, p.displayname as nombre, count(*) as jugados, sum(minutos)/count(*) as minutos, sum(puntos)/count(*) as puntos,"
				+ "sum(asistencias)/count(*) as asistencias, sum(perdidas)/count(*) as perdidas,"
				+ "sum(robos)/count(*) as robos, sum(tapones)/count(*) as tapones,"
				+ "sum(rebDef)/count(*) as rebDef, sum(rebOf)/count(*) as rebOf, "
				+ "sum(faltas)/count(*) as faltas, sum(fgm)/count(*) as fgm, sum(fga)/count(*) as fga, "
				+ "sum(3pm)/count(*) as tpm, sum(3pa)/count(*) as tpa, sum(ftm)/count(*) as ftm, "
				+ "sum(fta)/count(*) as fta,((sum(fgm)-sum(3pm))/(sum(fga)-sum(3pa)))*100 as 'fgPerc',"
				+ "(sum(3pm)/sum(3pa))*100 as 'tpPerc', (sum(ftm)/sum(fta))*100 as 'ftPerc'"
				+ ", SUM(IF(homescore > awayscore, 1, 0)) AS win,"
				+ "SUM(IF(homescore < awayscore, 1, 0)) AS lose"
				+ " FROM stats st "
				+ "join schedule s on (st.id_partido = s.id) "
				+ " join players p on (st.ID_JUGADOR=p.id)"
				+ "where s.season = '" + season + "'"
				+ " and s.type='RS'"
				+ " group by p.id order by puntos desc";
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(SeasonStatsDTO.class));
		return (List<SeasonStatsDTO>) query.list();
	}

	public List<Stats> getMatchStats(int match, int playerId, String team) {
		String sqlQuery = "SELECT id_jugador, st.id_equipo, id_partido, minutos, puntos, asistencias, perdidas,"
				+ "robos, tapones,rebDef,rebOf, "
				+ "faltas, fgm, fga, 3pm as tpm, 3pa as tpa, ftm, "
				+ "fta FROM stats st join schedule s on (st.id_partido = s.id) "
				+ " join partidos_liga p on (st.id_partido=p.schedule_id) where p.id_equipo='"+team+"' AND p.jornada="+match+" AND st.id_jugador="+playerId;
		SQLQuery query = getSQLQuery(sqlQuery);
		query.setResultTransformer(Transformers.aliasToBean(Stats.class));
		return (List<Stats>) query.list();
	}



}
