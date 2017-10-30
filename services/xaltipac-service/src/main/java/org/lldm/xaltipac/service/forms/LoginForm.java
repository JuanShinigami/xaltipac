package org.lldm.xaltipac.service.forms;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Clase Form de Login.
 * @author Juan Mateo.
 *
 */

public class LoginForm {

    @NotEmpty(message="El identificador es requerido.")
    private String j_username;
    
    @NotEmpty(message="La contraseña es requerida.")
    private String j_password;

    /**
     * @return the j_username
     */
    public String getJ_username() {
        return j_username;
    }

    /**
     * @param j_username the j_username to set
     */
    public void setJ_username(String j_username) {
        this.j_username = j_username;
    }

    /**
     * @return the j_password
     */
    public String getJ_password() {
        return j_password;
    }

    /**
     * @param j_password the j_password to set
     */
    public void setJ_password(String j_password) {
        this.j_password = j_password;
    }

	@Override
	public String toString() {
		return "LoginForm [j_username=" + j_username + ", j_password=" + j_password + "]";
	}
    
    
}

