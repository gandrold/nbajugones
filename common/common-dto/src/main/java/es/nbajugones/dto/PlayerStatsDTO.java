/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iblanco
 */
public class PlayerStatsDTO {

	private JugadorDTO jugador;

	private List<StatsDTO> currentSeasonStats;

	private List<SeasonStatsDTO> careerStatsDTO;

	public JugadorDTO getJugador() {
		return jugador;
	}

	public void setJugador(JugadorDTO jugador) {
		this.jugador = jugador;
	}

	public List<StatsDTO> getCurrentSeasonStats() {
		return currentSeasonStats;
	}

	public void setCurrentSeasonStats(List<StatsDTO> currentSeasonStats) {
		this.currentSeasonStats = currentSeasonStats;
	}

	public List<SeasonStatsDTO> getCareerStatsDTO() {
		return careerStatsDTO;
	}

	public void setCareerStatsDTO(List<SeasonStatsDTO> careerStatsDTO) {
		this.careerStatsDTO = careerStatsDTO;
	}

	public SeasonStatsDTO getLastSeasonAvg() {
		if (careerStatsDTO.isEmpty()) {
			return null;
		} else {
			return careerStatsDTO.get(0);
		}
	}

	public double getHoopsAvg() {
		double total = 0;
		for (StatsDTO dto : currentSeasonStats) {
			total += dto.getPuntuacionHoops();
		}
		if (currentSeasonStats.isEmpty()) {
			return 0;
		} else {
			return total / currentSeasonStats.size();
		}
	}

	public double getFppmAvg() {
		if (getLastSeasonAvg() != null && getLastSeasonAvg().getMinutos().intValue() > 0) {
			return getHoopsAvg() / getLastSeasonAvg().getMinutos().intValue();
		} else {
			return 0;
		}
	}

	public double getHoopsLast5() {
		return getHoopsAvgLast(5)[0];
	}

	public double getFppmLast5() {
		double[] result = getHoopsAvgLast(5);
		return result[1];
	}

	public double getHoopsLast10() {
		return getHoopsAvgLast(10)[0];
	}

	public double getFppmLast10() {
		double[] result = getHoopsAvgLast(10);
		return result[1];
	}

	public double getHoopsLast20() {
		return getHoopsAvgLast(20)[0];
	}

	public double getFppmLast20() {
		double[] result = getHoopsAvgLast(20);
		return result[1];
	}

	private double[] getHoopsAvgLast(int limit) {
		double total = 0;
		int min = 0;
		int count = 0;
		for (StatsDTO dto : currentSeasonStats) {
			total += dto.getPuntuacionHoops();
			min += dto.getMinutos();
			count++;
			if (count == limit) {
				break;
			}
		}
		double[] result = new double[2];
		if (count > 0) {
			double minAvg = min / count;
			result[0] = total / count;
			result[1] = result[0] / minAvg;
		}
		return result;
	}

	public List<String> getCurrentSeasonTeams() {
		List<String> teams = new ArrayList<String>();
		for (StatsDTO dto : currentSeasonStats) {
			if (!teams.contains(dto.getMemberOf())) {
				teams.add(dto.getMemberOf());
			}
		}
		return teams;
	}

}
