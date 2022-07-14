/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.controllers;


import es.nbajugones.dto.ScheduleDTO;
import es.nbajugones.exception.dbdao.DaoException;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.CalendarioService;
import es.nbajugones.services.EquipoService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.nbajugones.services.StatsService;
import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Nacho
 */
@Controller
public class CalendarioControlador {

	@Autowired
	CalendarioService calendarioService;

	@Autowired
	StatsService statsService;

	@Autowired
	EquipoService equipoService;

	@RequestMapping("/schedule.action")
	public void init(Model model, @RequestParam(value = "inicio", required = false) String inicio) throws ServiceException, ParseException {
		model.addAttribute("equipos", equipoService.getEquipos());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		String init = sdf.format(c.getTime());
		int days = 1;
		while (days < 4){
			c.add(Calendar.DATE,-1);
			List<ScheduleDTO> s = calendarioService.getDay(c.getTime());
			if (!s.isEmpty()){
				model.addAttribute("content"+days, s);
				model.addAttribute("day"+days, c.getTime());
				days++;
			}
		}
		model.addAttribute("inicio", init);
		model.addAttribute("seasons", calendarioService.getSeasons());
	}

	@RequestMapping(value = "/schedule.do", method = RequestMethod.POST)
	public String getSchedule(Model model, @RequestParam("inicio") long inicio) throws JSONException, IOException, ServiceException, ParseException, InterruptedException {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (inicio != 0) {
			c.setTimeInMillis(inicio);
		}
		String init = sdf.format(c.getTime());
		int days = 1;
		while (days < 4){
			List<ScheduleDTO> s = calendarioService.getDay(c.getTime());
			if (!s.isEmpty()){
				if (days == 1){
					init = sdf.format(c.getTime());
				}
				model.addAttribute("content"+days, s);
				model.addAttribute("day"+days, c.getTime());
				days++;
			}
			c.add(Calendar.DATE,-1);
		}
		model.addAttribute("inicio", init);
		model.addAttribute("seasons", calendarioService.getSeasons());
		return "schedule";
	}

	@RequestMapping(value = "/recalculate.do")
	public String recalculate(Model model) throws JSONException, IOException, ServiceException, ParseException, InterruptedException, DaoException {
		calendarioService.recalculateMatchOrders("2016-17");
		return "redirect:/schedule.action";
	}



}
