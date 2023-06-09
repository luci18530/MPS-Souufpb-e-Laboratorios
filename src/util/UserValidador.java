package util;


public class UserValidador {

	public static void validateName(String name) throws LoginInvalidException   {
		
		if(name.length() > 50) 
			throw new LoginInvalidException("Login com mais de 50 caracteres!\n");
		else if(name.length() == 0)
			throw new LoginInvalidException("Login vazio!\n");
		else if(name.matches(".*\\d.*"))
			 throw new LoginInvalidException("Login nao pode conter numeros!\n");
		else if (name.matches(".*[^A-Za-z0-9].*"))
			throw new LoginInvalidException("Login nao pode conter simbolos!\n");;
	}


	public static void validateEmail(String email) throws EmailInvalidException {
		if (email == null || email.isBlank()) {
			throw new EmailInvalidException("Email vazio!");
		}
	
		if (email.length() > 50) {
			throw new EmailInvalidException("Email com mais de 50 caracteres!");
		}
	
		String[] emailParts = email.split("@");
		if (emailParts.length != 2 || emailParts[0].isBlank() || emailParts[1].isBlank()) {
			throw new EmailInvalidException("Email inválido!");
		}
	
		String domain = emailParts[1];
		if (!domain.matches("[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			throw new EmailInvalidException("Domínio de email inválido!");
		}
	}
	
	public static void validatePassword(String pass) throws PasswordInvalidException   {
		
		 if(pass.length() > 12) 
			 throw new PasswordInvalidException("Senha nao pode possuir mais de 12 caracteres!\n");
		 else if(pass.length() < 8)
			 throw new PasswordInvalidException("Senha nao pode possuir menos de 8 caracteres!\n");
		 else if(!pass.matches(".*\\d.*") || !pass.matches(".*\\c.*")) 
			 throw new PasswordInvalidException("Senha deve possuir caracteres e numeros!\n");
		 else if (countNumbers(pass) < 2)
			throw new PasswordInvalidException("Senha deve ter pelo menos 2 numeros!\n");
	}
	
	 private static int countNumbers(String s){
	        int count = 0;
	        for (int i = 0, len = s.length(); i < len; i++) {
	            if (Character.isDigit(s.charAt(i))) {
	                count++;
	            }
	        }
	        return count;
	    }
	
	
	
	

}
