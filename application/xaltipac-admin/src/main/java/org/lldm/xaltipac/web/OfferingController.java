/**
 * 
 */
package org.lldm.xaltipac.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Offering;
import org.lldm.xaltipac.service.OfferingService;
import org.lldm.xaltipac.service.constantes.ActionsEnum;
import org.lldm.xaltipac.service.forms.OfferingForm;
import org.lldm.xaltipac.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Juan Mateo Sauce
 *
 */
@Controller
@PreAuthorize("hasRole('showOfferings')")
@RequestMapping(value = "/offering")
public class OfferingController {
	
	private static final Logger LOG = Logger
            .getLogger(OfferingController.class);

    @Autowired
    OfferingService offeringService;
    
    @Autowired
    LogUtil logUtil;
    
    private final String OFFERING_CREATE = "offering/createOffering";
    private final String OFFERING_EDIT = "offering/editOffering";
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listOfferings(Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/offering/", ActionsEnum.VIEW,
                request.getRemoteAddr(), "");

        model.addAttribute("offeringsList", offeringService.getAllOfferings());

        return "offering/offeringsList";
    }

    @PreAuthorize("hasRole('addOffering')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newOffering(Model model, HttpServletRequest request) {
        logUtil.logHistory(LOG, "/offering/add", ActionsEnum.ADD,
                request.getRemoteAddr(), "");

        model.addAttribute("offeringForm", new OfferingForm());
        return OFFERING_CREATE;
    }

    @Transactional
    @PreAuthorize("hasRole('addOffering')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String newOfferingPost(@Valid OfferingForm offeringForm,
            BindingResult result, Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/offering/add", ActionsEnum.ADD,
                request.getRemoteAddr(), offeringForm.toString());

        if (result.hasErrors()) {
            return OFFERING_CREATE;
        }

        Offering offeringExist = offeringService.findByName(offeringForm.getName());

        if (offeringExist != null) {
            result.addError(new ObjectError("exist",
                    "No se permite dar de alta dos ofrendas con el mismo nombre"));
            return OFFERING_CREATE;
        }

        Offering offering = new Offering(offeringForm.getName(), offeringForm.getDescription(), new Date(), new Date());
        offeringService.save(offering);

        OfferingForm offeringFormClean = new OfferingForm();
        model.addAttribute("offeringForm", offeringFormClean);
        model.addAttribute("ESTATUS", "Los datos se guardaron correctamente.");
        return OFFERING_CREATE;

    }

    @PreAuthorize("hasRole('editOffering')")
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String editOffering(Model model, @PathVariable Integer id,
            HttpServletRequest request) throws Exception {
        logUtil.logHistory(LOG, "/offering/edit", ActionsEnum.UPDATE,
                request.getRemoteAddr(), String.valueOf(id));

        Offering offering = offeringService.findOne(id);

        if (offering != null) {
            model.addAttribute("offeringForm",
                    new OfferingForm(offering.getId(), offering.getName(), offering.getDescription()));
            return OFFERING_EDIT;

        } else {
            return listOfferings(model, request);
        }

    }

    @Transactional
    @PreAuthorize("hasRole('editOffering')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editOfferingPost(@Valid OfferingForm offeringForm,
            BindingResult result, Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/offering/edit", ActionsEnum.ADD,
                request.getRemoteAddr(), offeringForm.toString());

        if (result.hasErrors()) {
            return OFFERING_EDIT;
        }

        Offering offeringExist = offeringService.findByName(offeringForm.getName());

        if (offeringExist != null
                && (offeringExist.getId() != offeringForm.getId())) {

            result.addError(new ObjectError("exist",
                    "No se permite editar una ofrenda con un nombre que ya existe"));
            return OFFERING_EDIT;
        }

        Offering offering = new Offering(offeringForm.getId(), offeringForm.getName(), offeringForm.getDescription(), new Date());
        offeringService.save(offering);

        model.addAttribute("ESTATUS", "Los datos se actualizaron correctamente.");
        return listOfferings(model, request);

    }

    @Transactional
    @PreAuthorize("hasRole('deleteOffering')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteResource(@RequestParam int idOffering, Model model,
            HttpServletRequest request) {

        logUtil.logHistory(LOG, "/offering/delete", ActionsEnum.DELETE,
                request.getRemoteAddr(), String.valueOf(idOffering));

        Offering offering = offeringService.findOne(idOffering);

        if (offering == null) {
            model.addAttribute("ESTATUS", "Ofrenda no existente");
            return listOfferings(model, request);
        }
        offeringService.delete(offering);
        model.addAttribute("ESTATUS", "La ofrenda " + offering.getName()
                + " se eliminó correctamente.");
        return listOfferings(model, request);
    }

}
