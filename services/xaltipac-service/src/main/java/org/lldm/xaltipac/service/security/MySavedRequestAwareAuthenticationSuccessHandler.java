package org.lldm.xaltipac.service.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.service.SettingService;
import org.lldm.xaltipac.service.constantes.SettingsEnum;
import org.lldm.xaltipac.service.security.model.RolePriorityUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * Clase que guarda la identidad del que logeo.
 * @author Juan Mateo.
 *
 */

public class MySavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	Logger log = Logger.getLogger(getClass());
    private Map<String, RolePriorityUrl> _rolePriority = new HashMap<String, RolePriorityUrl>();
    
    @Autowired
    SettingService settingService;
    
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
    	HttpSession session  = request.getSession();
    	Integer time  = new Integer(settingService.findByName(SettingsEnum.SESSION_TIME.toString()).getValue());
    	session.setMaxInactiveInterval(time);
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
//                getAuthentication().getPrincipal();
        String targetUrl = "/";
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

   public Map<String, RolePriorityUrl> getRolePriority() {
        return _rolePriority;
    }

    public void setRolePriority(Map<String, RolePriorityUrl> rolePriority) {
        this._rolePriority = rolePriority;
    }
}

