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
public class ExportControlador {

    @Autowired
    EquipoService equipoService; 

    @RequestMapping("/exportar.action")
    public void init(Model model) throws ServiceException{
        model.addAttribute("equipos", equipoService.getEquipos());
    }

    @RequestMapping("/export.do")
    public String getEquipo(Model model, @RequestParam("id") String id,
            HttpServletResponse response) throws JSONException,IOException, ServiceException {
        EquipoDTO equipo=equipoService.getEquipo(id);
        //Collections.sort(equipo.getPlantilla());
        model.addAttribute("equipo", equipo);
        response.setContentType("text/html;charset=UTF-8");       
        return "equipos/roster";
    }

    

}
