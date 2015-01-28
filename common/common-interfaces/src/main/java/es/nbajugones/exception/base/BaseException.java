package es.nbajugones.exception.base;

@SuppressWarnings("serial")
public class BaseException extends Exception {

	private Throwable parentException;
	
	public BaseException(Throwable e){
		this.setParentException(e);
	}

	public Throwable getParentException() {
		return parentException;
	}

	public void setParentException(Throwable parentException) {
		this.parentException = parentException;
	}

	public String getFullMessage(){
		Throwable next = parentException;
		StringBuffer message = new StringBuffer();
		while (next instanceof BaseException){
			if (next.getMessage()!=null){
				message.append(next.getMessage()+"...");
			}
			next = ((BaseException)next).parentException;
		}
		message.append(next.getMessage()+";");
		return message.toString();
	}
	
}
