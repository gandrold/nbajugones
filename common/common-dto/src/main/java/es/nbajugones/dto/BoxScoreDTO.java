/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

import java.util.*;

/**
 * @author iblanco
 */
public class BoxScoreDTO {

	private int idPartido;

	private EquipoDTO home;

	private EquipoDTO away;

	private int homeScore;

	private int awayScore;

	private StatsDTO homeStats;

	private StatsDTO awayStats;

	private List<StatsDTO> homeStarters;

	private List<StatsDTO> awayStarters;

	private List<StatsDTO> homeBench;

	private List<StatsDTO> awayBench;

	private double homeHoopsScore;

	private double awayHoopsScore;

	private Map<String, Integer> minutesHome;

	private Map<String, Integer> minutesAway;

	public BoxScoreDTO(EquipoDTO home, EquipoDTO away, int homeScore, int awayScore, List<StatsDTO> homeStarters, List<StatsDTO> awayStarters, List<StatsDTO> homeBench, List<StatsDTO> awayBench) {
		this.home = home;
		this.away = away;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.homeStarters = homeStarters;
		this.awayStarters = awayStarters;
		this.homeBench = homeBench;
		this.awayBench = awayBench;
		minutesHome = new HashMap<String, Integer>();
		minutesHome.put("G", 96);
		minutesHome.put("F", 96);
		minutesHome.put("C", 48);
		minutesAway = new HashMap<String, Integer>();
		minutesAway.put("G", 96);
		minutesAway.put("F", 96);
		minutesAway.put("C", 48);
		List<StatsDTO> h = new ArrayList<StatsDTO>();
		if (homeBench!=null) {
			h.addAll(homeStarters);
			h.addAll(homeBench);
			homeStats = accumulateStats(h);
		}
		h = new ArrayList<StatsDTO>();
		if (awayBench!=null) {
			h.addAll(awayStarters);
			h.addAll(awayBench);
			awayStats = accumulateStats(h);
		}
	}

	public EquipoDTO getHome() {
		return home;
	}

	public void setHome(EquipoDTO home) {
		this.home = home;
	}

	public EquipoDTO getAway() {
		return away;
	}

