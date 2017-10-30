/**
 * 
 */
package org.lldm.xaltipac.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.service.OfferingDetailService;
import org.lldm.xaltipac.service.OfferingService;
import org.lldm.xaltipac.service.WeekService;
import org.lldm.xaltipac.service.constantes.ActionsEnum;
import org.lldm.xaltipac.service.forms.WeekForm;
import org.lldm.xaltipac.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Juan Mateo Sauce
 *
 */

@Controller
@PreAuthorize("hasRole('showWeeks')")
@RequestMapping(value = "/week")
public class WeekController {

	private static final Logger LOG = Logger.getLogger(WeekController.class);

	@Autowired
	WeekService weekService;

	@Autowired
	OfferingService offeringService;

	@Autowired
	OfferingDetailService offeringDetailService;

	@Autowired
	LogUtil logUtil;

	private final String WEEK_CREATE = "week/createWeek";

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listWeeks(Model model, HttpServletRequest request) {

		
		logUtil.logHistory(LOG, "/week/", ActionsEnum.VIEW, request.getRemoteAddr(), "");
		
		List<Week> weekList = weekService.getAllWeek();

		for (Week week : weekList) {
			String parts[] = week.getDay().split("-");
			week.setDay(parts[2] + "-" + parts[1] + "-" + parts[0]);
		}
		model.addAttribute("weeks", weekList);

		return "week/weeksList";

	}

	@PreAuthorize("hasRole('addWeek')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String newWeek(Model model, HttpServletRequest request) {

		logUtil.logHistory(LOG, "/week/add", ActionsEnum.ADD, request.getRemoteAddr(), "");

		WeekForm weekForm = new WeekForm(offeringService.getAllOfferings());

		model.addAttribute("weekForm", weekForm);
		return WEEK_CREATE;
	}
	
	@Transactional
    @PreAuthorize("hasRole('addWeek')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String newWeekPost(@Valid WeekForm weekForm,
            BindingResult result, Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/week/add", ActionsEnum.ADD,
                request.getRemoteAddr(), weekForm.toString());

        if (result.hasErrors()) {
            return WEEK_CREATE;
        }

        Week weekExist = weekService.findByDay(weekForm.getDay());
        

        if (weekExist != null) {
            result.addError(new ObjectError("exist",
                    "No se puede dar de alta una fecha que ya existe."));
            return WEEK_CREATE;
        }
        
        Week week = new Week();
        week.setDay(weekForm.getDay());
        week.setTotal(0.0);
        week.setEnabled(1);
        week.setNotes(weekForm.getNotes());
        
        weekService.save(week);

        offeringDetailService.saveManyOfferginDetails(week, weekForm.getOfferingDetilsOfferingList());
        WeekForm weekClean = new WeekForm(offeringService.getAllOfferings());
        model.addAttribute("weekForm", weekClean);


        model.addAttribute("ESTATUS", "Los datos se guardaron correctamente.");

        return WEEK_CREATE;

    }
	
	@PreAuthorize("hasRole('editWeek')")
	@RequestMapping(value = "/editView", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> editWeek(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {
		
		Map<String, Object> output = new HashMap<String, Object>();
		
		String id = requestParams.get("id");
		
		boolean flagExist = false;
		Week week = null;
		
		if(id != ""){
			week = weekService.findOne(Integer.parseInt(id));
		}
		
		output.put("wwek", week);
		output.put("flagExist", flagExist);

		return output;
	}
	
	@PreAuthorize("hasRole('editWeek')")
	@RequestMapping(value = "/editViewSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> editWeekPost(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {
		
		Map<String, Object> output = new HashMap<String, Object>();
		
		String id = requestParams.get("id");
		
		boolean flagExist = false;
		boolean flagSave = false;
		Week week = null;
		
		if(id != ""){
			week = weekService.findOne(Integer.parseInt(id));
			//week.setDay();
			weekService.save(week);
		}
		
		output.put("wwek", week);
		output.put("flagExist", flagExist);
		output.put("flagSave", flagSave);

		return output;
	}

}
