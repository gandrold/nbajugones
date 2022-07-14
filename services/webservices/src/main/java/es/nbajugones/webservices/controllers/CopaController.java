package es.nbajugones.webservices.controllers;

import com.google.gson.Gson;
import es.nbajugones.dto.BoxScoreDTO;
import es.nbajugones.dto.CopaDTO;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by iblanco on 25/11/2016.
 */
@Controller
public class CopaController {


	@Autowired
	EquipoService equipoService;

	@RequestMapping("/copa/{temp}/{ronda}/{partido}")
	public @ResponseBody String	copaJson(Model model, @PathVariable String temp, @PathVariable int ronda
			, @PathVariable int partido) throws ServiceException {
		BoxScoreDTO dto = equipoService.getCopaBoxscore(temp, ronda, partido);
		dto.getAwayHoopsScore();
		dto.getHomeHoopsScore();
		Gson gson = new Gson();
		String response = gson.toJson(dto);
		return response;
	}

	@RequestMapping("/schedule/copa/{temp}")
	public@ResponseBody String	copaJson(Model model, @PathVariable String temp) throws ServiceException {
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
}
