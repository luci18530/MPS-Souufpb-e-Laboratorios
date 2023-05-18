

package business.model;

import java.io.Serializable;


public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3409171233621036055L;
	
	
	private String login;
    private String email;
    private String senha;
    
    public User(String login, String email, String senha) {
        super();
        this.login = login;
        this.email = email;
        this.senha = senha;
	}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        return this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
     
    public String toString(){
        return login +"\n"+senha;
    }

	
    
}
