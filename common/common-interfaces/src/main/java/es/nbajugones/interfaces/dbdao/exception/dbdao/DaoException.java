package es.nbajugones.interfaces.dbdao.exception.dbdao;

import es.nbajugones.interfaces.dbdao.exception.base.BaseException;

@SuppressWarnings("serial")
public class DaoException extends BaseException {

	public DaoException(Throwable e) {
		super(e);
	}
	
	public DaoException(String msg){
		super(new Exception(msg));
	}

	

}
