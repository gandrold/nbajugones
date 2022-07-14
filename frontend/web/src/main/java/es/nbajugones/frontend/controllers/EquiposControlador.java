/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.controllers;

import com.google.gson.Gson;
import es.nbajugones.dto.BoxScoreDTO;
import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.CopaDTO;
import es.nbajugones.dto.PlayoffDTO;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.*;

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
import org.springframework.web.bind.annotation.ResponseBody;

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

	@Autowired
	StatsService statsService;

	@RequestMapping("/index.action")
	public void init(Model model) throws ServiceException {
	}

	@RequestMapping("/dashboard.action")
	public void dashboard(Model model) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		model.addAttribute("evaluacion", equipoService.evaluar());
	}

	@RequestMapping("/roster.action")
	public void getEquipo(Model model, @RequestParam("id") String id) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		EquipoDTO equipo = equipoService.getEquipo(id);
		model.addAttribute("equipo", equipo);
		model.addAttribute("evaluacion", equipoService.evaluar(id));

	}

	@RequestMapping("/ordenar.do")
	public String ordenar(Model model, @RequestParam("id") String id) throws ServiceException {
		EquipoDTO equipo = equipoService.getEquipo(id);
		model.addAttribute("plantilla", equipo.getPlantilla());
		return "ordenarRoster";
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
		String temporada = temp == null ? "2017-18" : temp;
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
		buildTemporadas(model, 17);
	}

	@RequestMapping("/copaJson.do")
	public @ResponseBody
	String copaJson(Model model, @RequestParam(value = "y", required = false) String temp) throws ServiceException {
		String temporada = temp == null ? "2016-17" : temp;
		List<CopaDTO> ronda = equipoService.getRondaCopa(temporada, 1);
		ronda.addAll(equipoService.getRondaCopa(temporada, 2));
		ronda.addAll(equipoService.getRondaCopa(temporada, 3));
		ronda.addAll(equipoService.getRondaCopa(temporada, 4));
		ronda.addAll(equipoService.getRondaCopa(temporada, 5));
		Gson gson = new Gson();
		String response = gson.toJson(ronda);
		return response;
	}

	@RequestMapping("/copaBoxscore.do")
	public String copaBoscore(Model model, @RequestParam(value = "y", required = false) String temp, @RequestParam(value = "r", required = false) int ronda
			, @RequestParam(value = "p", required = false) int partido, HttpServletResponse response) throws ServiceException {
		model.addAttribute("boxscore", equipoService.getCopaBoxscore(temp, ronda, partido));
		response.setContentType("text/html;charset=UTF-8");
		return "copaBox";
	}

	@RequestMapping("/copaBoxscorejson.do")
	public @ResponseBody
	String	copaJson(Model model, @RequestParam(value = "y", required = false) String temp, @RequestParam(value = "r", required = false) int ronda
			, @RequestParam(value = "p", required = false) int partido) throws ServiceException {
		BoxScoreDTO dto = equipoService.getCopaBoxscore(temp, ronda, partido);
		dto.getAwayHoopsScore();
		dto.getHomeHoopsScore();
		Gson gson = new Gson();
		String response = gson.toJson(dto);
		return response;
	}

	@RequestMapping("/historico.action")
	public void historico(Model model, @RequestParam(value = "y", required = false) String temp) throws ServiceException {
		String temporada = temp == null ? "2017-18" : temp;
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
		buildTemporadas(model, 17);
	}

	@RequestMapping("/equipos/accion.do")
	public String accion(Model model, @RequestParam("idJugador") int id, @RequestParam("idEquipo") String equipo,
			@RequestParam("factor") double factor, @RequestParam(value = "lesionado", required=false) boolean lesionado, HttpServletResponse response) throws JSONException, IOException {
		try {
			EquipoDTO e = equipoService.getEquipo(equipo);
			if (factor >= 0) {
				jugadorService.cut(equipo, id, factor);
			} else {
				jugadorService.injured(equipo, id, lesionado);
			}
			model.addAttribute("equipo", e);
			model.addAttribute("evaluacion", equipoService.evaluar(equipo));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		return "redirect:/roster.action?id=" + equipo;
	}

	@RequestMapping("/equipos/trade.do")
	public String trade(Model model, @RequestParam(value = "fecha", required = false) String fecha, @RequestParam(value = "players1", required = false) List<String> players1,
			@RequestParam(value = "players2", required = false) List<String> players2, @RequestParam(value = "rondas1", required = false) List<String> rondas1,
			@RequestParam(value = "rondas2", required = false) List<String> rondas2, @RequestParam(value = "derechos1", required = false) List<String> derechos1,
			@RequestParam(value = "derechos2", required = false) List<String> derechos2, @RequestParam("equipo1") String equipo1, @RequestParam("equipo2") String equipo2,
			HttpServletResponse response) throws JSONException, IOException {
		try {
			tradeService.trade(players1, players2, rondas1, rondas2, derechos1, derechos2, equipo1, equipo2, fecha);
			response.setContentType("text/html;charset=UTF-8");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "redirect:/trade.action";
	}

	@RequestMapping("/equipos/activar.do")
	public String activar(Model model, @RequestParam("jugador") int jugador, @RequestParam("equipo") String equipo,
			HttpServletResponse response) throws JSONException, IOException {
		try {
			jugadorService.activar(jugador, equipo);
			EquipoDTO e = equipoService.getEquipo(equipo);
			model.addAttribute("equipos", equipoService.getEquipos());
			model.addAttribute("equipo", e);
			model.addAttribute("evaluacion", equipoService.evaluar(equipo));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		return "roster";
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
		String logo = "";
		if (!"FA".equals(id)) {
			logo = equipoService.getEquipo(id).getLogoDraft();
		}
		response.setContentType("application/json;charset=UTF-8");
		JSONObject eq = new JSONObject();
		eq.put("logo", logo);
		PrintWriter out = response.getWriter();
		out.println(eq.toString());
		out.close();
	}

	private void buildTemporadas(Model model, int limit) {
		List<String> temporadas = new ArrayList<String>();
		for (int i = limit; i > 3; i--) {
			int next = i + 1;
			String t = "20";
			t = t + (i < 10 ? "0" + i : i) + "-" + (next < 10 ? "0" + next : next);
			temporadas.add(t);
		}
		model.addAttribute("tempList", temporadas);
	}

}
