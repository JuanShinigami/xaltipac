package org.lldm.xaltipac.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Resource;
import org.lldm.xaltipac.service.ResourceService;
import org.lldm.xaltipac.service.constantes.ActionsEnum;
import org.lldm.xaltipac.service.forms.ResourceForm;
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
 * 
 * Controlador para administrar los recursos.
 * @author Juan Mateo Sauce.
 *
 */

@Controller
@PreAuthorize("hasRole('showResources')")
@RequestMapping(value = "/recurso")
public class ResourceController {

    private static final Logger LOG = Logger
            .getLogger(ResourceController.class);

    @Autowired
    ResourceService resourceService;
    @Autowired
    LogUtil logUtil;

    private final String RESOURCE_CREATE = "resource/createResource";
    private final String RESOURCE_EDIT = "resource/editResource";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listResources(Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/resource/", ActionsEnum.VIEW,
                request.getRemoteAddr(), "");

        model.addAttribute("resourcesList", resourceService.getAllResources());

        return "resource/resourcesList";
    }

    @PreAuthorize("hasRole('addResource')")
    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String newResource(Model model, HttpServletRequest request) {
        logUtil.logHistory(LOG, "/recurso/agregar", ActionsEnum.ADD,
                request.getRemoteAddr(), "");

        model.addAttribute("resourceForm", new ResourceForm());
        return RESOURCE_CREATE;
    }

    @Transactional
    @PreAuthorize("hasRole('addResource')")
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public String newResourcePost(@Valid ResourceForm resourceForm,
            BindingResult result, Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/regurso/agregar", ActionsEnum.ADD,
                request.getRemoteAddr(), resourceForm.toString());

        if (result.hasErrors()) {
            return RESOURCE_CREATE;
        }

        Resource resourceExist = resourceService.findByName(resourceForm
                .getName());

        if (resourceExist != null) {
            result.addError(new ObjectError("exist",
                    "No se permite dar de alta dos recursos con el mismo nombre"));
            return RESOURCE_CREATE;
        }

        Resource resource = new Resource(resourceForm.getName(),
                resourceForm.getPath());
        resourceService.save(resource);

        ResourceForm resourceFormClean = new ResourceForm();
        model.addAttribute("resourceForm", resourceFormClean);
        model.addAttribute("ESTATUS", "Los datos se guardaron correctamente.");
        return RESOURCE_CREATE;

    }

    @PreAuthorize("hasRole('editResource')")
    @RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
    public String show(Model model, @PathVariable Integer id,
            HttpServletRequest request) throws Exception {
        logUtil.logHistory(LOG, "/recurso/editar", ActionsEnum.UPDATE,
                request.getRemoteAddr(), String.valueOf(id));

        Resource resource = resourceService.findOne(id);

        if (resource != null) {
            model.addAttribute("resourceForm",
                    new ResourceForm(resource.getId(), resource.getName(),
                            resource.getPath()));
            return RESOURCE_EDIT;

        } else {
            return listResources(model, request);
        }

    }

    @Transactional
    @PreAuthorize("hasRole('editResource')")
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editResourcePost(@Valid ResourceForm resourceForm,
            BindingResult result, Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/recurso/editar", ActionsEnum.ADD,
                request.getRemoteAddr(), resourceForm.toString());

        if (result.hasErrors()) {
            return RESOURCE_EDIT;
        }

        Resource resourceExist = resourceService.findByName(resourceForm
                .getName());

        if (resourceExist != null
                && (resourceExist.getId() != resourceForm.getId())) {

            result.addError(new ObjectError("exist",
                    "No se permite editar un recurso con un nombre que ya existe"));
            return RESOURCE_EDIT;
        }

        Resource resource = new Resource(resourceForm.getId(),
                resourceForm.getName(), resourceForm.getPath());
        resourceService.save(resource);

        model.addAttribute("ESTATUS", "Los datos se guardaron correctamente.");
        return listResources(model, request);

    }

    @Transactional
    @PreAuthorize("hasRole('deleteResource')")
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String deleteResource(@RequestParam int idResource, Model model,
            HttpServletRequest request) {

        logUtil.logHistory(LOG, "/perfil/eliminar", ActionsEnum.DELETE,
                request.getRemoteAddr(), String.valueOf(idResource));

        Resource resource = resourceService.findOne(idResource);

        if (resource == null) {
            model.addAttribute("ESTATUS", "Recurso no existente");
            return listResources(model, request);
        }

        resourceService.delete(resource);
        model.addAttribute("ESTATUS", "El Recurso " + resource.getName()
                + " se eliminó correctamente.");

        return listResources(model, request);
    }

}
