/**
 * 
 */
package org.lldm.xaltipac.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.OfferingDetails;
import org.lldm.xaltipac.data.model.UserDetails;
import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.service.OfferingDetailService;
import org.lldm.xaltipac.service.UserDetailsService;
import org.lldm.xaltipac.service.WeekService;
import org.lldm.xaltipac.service.constantes.ActionsEnum;
import org.lldm.xaltipac.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Juan Mateo Sauce
 *
 */

@Controller
@PreAuthorize("hasRole('showOfferingDetails')")
@RequestMapping(value = "/week/offeringDetails")
public class OfferingDetailsController {

	private static final Logger LOG = Logger.getLogger(OfferingDetailsController.class);

	@Autowired
	OfferingDetailService offeringDetailService;

	@Autowired
	WeekService weekService;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	LogUtil logUtil;

	@RequestMapping(value = "/{id}/", method = RequestMethod.POST)
	public String listOfferingDetails(Model model, @PathVariable Integer id, HttpServletRequest request) {

		logUtil.logHistory(LOG, "/week/offeringDetails/", ActionsEnum.VIEW, request.getRemoteAddr(), "");

		Week week = weekService.findOne(id);

		LOG.debug("Fecha que recibo de la URL. -------------- " + week);

		model.addAttribute("userList", userDetailsService.getAllUsersActive());
		model.addAttribute("week", week);
		return "offeringDetails/offeringDetailsList";

	}

	@RequestMapping(value = "/searchOfferingByWeekAndUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> editWeekPost(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {

		Map<String, Object> output = new HashMap<String, Object>();
		boolean isEmpty = true;
		Integer idWeek = Integer.parseInt(requestParams.get("idWeek"));
		Integer idUserD = Integer.parseInt(requestParams.get("idUser"));
		String indices = "";
		Week week = weekService.findOne(idWeek);
		UserDetails ud = userDetailsService.findOne(idUserD);

		List<OfferingDetails> offeringDetails = offeringDetailService.findByUserDetailsAndWeek(ud, week);

		if (offeringDetails.size() > 0) {
			isEmpty = false;
			for (OfferingDetails offeringDetails2 : offeringDetails) {
				indices += offeringDetails2.getId() + ",";
			}
		}

		indices = indices.substring(0, indices.length() - 1);

		LOG.debug("CADENA DE INDICES ------------- " + indices);
		output.put("isEmpty", isEmpty);
		output.put("offeringDetails", offeringDetails);
		output.put("indices", indices);

		return output;
	}

	@Transactional
	@RequestMapping(value = "/editOfferingDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> editOfferingDetails(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {
		
		Map<String, Object> output = new HashMap<String, Object>();
		boolean isSave = false;
		boolean isEmpty = true;
		String contentIndices = requestParams.get("contentIndices");
		
		String[] offeringDetail = contentIndices.split(",");
//		for(int x = 0; x<contentIndices.length(); x++){
//			LOG.debug("ENCONTRE UN INDEX ----------------- " + offeringDetail[x]);
//		}
		OfferingDetails offeringD = null;
		if(offeringDetail.length > 0){
			
			isEmpty = false;
			for(int x = 0; x<offeringDetail.length; x++){
				offeringD = offeringDetailService.findOne(Integer.parseInt(offeringDetail[x]));
				offeringD.setQuantity(Double.parseDouble(requestParams.get("offering-"+offeringD.getId())));
				offeringDetailService.save(offeringD);
			}
			isSave = true;
		}
		output.put("isEmpty", isEmpty);
		output.put("isSave", isSave);
		return output;
	}
	
	
	
}
