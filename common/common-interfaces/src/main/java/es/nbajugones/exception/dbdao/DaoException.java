package es.nbajugones.exception.dbdao;

import es.nbajugones.exception.base.BaseException;

@SuppressWarnings("serial")
public class DaoException extends BaseException {

	public DaoException(Throwable e) {
		super(e);
	}
	
	public DaoException(String msg){
		super(new Exception(msg));
	}

	

}
