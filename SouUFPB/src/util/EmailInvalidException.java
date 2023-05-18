

package util;

public class EmailInvalidException extends UserException {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 4081633597571256549L;
	
	public EmailInvalidException(){
		super("Email Invalido");
	}
    public EmailInvalidException(String Message){
        super(Message);
    }
}

