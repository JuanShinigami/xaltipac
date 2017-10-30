package org.lldm.xaltipac.service.security;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.service.util.EncryptMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Clase Dao para verificar credenciales de logeo.
 * @author Juan Mateo.
 *
 */

public class IOFDaoAuthenticationProvider extends DaoAuthenticationProvider{
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	EncryptMD5 encryptMD5;
    
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        
    	Object salt = null;
        log.info("Checking password fidelity");
        
        if (getSaltSource() != null) {
            salt = getSaltSource().getSalt(userDetails);
        }
  
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException(messages.getMessage(
            		"AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
  
        String presentedPassword = authentication.getCredentials().toString();
        
        //System.out.println("encode present :" + getPasswordEncoder().encodePassword(presentedPassword, salt));
        
        String encodedPAss = "";
        try {
        	encodedPAss = encryptMD5.main(presentedPassword);    	
			
        } catch (Exception e) {	
			
        }
        

		if (!userDetails.getPassword().equals(encodedPAss)) {
		    
		    throw new BadCredentialsException(messages.getMessage(	
		            "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials - Wrong password"));
		}
        
    }
}
