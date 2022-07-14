/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.nbajugones.frontend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.EquipoService;
import es.nbajugones.services.ExporterService;
import es.nbajugones.services.RenovacionesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Nacho
 */
@Controller
public class RenovacionesControlador {

	int LAST_RENOVACION = 2021;

	@Autowired
	RenovacionesService renovacionesService;

	@Autowired
	EquipoService equipoService;

	@RequestMapping("/renovacion.action")
	public void init(Model model, @RequestParam(value = "y", required = false) String id,
			@RequestParam(value = "tanda", required = false) String tanda) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		int y = LAST_RENOVACION;
		int t = 1;
		if (id != null) {
			y = Integer.parseInt(id);
		}
		if (tanda != null) {
			t = Integer.parseInt(tanda);
		}
		model.addAttribute("y", y);
		model.addAttribute("t", t);
		List<Integer> years = new ArrayList<Integer>();
		for (int i = ExporterService.LAST_DRAFT; i >= 2005; i--) {
			years.add(i);
		}
		model.addAttribute("years", years);
		model.addAttribute("renovaciones", renovacionesService.get(y, t));
	}

	@RequestMapping("/renovacionDetail.do")
	public String renovacionDetail(Model model, @RequestParam(value = "y", required = false) int y,
			@RequestParam(value = "idJugador", required = false) int jugador, HttpServletResponse response)
					throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		model.addAttribute("renovacion", renovacionesService.getRenovacion(y, jugador));
		response.setContentType("text/html;charset=UTF-8");
		return "renovacionDetail";
	}

	@RequestMapping("/renovacion.do")
	public String renovacion(Model model, @RequestParam(value = "year") int y, @RequestParam(value = "tanda") int tanda,
			@RequestParam(value = "idJugador") int jugador, @RequestParam(value = "idEquipoProp") String idEquipoProp,
			@RequestParam(value = "salario") double salario, @RequestParam(value = "years") int years,
			@RequestParam(value = "idEquipoGanador") String idEquipoGanador,
			@RequestParam(value = "renueva", required = false) String renueva, HttpServletResponse response)
					throws ServiceException {
		if (renueva == null || "".equals(renueva)) {
			renovacionesService.renovacionTemp(idEquipoGanador, salario, years, jugador, y);
		} else {
			if("FA".equals(renueva)) {			
				renovacionesService.noRenovar(jugador, idEquipoProp);
			}
			if("RENUEVA".equals(renueva)) {
				renovacionesService.renovar(jugador, idEquipoProp, idEquipoProp, salario, years, false);
			}
			if("FICHADO".equals(renueva)) {
				renovacionesService.renovar(jugador, idEquipoProp, idEquipoGanador, salario, years, true);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		return "redirect:/renovacion.action?y="+y+"&tanda="+tanda;
	}

}
