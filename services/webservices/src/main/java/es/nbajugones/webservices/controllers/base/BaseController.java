package es.nbajugones.webservices.controllers.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import es.nbajugones.exception.base.BaseException;
import es.nbajugones.webservices.beans.BasicResponse;
import es.nbajugones.webservices.beans.ErrorMessage;


public class BaseController {

	
	public ResponseEntity<BasicResponse> handleException(Exception e){
		ErrorMessage error = new ErrorMessage(e.toString());
		BasicResponse errorResponse = new BasicResponse();
		errorResponse.setResult("KO");
		errorResponse.setResponse(error);
		return new ResponseEntity<BasicResponse>(errorResponse, HttpStatus.OK);
	}
	
	public ResponseEntity<BasicResponse> handleBaseException(BaseException e){
		e.printStackTrace();
		ErrorMessage error = new ErrorMessage(e.getFullMessage());
		BasicResponse errorResponse = new BasicResponse();
		errorResponse.setResult("KO");
		errorResponse.setResponse(error);
		return new ResponseEntity<BasicResponse>(errorResponse, HttpStatus.OK);
	}
	
	
}
