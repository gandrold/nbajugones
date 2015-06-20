/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.controllers;

import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.EquipoService;
import es.nbajugones.services.JugadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ignacio Blanco
 */
@Controller
public class JugadoresControlador {

    @Autowired
    JugadorService jugadorService;

     @Autowired
    EquipoService equipoService;

    @RequestMapping("/jugadores/fa.action")
    public void fa(Model model) throws ServiceException {
        model.addAttribute("faG",jugadorService.getTop5FA("G"));
        model.addAttribute("faF",jugadorService.getTop5FA("F"));
        model.addAttribute("faC",jugadorService.getTop5FA("C"));
        model.addAttribute("fa",jugadorService.getAllFA());
        model.addAttribute("equipos", equipoService.getEquipos());
    }

     @RequestMapping("/jugadores/todos.action")
     public void todos(Model model) throws ServiceException {
        model.addAttribute("jugadores",jugadorService.getAll());
        model.addAttribute("equipos", equipoService.getEquipos());
     }
}
