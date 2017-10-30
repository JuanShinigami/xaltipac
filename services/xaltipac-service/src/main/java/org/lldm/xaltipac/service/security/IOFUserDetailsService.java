package org.lldm.xaltipac.service.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Action;
import org.lldm.xaltipac.data.model.User;
import org.lldm.xaltipac.service.ActionService;
import org.lldm.xaltipac.service.UserService;
import org.lldm.xaltipac.service.security.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Clase service para saber quien esta logeado y obtener sus datos.
 * @author Juan Mateo.
 *
 */

public class IOFUserDetailsService implements UserDetailsService, Serializable {
    private static final long serialVersionUID = 2063839744109943650L;

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    UserService userService;
    @Autowired
    ActionService actionService;

    public UserDetails loadUserByUsername(final String userName)
            throws UsernameNotFoundException, DataAccessException {

        log.debug("HIBERNATE User details" + userName);

        User user = userService.findByUserName(userName);

        log.debug("--------- usuario " + user);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no existente");

        } else {

            log.debug("Existe password :" + user.getPassword());

            UserDetails userDetails = createUserDetails(user);

            if (userDetails != null) {
                System.out.println(userDetails.getAuthorities());
            } else {
                throw new UsernameNotFoundException(userName + " not found");
            }

            return userDetails;

        }
    }

    private MyUserDetails createUserDetails(User user) {
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUser(user);
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setEnabled(user.getEnabled() == 1 ? true : false);
        userDetails.setUsername(user.getUserName());
        userDetails.setPassword(user.getPassword());

        List<GrantedAuthority> authorities = getAuthorities(user);
        log.debug("Size of autorities" + authorities.size());
        
        for (GrantedAuthority ga : authorities)
            userDetails.addAuthority(ga);
        return userDetails;
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        
        log.debug("User ID : " + user.getId());
        
        List<Action> actions = actionService.findByProfile(user.getProfile());

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(
                actions.size());

        for (Action action : actions) {
            authorities.add(GrantedAuthorityUtils.toGrantedAuthority(action));
        }

        authorities.add(GrantedAuthorityUtils.authorityDefault());

        return authorities;
    }
}
