package es.nbajugones.frontend.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import es.nbajugones.dto.ExportDTO;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.EquipoService;
import es.nbajugones.services.JugadorService;

@Controller
public class ImportCSVControlador {

	@Autowired
	JugadorService jugadorService;

	@Autowired
	EquipoService equipoService;

	@RequestMapping("/import.action")
	public void importStats(Model model, HttpServletRequest request) throws ServiceException {
		model.addAttribute("equipos", equipoService.getEquipos());
		model.addAttribute("export", request.getSession().getAttribute("export"));
	}

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String upload(Model model, @RequestParam("csv") MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if (!file.isEmpty()) {
			try {
				byte[] fileContent = file.getBytes();
				File f = File.createTempFile("file", "csv");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(f));
				stream.write(fileContent);
				stream.close();
				ExportDTO result = jugadorService.updateScores(f);
				request.getSession().setAttribute("export", result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("equipos", equipoService.getEquipos());
		response.setContentType("text/html;charset=UTF-8");
		return "redirect:/import.action";
	}

}
