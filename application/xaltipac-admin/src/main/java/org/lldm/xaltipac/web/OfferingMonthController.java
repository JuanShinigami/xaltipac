/**
 * 
 */
package org.lldm.xaltipac.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.service.constantes.ActionsEnum;
import org.lldm.xaltipac.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Juan Mateo Sauce
 * @Controller Controlador para el reporte mensual.
 *
 */
@Controller
@PreAuthorize("hasRole('showMonthOffering')")
@RequestMapping(value = "/offering/month")
public class OfferingMonthController {
	
	private static final Logger LOG = Logger.getLogger(OfferingMonthController.class);
	
	@Autowired
	LogUtil logUtil;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listMonthOffering(Model model, HttpServletRequest request) {

		logUtil.logHistory(LOG, "/offering/month/", ActionsEnum.VIEW, request.getRemoteAddr(), "");

		return "offeringMonth/monthList";

	}

}
