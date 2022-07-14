/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.frontend.controllers;

import es.nbajugones.dto.DerechoDTO;
import es.nbajugones.dto.ScheduleDTO;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.CalendarioService;
import es.nbajugones.services.EquipoService;
import es.nbajugones.services.JugadorService;
import es.nbajugones.services.StatsService;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

	@Autowired
	StatsService statsService;

	@Autowired
	CalendarioService calendarioService;

    @RequestMapping("/jugadores/fa.action")
    public void fa(Model model) throws ServiceException {
        model.addAttribute("fa",jugadorService.getAllFA());
        model.addAttribute("equipos", equipoService.getEquipos());
    }

	@RequestMapping("/jugadores/faWidget.action")
    public void faWidget(Model model) throws ServiceException {
		model.addAttribute("faG",jugadorService.getTop5FA("G"));
        model.addAttribute("faF",jugadorService.getTop5FA("F"));
		model.addAttribute("faC",jugadorService.getTop5FA("C"));
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE,-1);
		model.addAttribute("schedule", calendarioService.getWidgetDay(c.getTime()));
	}


    @RequestMapping("/jugadores/new.action")
    public void nuevo(Model model) throws ServiceException{
    	model.addAttribute("equipos", equipoService.getEquipos());
    }

    @RequestMapping("/jugadores/derechos.action")
    public void derechos(Model model) throws ServiceException{
		List<DerechoDTO> derechos = jugadorService.getDerechos();
		Collections.sort(derechos, new Comparator<DerechoDTO>() {
			@Override
			public int compare(DerechoDTO o1, DerechoDTO o2) {
				return o2.getJugados() - o1.getJugados();
			}
		});
		model.addAttribute("equipos", equipoService.getEquipos());
    	model.addAttribute("derechos", derechos);
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
             HttpServletResponse response) throws JSONException,IOException, ServiceException{
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
    		 @RequestParam("salario") String salario,@RequestParam("duracion") String duracion,@RequestParam("fecha") String fecha,
             HttpServletResponse response) throws JSONException,IOException, ServiceException{
    	 jugadorService.ficharFA(equipo, jugador, salario, duracion, fecha);
     	 model.addAttribute("fa",jugadorService.getAllFA());
     	 model.addAttribute("equipos", equipoService.getEquipos());
     	 response.setContentType("text/html;charset=UTF-8");
         return "redirect:/jugadores/fa.action";
     }
}
