package org.lldm.xaltipac.service.util;

import java.util.Date;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Setting;
import org.lldm.xaltipac.service.SettingService;
import org.lldm.xaltipac.service.constantes.ActionsEnum;
import org.lldm.xaltipac.service.constantes.AnswersEnum;
import org.lldm.xaltipac.service.constantes.SettingsEnum;
import org.lldm.xaltipac.service.security.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *@author Juan Mateo.
 * Clase para escribir en el log de bitacora
 */
@Component
public class LogUtil {
	
	@Autowired
	SettingService settingService;

	
	/**
	 *Metodo para escribir el el log de bitacora
	 *@param  log  Log del controller
	 *@param  usuario  Usuario que ejecuto la accion
	 *@param  pageRequest Pagina de donde ingreso
	 *@param action Accion ejecutada tomada de un Enum de acciones
	 *@param ipAddress Direccion ip remota
	 *@param parameters Array de parametros del request
	 */
	public  void logHistory(Logger log,
			String pageRequest, Enum<ActionsEnum> action, String ipAddress,
			String... parameters) {
		
		
		Setting setting = settingService.findByName(SettingsEnum.LOG_USER.toString());
		
		if(setting != null && setting.getValue().equalsIgnoreCase(AnswersEnum.YES.toString())){

			MyUserDetails user = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String name = user.getUsername();
			   
			log.info("Fecha Acceso [" + new Date() + "] Usuario [" + name
					+ "] Pagina [" + pageRequest + "] Parametros ["
					+ parametersToString(parameters) + "] Operacion [" + action
					+ "] IP [" + ipAddress + "]");
		}
	}
	
	private  String parametersToString(String [] parameters){

		StringBuffer parametesSB = new StringBuffer();

		if (parameters != null) {
			
			for (int i = 0; i < parameters.length ; i ++) {
				parametesSB.append(parameters[i]);				
				
				if(i < parameters.length-1){
					parametesSB.append(",");
				}
			}
		}
		
		return parametesSB.toString();
	}
}
