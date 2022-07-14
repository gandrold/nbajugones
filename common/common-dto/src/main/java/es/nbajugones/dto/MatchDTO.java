/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

/**
 *
 * @author iblanco
 */
public class MatchDTO {

	private BoxScoreDTO boxscore;

	private ScheduleDTO match;

	public MatchDTO(BoxScoreDTO boxscore, ScheduleDTO match) {
		this.boxscore = boxscore;
		this.match = match;
	}

	public BoxScoreDTO getBoxscore() {
		return boxscore;
	}

	public void setBoxscore(BoxScoreDTO boxscore) {
		this.boxscore = boxscore;
	}

	public ScheduleDTO getMatch() {
		return match;
	}

	public void setMatch(ScheduleDTO match) {
		this.match = match;
	}

	public StatsDTO getBestPlayer(){
		StatsDTO best = null;
		for (StatsDTO p:boxscore.getAllStats()){
			if (best == null){
				best = p;
			} else {
				if (best.getPuntuacionHoops()<p.getPuntuacionHoops()){
					best = p;
				}
			}
		}
		return best;
	}


}
