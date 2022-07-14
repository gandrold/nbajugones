package es.nbajugones.frontend.controllers;

import es.nbajugones.exception.service.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by iblanco on 19/01/2017.
 */
@Controller
public class Dashboard {

	@RequestMapping("/home.action")
	public void init(Model model, Principal principal) throws ServiceException {

	}

}
