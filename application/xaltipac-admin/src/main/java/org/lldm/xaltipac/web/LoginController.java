package org.lldm.xaltipac.web;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.service.SettingService;
import org.lldm.xaltipac.service.UserService;
import org.lldm.xaltipac.service.forms.LoginForm;
import org.lldm.xaltipac.service.util.EncryptMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controlador para Login.
 * @author Juan Mateo Sauce.
 *
 */

@Controller
public class LoginController {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	UserService usuarioService;
	
	@Autowired
	SettingService settingService;
	
	@Autowired
	EncryptMD5 encryptMD5;
	
	// jmartinez: para debuggear localmente sin acceso a Internet
	Boolean debug_offline_mode = false;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model)
	{
		model.addAttribute("loginForm", new LoginForm());
		model.addAttribute("isLogin", true);
		
		return "login/login";
	}
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String getLogin(Model model, @Valid LoginForm loginForm,BindingResult resultado,ServletRequest servletRequest) 
    {
		model.addAttribute("isLogin", true);
		
				
    	if (resultado.hasErrors()){
			return "login/login";
    	}
    	
    	log.info("---------------" + loginForm.getJ_password());
    	log.info("---------------" + loginForm.getJ_username());
    	
    	return "forward:j_spring_security_check";
    }
    
    
    @RequestMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login/login";
    }
    
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(Model model) {
        return "logout";
    }

}