

package business.model;

import java.io.Serializable;


public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3409171233621036055L;
	
	
	private String login, senha;
    
    public User(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
