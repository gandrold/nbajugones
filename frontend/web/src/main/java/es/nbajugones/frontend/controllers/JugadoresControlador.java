/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.EquipoService;
import es.nbajugones.services.JugadorService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @RequestMapping("/jugadores/new.action")
    public void nuevo(Model model) throws ServiceException{
    	model.addAttribute("equipos", equipoService.getEquipos());
    }
    
    @RequestMapping("/jugadores/derechos.action")
    public void derechos(Model model) throws ServiceException{
    	model.addAttribute("equipos", equipoService.getEquipos());
    	model.addAttribute("derechos", jugadorService.getDerechos());
    }

     @RequestMapping("/jugadores/todos.action")
     public void todos(Model model) throws ServiceException {
        model.addAttribute("jugadores",jugadorService.getAll());
        model.addAttribute("equipos", equipoService.getEquipos());
     }
     
     @RequestMapping("/jugadores/new.do")
     public String nuevoJugador(Model model,@RequestParam("nombre") String nombre,
    		 @RequestParam("posicion") String posicion,
             HttpServletResponse response) throws ServiceException{
    	 jugadorService.crearJugador(nombre, posicion);
     	 model.addAttribute("fa",jugadorService.getAllFA());
     	 model.addAttribute("equipos", equipoService.getEquipos());
     	 response.setContentType("text/html;charset=UTF-8"); 
     	return "redirect:/jugadores/fa.action";
     }
     
     
     
     @RequestMapping("/jugadores/buscarFA.do")
     public String activar(Model model, @RequestParam("query") String query,
             HttpServletResponse response) throws JSONException,IOException{
     	 model.addAttribute("fa",jugadorService.getFA(query));
     	 response.setContentType("text/html;charset=UTF-8");       
         return "jugadores/fa";
     }
     
     @RequestMapping("/jugadores/seleccionar.do")
     public String seleccionar(Model model, @RequestParam("jugador") int jugador,
             HttpServletResponse response) throws JSONException,IOException, ServiceException{
     	 model.addAttribute("jugador",jugadorService.getJugador(jugador));
     	 model.addAttribute("equipos", equipoService.getEquipos());
     	 response.setContentType("text/html;charset=UTF-8");       
         return "jugadores/detalle";
     }
     
     @RequestMapping(value="/jugadores/ficharFA.do", method=RequestMethod.POST)
     public String ficharFA(Model model, @RequestParam("idJugador") int jugador, @RequestParam("equipo") String equipo,
    		 @RequestParam("salario") String salario,@RequestParam("duracion") String duracion,
             HttpServletResponse response) throws JSONException,IOException, ServiceException{
    	 jugadorService.ficharFA(equipo, jugador, salario, duracion);
     	 model.addAttribute("fa",jugadorService.getAllFA());
     	 model.addAttribute("equipos", equipoService.getEquipos());
     	 response.setContentType("text/html;charset=UTF-8");       
         return "redirect:/jugadores/fa.action";
     }
}
