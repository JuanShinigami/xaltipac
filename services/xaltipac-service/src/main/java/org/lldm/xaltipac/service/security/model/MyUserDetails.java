package org.lldm.xaltipac.service.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lldm.xaltipac.data.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Modelo de usuario logeado.
 * @author Juan Mateo.
 *
 */

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 90L;

	private User user;
	private String password;
	private String username;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private List<GrantedAuthority> authotities;

	public MyUserDetails() {
	}
	
	public MyUserDetails(boolean _accountNonLocked,boolean _enabled,boolean _accountNonExpired) {
		this.accountNonLocked = _accountNonLocked;
		this.enabled = _enabled;
		this.accountNonExpired = _accountNonExpired;
	}
	
	
	public void addAuthority(GrantedAuthority authority) {
		if (authotities == null)
			authotities = new ArrayList<GrantedAuthority>();
		authotities.add(authority);
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authotities;
	}

	/**
	 * @return the _user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the _user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the _password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the _password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the _username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the _username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the _accountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @param accountNonExpired
	 *            the _accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @return the _accountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @param accountNonLocked
	 *            the _accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @return the _credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param credentialsNonExpired
	 *            the _credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @return the _enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the _enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

