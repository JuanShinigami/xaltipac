/**
 * 
 */
package org.lldm.xaltipac.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Offering;
import org.lldm.xaltipac.data.model.OfferingDetails;
import org.lldm.xaltipac.data.model.UserDetails;
import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.service.OfferingDetailService;
import org.lldm.xaltipac.service.OfferingService;
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
	OfferingService offeringService;

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
	
	@RequestMapping(value = "/searchOfferingByWeek", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> searchOfferingDByWeek(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {

		Map<String, Object> output = new HashMap<String, Object>();
		
		Integer idWeek = Integer.parseInt(requestParams.get("weekId"));
		boolean isEmpty = true;
		
		Week week = weekService.findOne(idWeek);
		

		List<OfferingDetails> offeringDetails = offeringDetailService.searchByWeek(week);

		if (offeringDetails.size() > 0) {
			isEmpty = false;
			for (OfferingDetails offeringDetails2 : offeringDetails) {
				LOG.debug("ENCONTRE --------------- "  + offeringDetails2.getOffering().getName());
			}
		}

		output.put("isEmpty", isEmpty);
		output.put("offeringDetails", offeringDetails);
		

		return output;
	}
	
	@Transactional
	@RequestMapping(value = "/weekClose", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> weekClose(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {

		Map<String, Object> output = new HashMap<String, Object>();
		
		Integer idWeek = Integer.parseInt(requestParams.get("anonymousWeek"));
		
		boolean isComplete = false;
		Offering offering = null;
		
		Week week = weekService.findOne(idWeek);
		UserDetails userDatils = userDetailsService.findOne(2);
		
		String indeces = requestParams.get("index-anonymous");
		String index[] = indeces.split(",");
		
		if(indeces.length() > 0){
			for(int x = 0; x < index.length ; x++){
				offering = offeringService.findOne(Integer.parseInt(requestParams.get("optionOffering-" + index[x])));
				double quantity = Double.parseDouble(requestParams.get("anonymous-" + index[x]));
				OfferingDetails offeringDetail = new OfferingDetails(quantity, offering, week, userDatils);
				// Agregarmos los anónimos
				//offeringDetailService.save(offeringDetail);
//				LOG.debug("OFRENDA ANONIMA A GUARDAR ------------- " + offeringDetail);
//				LOG.debug("ANONIMO ---------------------- " + index[x]);
//				LOG.debug("CANTIDAD ----------- " + requestParams.get("anonymous-" + index[x]));
//				LOG.debug("OFRENDA -------------------- " + requestParams.get("optionOffering-" + index[x]));
			}
		}
		
		
		Double totalPrice = offeringDetailService.getTotalOffering(week);
		LOG.debug("TOTAL QEU SE JUNTO ----------- " + totalPrice);
		
		LOG.debug("INDICES QUE ENCONTRE ------ " + indeces);
		LOG.debug("SEMANA A CERRAR ---------" + week);
		
		week.setEnabled(0);
		week.setTotal(totalPrice);
		// Editamos la semana
		//weekService.save(week);
		isComplete = true;


		output.put("isComplete", isComplete);

		return output;
	}
	
	@RequestMapping(value = "/generatePDF", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> generateWeekPDF(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {

		Map<String, Object> output = new HashMap<String, Object>();
		
		Integer idWeek = Integer.parseInt(requestParams.get("weekPDFId"));
		
		boolean isComplete = false;
		
		Week week = weekService.findOne(idWeek);
		
		LOG.debug("VOY A GENERAR EL PDF DE  --------------- " + week);
		
		List<OfferingDetails> offeringHombres = offeringDetailService.getAllOfferingDetailsByHombre(week);
		
		for (OfferingDetails offeringDetails : offeringHombres) {
			LOG.debug(offeringDetails.getUserDetails().getName());
		}
		
		
		isComplete = true;


		output.put("isComplete", isComplete);

		return output;
	}
	
}
