/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.nbajugones.frontend.controllers;

import es.nbajugones.dto.RondaDTO;
import java.util.ArrayList;
import java.util.List;

import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.DraftService;
import es.nbajugones.services.EquipoService;
import es.nbajugones.services.ExporterService;
import javax.servlet.http.HttpServletResponse;

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
public class DraftControlador {



    @Autowired
    DraftService draftService;

    @Autowired
    EquipoService equipoService;

    @RequestMapping("/draft.action")
    public void init(Model model, @RequestParam(value = "y", required = false) String id) throws ServiceException{
        model.addAttribute("equipos", equipoService.getEquipos());
        int y = ExporterService.LAST_DRAFT;
        if (id != null){
        	y = Integer.parseInt(id);
        }
        model.addAttribute("y", y);
        List<Integer> years = new ArrayList<Integer>();
        for (int i = ExporterService.LAST_DRAFT ; i >= 2005;i--){
        	years.add(i);
        }
        model.addAttribute("years", years);
        model.addAttribute("primeraRonda", draftService.getDraft(y,1));
        model.addAttribute("segundaRonda", draftService.getDraft(y,2));
    }

	@RequestMapping("/pick.action")
	public String getPick(Model model, @RequestParam(value = "y", required = false) Integer id
			, @RequestParam(value = "team", required = false) String team, @RequestParam(value = "round", required = false) Integer round
			, HttpServletResponse response) throws ServiceException{
		model.addAttribute("pick", draftService.getPick(id, round, team));
		response.setContentType("text/html;charset=UTF-8");
		return "draftPick";
	}

	@RequestMapping("/pick.do")
	public String doPick(Model model, @RequestParam(value = "y", required = false) Integer id
			, @RequestParam(value = "team", required = false) String team, @RequestParam(value = "round", required = false) Integer round
			, @RequestParam(value = "jugador", required = false) String jugador , @RequestParam(value = "posicion", required = false) String posicion
			, HttpServletResponse response) throws ServiceException{
		draftService.savePick(id, round, team, jugador, posicion);
		response.setContentType("text/html;charset=UTF-8");
		return "redirect:/draft.action?y="+id;
	}

}
