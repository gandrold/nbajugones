package es.nbajugones.exception.service;

import es.nbajugones.exception.base.BaseException;


@SuppressWarnings("serial")
public class ServiceException extends BaseException {

	public ServiceException(Throwable e) {
		super(e);
	}
	
	public ServiceException(String msg){
		super (new Exception(msg));
	}

}
