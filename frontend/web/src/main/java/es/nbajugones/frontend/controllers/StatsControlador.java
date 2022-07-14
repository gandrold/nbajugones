/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.controllers;


import es.nbajugones.dto.PlayerStatsDTO;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.EquipoService;
import es.nbajugones.services.ExporterService;
import es.nbajugones.services.StatsService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nacho
 */
@Controller
public class StatsControlador {

	@Autowired
	StatsService statsService;

	@Autowired
	EquipoService equipoService;

	@RequestMapping("/stats.action")
	public void init(Model model, @RequestParam(value = "inicio", required = false) String inicio,
			@RequestParam(value = "temporada", required = false) String temporada) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		if (inicio != null) {
			model.addAttribute("inicio", inicio);
			model.addAttribute("temporada", temporada);
		}
	}

	@RequestMapping("/boxscore.do")
	public String boxscore(Model model, @RequestParam(value = "id", required = false) int matchId,
			HttpServletResponse response) throws ServiceException {
		model.addAttribute("boxscore", statsService.getBoxScore(matchId));
		response.setContentType("text/html;charset=UTF-8");
		return "boxscore";
	}

	@RequestMapping("/playerStats.action")
	public void playerStats(Model model, @RequestParam(value = "id", required = false) int id,
			@RequestParam(value = "temporada", required = false) String temporada) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		PlayerStatsDTO dto;
		if (temporada == null || temporada.equals(""+ExporterService.LAST_DRAFT)){
			dto = statsService.getPlayerStats(id);
			model.addAttribute("temporada", ExporterService.LAST_DRAFT);
		} else {
			dto = statsService.getPlayerStats(id, temporada+"1101");
			model.addAttribute("temporada", temporada);
		}
		List<Integer> years = new ArrayList<Integer>();
		for (int i = ExporterService.LAST_DRAFT; i >= 2012; i--) {
			years.add(i);
		}
		model.addAttribute("years", years);
		model.addAttribute("equipoJugones",equipoService.getIdEquipo(dto.getJugador().getIdJugador()));
		model.addAttribute("playerStats", dto);
		model.addAttribute("reduccion", statsService.getReduccionMinutos(id));
	}


	@RequestMapping("/seasonStats.action")
	public void seasonStats(Model model, @RequestParam(value = "season", required = false) String season) throws ServiceException {
		if (season == null){
			season = "2017-18";
		}
		model.addAttribute("equipos", equipoService.getEquipos());
		model.addAttribute("stats", statsService.getSeasonStats(season));
	}

	@RequestMapping("/reduccion.action")
	public void reduccion(Model model) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		model.addAttribute("stats", statsService.getReduccionMinutos());
	}



}
