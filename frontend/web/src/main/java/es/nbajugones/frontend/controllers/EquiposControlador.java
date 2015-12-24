/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.controllers;

import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.CopaDTO;
import es.nbajugones.dto.PlayoffDTO;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.EquipoService;
import es.nbajugones.services.HistoricoService;
import es.nbajugones.services.JugadorService;
import es.nbajugones.services.TradeService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
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
public class EquiposControlador {

	@Autowired
	EquipoService equipoService;

	@Autowired
	JugadorService jugadorService;

	@Autowired
	TradeService tradeService;

	@Autowired
	HistoricoService historicoService;

	@RequestMapping("/index.action")
	public void init(Model model) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		model.addAttribute("evaluacion", equipoService.evaluar());
	}

	@RequestMapping("/trade.action")
	public void trade(Model model) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
	}

	@RequestMapping("/rondas.action")
	public void rondas(Model model) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		model.addAttribute("rondas", equipoService.getRondas());
	}

	@RequestMapping("/evaluar.action")
	public void evaluar(Model model) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		model.addAttribute("evaluacion", equipoService.evaluar());
	}

	@RequestMapping("/copa.action")
	public void copa(Model model, @RequestParam(value = "y", required = false) String temp) throws ServiceException {
		String temporada = temp == null ? "2015-16" : temp;
		List<CopaDTO> ronda1 = equipoService.getRondaCopa(temporada, 1);
		model.addAttribute("ronda1", ronda1);
		model.addAttribute("ronda2", equipoService.getRondaCopa(temporada, 2));
		model.addAttribute("cuartos", equipoService.getRondaCopa(temporada, 3));
		model.addAttribute("semi", equipoService.getRondaCopa(temporada, 4));
		List<CopaDTO> rondaFinal = equipoService.getRondaCopa(temporada, 5);
		model.addAttribute("rondaFinal", rondaFinal);
		if (!rondaFinal.isEmpty()) {
			if (rondaFinal.get(0).isCasaGanador()) {
				model.addAttribute("ganador", rondaFinal.get(0).getEquipoCasa());
			} else {
				if (rondaFinal.get(0).isFueraGanador()) {
					model.addAttribute("ganador", rondaFinal.get(0).getEquipoFuera());
				}
			}
		}
		model.addAttribute("temporada", temporada);
		buildTemporadas(model, 16);
	}

	@RequestMapping("/historico.action")
	public void historico(Model model, @RequestParam(value = "y", required = false) String temp) throws ServiceException {
		String temporada = temp == null ? "2014-15" : temp;
		model.addAttribute("temporada", temporada);
		model.addAttribute("rs", historicoService.getResultados(temporada));
		model.addAttribute("ronda1", historicoService.getPlayOff(temporada, 1));
		model.addAttribute("ronda2", historicoService.getPlayOff(temporada, 2));
		model.addAttribute("semis", historicoService.getPlayOff(temporada, 3));
		List<PlayoffDTO> rondaFinal = historicoService.getPlayOff(temporada, 4);
		model.addAttribute("rondaFinal", rondaFinal);
		if (!rondaFinal.isEmpty()) {
			if (rondaFinal.get(0).isGanador1()) {
				model.addAttribute("ganador", rondaFinal.get(0).getEquipo1());
			} else {
				if (rondaFinal.get(0).isGanador2()) {
					model.addAttribute("ganador", rondaFinal.get(0).getEquipo2());
				}
			}
		}
		buildTemporadas(model, 15);
	}

	@RequestMapping("/equipos/roster.do")
	public String getEquipo(Model model, @RequestParam("id") String id,
			HttpServletResponse response) throws JSONException, IOException {
		try {
			EquipoDTO equipo = equipoService.getEquipo(id);
			model.addAttribute("equipo", equipo);
			model.addAttribute("evaluacion", equipoService.evaluar(id));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		return "equipos/roster";
	}

	@RequestMapping("/equipos/cortar.do")
	public String cortar(Model model, @RequestParam("id") int id, @RequestParam("equipo") String equipo,
			@RequestParam("factor") double factor, HttpServletResponse response) throws JSONException, IOException {
		try {
			jugadorService.cut(equipo, id, factor);
			EquipoDTO e = equipoService.getEquipo(equipo);
			model.addAttribute("equipo", e);
			model.addAttribute("evaluacion", equipoService.evaluar(equipo));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		return "equipos/roster";
	}

	@RequestMapping("/equipos/trade.do")
	public String trade(Model model, @RequestParam(value = "players1", required = false) List<String> players1,
			@RequestParam(value = "players2", required = false) List<String> players2, @RequestParam(value = "rondas1", required = false) List<String> rondas1,
			@RequestParam(value = "rondas2", required = false) List<String> rondas2, @RequestParam(value = "derechos1", required = false) List<String> derechos1,
			@RequestParam(value = "derechos2", required = false) List<String> derechos2, @RequestParam("equipo1") String equipo1, @RequestParam("equipo2") String equipo2,
			HttpServletResponse response) throws JSONException, IOException {
		try {
			tradeService.trade(players1, players2, rondas1, rondas2, derechos1, derechos2, equipo1, equipo2);
			response.setContentType("text/html;charset=UTF-8");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "redirect:/trade.action";
	}

	@RequestMapping("/equipos/activar.do")
	public String activar(Model model, @RequestParam("jugador") String jugador, @RequestParam("equipo") String equipo,
			HttpServletResponse response) throws JSONException, IOException {
		try {
			jugadorService.activar(jugador, equipo);
			EquipoDTO e = equipoService.getEquipo(equipo);
			model.addAttribute("equipo", e);
			model.addAttribute("evaluacion", equipoService.evaluar(equipo));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		return "equipos/roster";
	}

	@RequestMapping("/equipos/rosterMin.do")
	public String getEquipoMin(Model model, @RequestParam("id") String id, @RequestParam("posicion") String pos,
			HttpServletResponse response) throws JSONException, IOException, ServiceException {
		EquipoDTO equipo = equipoService.getEquipo(id);
		model.addAttribute("equipo", equipo);
		model.addAttribute("idEquipo", id);
		model.addAttribute("posicion", pos);
		response.setContentType("text/html;charset=UTF-8");
		return "equipos/rosterMin";
	}

	@RequestMapping("/ajax/logo.do")
	public void getLogo(Model model, @RequestParam("id") String id,
			HttpServletResponse response) throws JSONException, IOException, ServiceException {
		String logo = equipoService.getEquipo(id).getLogoDraft();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject eq = new JSONObject();
		eq.put("logo", logo);
		PrintWriter out = response.getWriter();
		out.println(eq.toString());
		out.close();
	}

	private void buildTemporadas(Model model, int limit){
		List<String> temporadas = new ArrayList<String>();
		for (int i=limit;i>3;i--){
			int next = i+1;
			String t = "20";
			t = t +(i<10?"0"+i:i)+"-"+(next<10?"0"+next:next);
			temporadas.add(t);
		}
		model.addAttribute("tempList", temporadas);
	}

}
