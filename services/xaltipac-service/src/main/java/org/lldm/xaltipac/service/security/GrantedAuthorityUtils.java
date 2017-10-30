package org.lldm.xaltipac.service.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Action;
import org.springframework.security.core.GrantedAuthority;

/**
 * Clase para dar autorización optinido en las acciones.
 * @author Juan Mateo.
 *
 */

public class GrantedAuthorityUtils {

    static Logger log = Logger.getLogger(GrantedAuthorityUtils.class);

    public static List<GrantedAuthority> toGrantedAuthority(List<Action> actions) {

        if (actions == null || actions.size() == 0)
            return null;

        List<GrantedAuthority> authotities = new ArrayList<GrantedAuthority>(
                actions.size());

        GrantedAuthority ga = null;
        for (Action action : actions) {
            ga = new IOFGrantedAuthority(action);
            authotities.add(ga);

        }

        return authotities;
    }

    public static GrantedAuthority authorityDefault() {
        GrantedAuthority defaul = new IOFGrantedAuthorityStatic();
        return defaul;
    }

    public static GrantedAuthority toGrantedAuthority(Action action) {

        if (action == null) {
            log.info("rol nulo");
            return null;
        }

        GrantedAuthority au = new IOFGrantedAuthority(action);
        return au;
    }
}

class IOFGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 14L;

    private Action action;

    public IOFGrantedAuthority(Action action) {
        this.action = action;
    }

    public String getAuthority() {
        String rol = "";

        if (action.getResource() != null) {
            rol = action.getResource().getName();
            return rol;
        } else {
            return rol;
        }
    }

}

class IOFGrantedAuthorityStatic implements GrantedAuthority {

    private static final long serialVersionUID = 14L;

    public IOFGrantedAuthorityStatic() {
    }

    public String getAuthority() {
        return "ROLE_USER";
    }
}
