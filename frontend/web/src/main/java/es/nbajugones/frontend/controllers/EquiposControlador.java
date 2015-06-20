/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.nbajugones.frontend.controllers;

import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.EquipoService;

import java.io.IOException;
import java.io.PrintWriter;

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

    @RequestMapping("/index.action")
    public void init(Model model) throws ServiceException{
        model.addAttribute("equipos", equipoService.getEquipos());
    }

    @RequestMapping("/trade.action")
    public void trade(Model model) throws ServiceException{
        model.addAttribute("equipos", equipoService.getEquipos());
    }

    @RequestMapping("/evaluar.action")
    public void evaluar(Model model) throws ServiceException{
        model.addAttribute("equipos", equipoService.getEquipos());
        model.addAttribute("evaluacion", equipoService.evaluar());
    }

    @RequestMapping("/equipos/roster.do")
    public String getEquipo(Model model, @RequestParam("id") String id,
            HttpServletResponse response) throws JSONException,IOException, ServiceException {
        EquipoDTO equipo=equipoService.getEquipo(id);
        //Collections.sort(equipo.getPlantilla());
        model.addAttribute("equipo", equipo);
        response.setContentType("text/html;charset=UTF-8");       
        return "equipos/roster";
    }

   @RequestMapping("/equipos/rosterMin.do")
    public String getEquipoMin(Model model, @RequestParam("id") String id,@RequestParam("posicion") String pos,
            HttpServletResponse response) throws JSONException,IOException, ServiceException {
        EquipoDTO equipo=equipoService.getEquipo(id);
        model.addAttribute("equipo", equipo);
        model.addAttribute("posicion", pos);
        response.setContentType("text/html;charset=UTF-8");
        return "equipos/rosterMin";
    }

    @RequestMapping("/ajax/logo.do")
    public void getLogo(Model model, @RequestParam("id") String id,
            HttpServletResponse response) throws JSONException,IOException, ServiceException {
        String logo=equipoService.getEquipo(id).getLogoDraft();
        response.setContentType("text/html;charset=UTF-8");
        JSONObject eq=new JSONObject();
        eq.put("logo",logo);
        PrintWriter out = response.getWriter();
		out.println(eq.toString());
		out.close();
    }
    

}
