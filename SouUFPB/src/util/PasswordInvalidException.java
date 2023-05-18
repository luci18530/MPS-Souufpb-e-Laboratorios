

package util;

public class PasswordInvalidException extends UserException {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 4081633597571256549L;
	
	public PasswordInvalidException(){
		super("Password Invalido");
	}
    public PasswordInvalidException(String Message){
        super(Message);
    }
}

