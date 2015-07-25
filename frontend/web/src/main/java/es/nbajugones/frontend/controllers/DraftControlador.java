/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.nbajugones.frontend.controllers;

import java.util.ArrayList;
import java.util.List;

import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.DraftService;
import es.nbajugones.services.EquipoService;

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

	private static final int LAST_DRAFT = 2015;
	
    @Autowired
    DraftService draftService;
    
    @Autowired
    EquipoService equipoService; 
    
    @RequestMapping("/draft.action")
    public void init(Model model, @RequestParam(value = "y", required = false) String id) throws ServiceException{
        model.addAttribute("equipos", equipoService.getEquipos());
        int y = LAST_DRAFT;
        if (id != null){
        	y = Integer.parseInt(id);
        }
        model.addAttribute("y", y);
        List<Integer> years = new ArrayList<Integer>();
        for (int i = LAST_DRAFT ; i >= 2005;i--){
        	years.add(i);
        }
        model.addAttribute("years", years);
        model.addAttribute("primeraRonda", draftService.getDraft(y,1));
        model.addAttribute("segundaRonda", draftService.getDraft(y,2));
    }


}
