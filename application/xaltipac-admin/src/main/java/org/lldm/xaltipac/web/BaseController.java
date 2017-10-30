package org.lldm.xaltipac.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.lldm.xaltipac.service.constantes.ModulesEnum;
import org.lldm.xaltipac.service.constantes.RolsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * Clase Base que verifica los errores y permisos.
 * @author Juan Mateo Sauce.
 *
 */

public abstract class BaseController {

	protected String canAdd;
	protected String canUpdate;
	protected String canDelete;
	
	@Autowired
	private ApplicationContext context;

	public String getMessage(final String message) {
		return context.getMessage(message, null, Locale.getDefault());
	}
	
	public String getMessage(final String message, final String parameter) {
		return getMessage(message, new String[] {parameter});
	}
	
	public String getMessage(final String message, final String[] parameters) {
		return context.getMessage(message, parameters, Locale.getDefault());
	}
	
	@ModelAttribute
	public void setActionsPermision(Model model){
		
		Map<String, String> actionsMap = getActions();
		
		canAdd = actionsMap.get("ADD");
		canUpdate = actionsMap.get("UPDATE");
		canDelete = actionsMap.get("DELETE");
		
		model.addAttribute("canAdd", canAdd);
		model.addAttribute("canUpdate", canUpdate);
		model.addAttribute("canDelete", canDelete);
	}
	
	public Map<String,String> swithMapAcions(ModulesEnum modulesEnum){
		Map<String, String> output = new HashMap<String, String>();
		
		switch (modulesEnum) {
		case USERS:
			output.put("ADD",RolsEnum.USER_ADD.toString());
			output.put("UPDATE",RolsEnum.USER_UPDATE.toString());
			output.put("DELETE",RolsEnum.USER_DELETE.toString());
			break;
		case BUSINESS:
			output.put("ADD",RolsEnum.BUSINESS_ADD.toString());
			output.put("UPDATE",RolsEnum.BUSINESS_UPDATE.toString());
			output.put("DELETE",RolsEnum.BUSINESS_DELETE.toString());
			break;
		case CATEGORIES:
			output.put("ADD",RolsEnum.CATEGORY_ADD.toString());
			output.put("UPDATE",RolsEnum.CATEGORY_UPDATE.toString());
			output.put("DELETE",RolsEnum.CATEGORY_DELETE.toString());
			break;
		case ALGORITHMS:
			output.put("ADD",RolsEnum.ALGORITHMS_ADD.toString());
			output.put("UPDATE",RolsEnum.ALGORITHMS_UPDATE.toString());
			output.put("DELETE",RolsEnum.ALGORITHMS_DELETE.toString());
			break;
		case ENGINES:
			output.put("ADD",RolsEnum.ENGINES_ADD.toString());
			output.put("UPDATE",RolsEnum.ENGINES_UPDATE.toString());
			output.put("DELETE",RolsEnum.ENGINES_DELETE.toString());
			break;
		case PROFILES:
			output.put("ADD",RolsEnum.PROFILE_ADD.toString());
			output.put("UPDATE",RolsEnum.PROFILE_UPDATE.toString());
			output.put("DELETE",RolsEnum.PROFILE_DELETE.toString());
			break;
		case SUBCATEGORIES:
			output.put("ADD",RolsEnum.SUBCATEGORY_ADD.toString());
			output.put("UPDATE",RolsEnum.SUBCATEGORY_UPDATE.toString());
			output.put("DELETE",RolsEnum.SUBCATEGORY_DELETE.toString());
			break;
		case INFORMATIONS:
			output.put("ADD",RolsEnum.INFORMATION_ADD.toString());
			output.put("UPDATE",RolsEnum.INFORMATION_UPDATE.toString());
			output.put("DELETE",RolsEnum.INFORMATION_DELETE.toString());
			break;
		}

		return output;
	}
	
	abstract  Map<String,String> getActions();
}
