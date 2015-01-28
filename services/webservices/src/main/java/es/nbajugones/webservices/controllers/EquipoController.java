package es.nbajugones.webservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.exception.base.BaseException;
import es.nbajugones.services.EquipoService;
import es.nbajugones.webservices.beans.BasicResponse;
import es.nbajugones.webservices.controllers.base.BaseController;

@Controller
@RequestMapping("/equipo")
public class EquipoController extends BaseController {

	@Autowired
	EquipoService equipoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/data/{identifier}")
    public ResponseEntity<BasicResponse> getUserInformation(@PathVariable String identifier) {
		BasicResponse response = new BasicResponse();
		try {
			EquipoDTO equipo = equipoService.getEquipo(identifier);
			response.setResult("OK");
			response.setResponse(equipo);
			return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
		} catch (BaseException e) {			
			return handleBaseException(e);
		} 
	}
}
