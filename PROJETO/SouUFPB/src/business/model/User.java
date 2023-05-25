

package business.model;

import java.io.Serializable;
import infra.Timestamp;

public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3409171233621036055L;
	
	
	private String login;
    private String email;
    private String senha;
    private String timestamp;
    private Timestamp time;
    
    public User(String login, String email, String senha) {
        super();
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.timestamp = time.getTime();

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
        return login +"\n"+ senha;
    }

	public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = time.getTime();
    }
    
}