	public void setAway(EquipoDTO away) {
		this.away = away;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public List<StatsDTO> getHomeStarters() {
		return homeStarters;
	}

	public void setHomeStarters(List<StatsDTO> homeStarters) {
		this.homeStarters = homeStarters;
	}

	public List<StatsDTO> getAwayStarters() {
		return awayStarters;
	}

	public void setAwayStarters(List<StatsDTO> awayStarters) {
		this.awayStarters = awayStarters;
	}

	public List<StatsDTO> getHomeBench() {
		return homeBench;
	}

	public void setHomeBench(List<StatsDTO> homeBench) {
		this.homeBench = homeBench;
	}

	public List<StatsDTO> getAwayBench() {
		return awayBench;
	}

	public void setAwayBench(List<StatsDTO> awayBench) {
		this.awayBench = awayBench;
	}

	public List<StatsDTO> getAllStats() {
		List<StatsDTO> all = new ArrayList<StatsDTO>();
		all.addAll(homeBench);
		all.addAll(awayBench);
		all.addAll(awayStarters);
		all.addAll(homeStarters);
		return all;
	}

	public double getHomeHoopsScore() {
		homeHoopsScore = StatsDTO.round(getScore(homeStarters, minutesHome) + 4, 2);
		return  homeHoopsScore;
	}

	public void setHomeHoopsScore(double homeHoopsScore) {
		this.homeHoopsScore = homeHoopsScore;
	}

	public void setAwayHoopsScore(double awayHoopsScore) {
		this.awayHoopsScore = awayHoopsScore;
	}

	public double getAwayHoopsScore() {
		awayHoopsScore= StatsDTO.round(getScore(awayStarters, minutesAway) - 4, 2);
		return awayHoopsScore;
	}

	private double getScore(List<StatsDTO> players, Map<String, Integer> minutes) {
		double score = 0;
		for (StatsDTO dto : players) {
			if (dto.getPosicionHoops().length() == 2) {
				String firstPosition = dto.getPosicionHoops().substring(0, 1);
				int minutesLeft = minutes.get(firstPosition);
				int playerMinutesLeft = dto.getMinutos();
				if (minutesLeft > 0) {
					int playerMinutes = dto.getMinutos();
					if (playerMinutes < minutesLeft) {
						score += dto.getPuntuacionHoops();
						minutesLeft = minutesLeft - playerMinutes;
						dto.setMinutosUsados(playerMinutes);
						dto.addMinutos(playerMinutes, firstPosition);
						playerMinutesLeft = 0;
						dto.setRealPuntuacionHoops(dto.getPuntuacionHoops());
					} else {
						playerMinutesLeft -= minutesLeft;
						dto.setMinutosUsados(minutesLeft);
						dto.addMinutos(minutesLeft, firstPosition);
						minutesLeft = 0;
						double scoreToAdd = StatsDTO.round((dto.getMinutosUsados() * dto.getPuntuacionHoops()) / dto.getMinutos(), 2);
						score += scoreToAdd;
						dto.setRealPuntuacionHoops(scoreToAdd);
					}
					minutes.put(firstPosition, minutesLeft);
				}
				if (playerMinutesLeft > 0) {
					String secondPosition = dto.getPosicionHoops().substring(1, 2);
					minutesLeft = minutes.get(secondPosition);
					if (minutesLeft > 0) {
						if (playerMinutesLeft < minutesLeft) {
							score += (dto.getPuntuacionHoops() - dto.getRealPuntuacionHoops());
							minutesLeft = minutesLeft - playerMinutesLeft;
							dto.addMinutos(playerMinutesLeft, secondPosition);
							dto.setMinutosUsados(dto.getMinutosUsados()+playerMinutesLeft);
							dto.setRealPuntuacionHoops(dto.getPuntuacionHoops());
						} else {
							dto.setMinutosUsados(minutesLeft);
							dto.addMinutos(minutesLeft, secondPosition);
							minutesLeft = 0;
							double scoreToAdd = StatsDTO.round((dto.getMinutosUsados() * dto.getPuntuacionHoops()) / dto.getMinutos(), 2);
							score += scoreToAdd;
							dto.setRealPuntuacionHoops(scoreToAdd);
						}
						minutes.put(secondPosition, minutesLeft);
					} else {
						dto.setMinutosUsados(0);
					}
				}

			} else {
				int minutesLeft = minutes.get(dto.getPosicionHoops());
				if (minutesLeft > 0) {
					int playerMinutes = dto.getMinutos();
					if (playerMinutes < minutesLeft) {
						score += dto.getPuntuacionHoops();
						minutesLeft = minutesLeft - playerMinutes;
						dto.addMinutos(playerMinutes, dto.getPosicionHoops());
						dto.setMinutosUsados(playerMinutes);
						dto.setRealPuntuacionHoops(dto.getPuntuacionHoops());
					} else {
						int usedMinutes = minutesLeft;
						dto.setMinutosUsados(usedMinutes);
						dto.addMinutos(usedMinutes, dto.getPosicionHoops());
						minutesLeft = 0;
						double scoreToAdd = StatsDTO.round((dto.getMinutosUsados() * dto.getPuntuacionHoops()) / dto.getMinutos(), 2);
						score += scoreToAdd;
						dto.setRealPuntuacionHoops(scoreToAdd);
					}
					minutes.put(dto.getPosicionHoops(), minutesLeft);
				} else {
					dto.setMinutosUsados(0);
				}
			}
		}
		return score;
	}

	public Map<String, Integer> getMinutesHome() {
		return minutesHome;
	}

	public void setMinutesHome(Map<String, Integer> minutesHome) {
		this.minutesHome = minutesHome;
	}

	public Map<String, Integer> getMinutesAway() {
		return minutesAway;
	}

	public void setMinutesAway(Map<String, Integer> minutesAway) {
		this.minutesAway = minutesAway;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	private StatsDTO accumulateStats(List<StatsDTO> stats) {
		StatsDTO result = new StatsDTO();
		for (StatsDTO stat:stats){
			result.setAsistencias(result.getAsistencias()+stat.getAsistencias());
			result.setFaltas(result.getFaltas()+stat.getFaltas());
			result.setFga(result.getFga()+stat.getFga());
			result.setFgm(result.getFgm()+stat.getFgm());
			result.setFta(result.getFta()+stat.getFta());
			result.setFtm(result.getFtm()+stat.getFtm());
			result.setPerdidas(result.getPerdidas()+stat.getPerdidas());
			result.setRebDef(result.getRebDef()+stat.getRebDef());
			result.setRebOf(result.getRebOf()+stat.getRebOf());
			result.setRobos(result.getRobos()+stat.getRobos());
			result.setTapones(result.getTapones()+stat.getTapones());
			result.setTpa(result.getTpa()+stat.getTpa());
			result.setTpm(result.getTpm()+stat.getTpm());
		}
		return result;
	}

	public StatsDTO getHomeStats() {
		return homeStats;
	}

	public void setHomeStats(StatsDTO homeStats) {
		this.homeStats = homeStats;
	}

	public StatsDTO getAwayStats() {
		return awayStats;
	}

	public void setAwayStats(StatsDTO awayStats) {
		this.awayStats = awayStats;
	}

	@Override
	public String toString() {
		return "BoxScoreDTO{" +
				"idPartido=" + idPartido +
				", home=" + home +
				", away=" + away +
				", homeScore=" + homeScore +
				", awayScore=" + awayScore +
				'}';
	}
}
