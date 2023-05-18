

package util;


public class LoginInvalidException extends UserException {
   
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3797234359811694207L;

	public LoginInvalidException(){
    	super("Login invalido");
    }
   
    public LoginInvalidException(String Message){
        super(Message);
    }
}

