package ColocationServicesModel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.GeneratedValue;

/**
 * User
 * 
 * @author philippe
 * @Copyright Cedric Wemmert
 */

@Entity
@JsonIgnoreProperties({"password"})
public class User implements Serializable {
	@Id
        @GeneratedValue
	private String login;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String firstname;
	private String lastname;
        private boolean Creator; 

	public User() {	
	}
	
	public User(String login, String password, String firstname, String lastname) {
		super();
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
                this.Creator =false;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

    public boolean isCreator() {
        return Creator; //To change body of generated methods, choose Tools | Templates.
    }
    public void Creator(){
        this.Creator=true;
    }
}
