package org.lldm.xaltipac.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Action;
import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.data.model.Resource;
import org.lldm.xaltipac.data.model.User;
import org.lldm.xaltipac.service.ActionService;
import org.lldm.xaltipac.service.ProfileService;
import org.lldm.xaltipac.service.ResourceService;
import org.lldm.xaltipac.service.UserService;
import org.lldm.xaltipac.service.constantes.ActionsEnum;
import org.lldm.xaltipac.service.constantes.GeneralsEnum;
import org.lldm.xaltipac.service.forms.ProfileForm;
import org.lldm.xaltipac.service.security.model.MyUserDetails;
import org.lldm.xaltipac.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controlador para la administración de perfiles.
 * @author Juan Mateo Suace.
 *
 */

@Controller
@PreAuthorize("hasRole('showProfiles')")
@RequestMapping(value = "/perfil")
public class ProfileController {

    private static final Logger LOG = Logger.getLogger(ProfileController.class);

    @Autowired
    ProfileService profileService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    ActionService actionService;
    @Autowired
    UserService userService;
    @Autowired
    LogUtil logUtil;

    private final String PROFILE_CREATE = "perfil/createProfile";
    private final String PROFILE_EDIT = "perfil/editProfile";

    /* LISTADO DE PERFILES */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listProfiles(Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/perfil/", ActionsEnum.VIEW,
                request.getRemoteAddr(), "");

        List<Profile> profileList = profileService.getAll();
        model.addAttribute("profiles", profileList);

        return "perfil/perfiles";

    }

    @PreAuthorize("hasRole('addProfile')")
    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String newProfile(Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/perfil/agregar", ActionsEnum.ADD,
                request.getRemoteAddr(), "");

        ProfileForm profileForm = new ProfileForm(
                resourceService.getAllResources());

        model.addAttribute("profileForm", profileForm);
        return PROFILE_CREATE;
    }

    @Transactional
    @PreAuthorize("hasRole('addProfile')")
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public String newUserProcess(@Valid ProfileForm profileForm,
            BindingResult result, Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/perfil/agregar", ActionsEnum.ADD,
                request.getRemoteAddr(), profileForm.toString());

        if (result.hasErrors()) {
            return PROFILE_CREATE;
        }

        Profile profileExist = profileService.findByName(profileForm.getName());

        if (profileExist != null) {
            result.addError(new ObjectError("exist",
                    "No se permite dar de alta dos perfiles con el mismo nombre"));
            return PROFILE_CREATE;
        }

        Profile profile = new Profile();

        profile.setName(profileForm.getName());
        profile.setLastModified(new Date());
        profile.setCreated(new Date());
        profileService.save(profile);

        actionService.saveMany(profile, profileForm.getActionResourceList());

        ProfileForm profileClean = new ProfileForm(
                resourceService.getAllResources());
        model.addAttribute("profileForm", profileClean);

        model.addAttribute("ESTATUS", "Los datos se guardaron correctamente.");

        return PROFILE_CREATE;

    }

    @PreAuthorize("hasRole('editProfile')")
    @RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
    public String show(Model model, @PathVariable Integer id,
            HttpServletRequest request) throws Exception {
        logUtil.logHistory(LOG, "/perfil/editar", ActionsEnum.UPDATE,
                request.getRemoteAddr(), String.valueOf(id));

        if (id == GeneralsEnum.PROFILE_SUPER_ADMIN.getId()) {
            // if para evitar eliminar el perfil business
            model.addAttribute("ESTATUS",
                    "No se pueden editar Perfiles administrativos");
            return listProfiles(model, request);
        }

        Profile profile = profileService.findOne(id);

        if (profile != null) {
            List<Action> actions = actionService.findByProfile(profile);
            List<Resource> allResources = resourceService.getAllResources();
            List<Resource> userResources = resourceService
                    .getResourcesToActions(actions);

            ProfileForm profileForm = new ProfileForm(allResources, profile,
                    userResources);
            model.addAttribute("profileForm", profileForm);
            return PROFILE_EDIT;

        } else {
            return listProfiles(model, request);
        }
    }

    @Transactional
    @PreAuthorize("hasRole('editProfile')")
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editProfileProcess(@Valid ProfileForm profileForm,
            BindingResult result, Model model, HttpServletRequest request) {

        logUtil.logHistory(LOG, "/perfil/agregar", ActionsEnum.ADD,
                request.getRemoteAddr(), profileForm.toString());

        if (result.hasErrors()) {
            return PROFILE_EDIT;
        } else {

            MyUserDetails userSession = (MyUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();

            Profile profileExist = profileService.findByName(profileForm
                    .getName());

            if (profileExist != null
                    && profileExist.getId() != profileForm.getId()) {
                result.addError(new ObjectError("exist",
                        "No se permite dar de alta dos perfiles con el mismo nombre"));
                return PROFILE_EDIT;
            }

            User currentUser = userService.findByUserName(userSession
                    .getUsername());

            Profile profile = new Profile(profileForm.getId());
            profile.setName(profileForm.getName());
            profile.setCreated(currentUser.getProfile().getCreated());
            profile.setLastModified(new Date());
            profileService.save(profile);

            actionService
                    .saveMany(profile, profileForm.getActionResourceList());

            ProfileForm profileClean = new ProfileForm(
                    resourceService.getAllResources());
            model.addAttribute("profileForm", profileClean);

            if (profile.getId() != currentUser.getProfile().getId()) {
                model.addAttribute("ESTATUS",
                        "Los datos se actualizaron correctamente.");
            } else {
                model.addAttribute(
                        "ESTATUS",
                        "Los datos se actualizaron correctamente. Por favor vuelva a iniciar sesión para ver los cambios en su perfil");
            }
            return listProfiles(model, request);

        }

    }

    @Transactional
    @PreAuthorize("hasRole('deleteProfile')")
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String deleteUserProcess(@RequestParam int idProfile, Model model,
            HttpServletRequest request) {
        logUtil.logHistory(LOG, "/perfil/eliminar", ActionsEnum.DELETE,
                request.getRemoteAddr(), String.valueOf(idProfile));

        if (idProfile == GeneralsEnum.PROFILE_SUPER_ADMIN.getId()
                || idProfile == GeneralsEnum.PROFILE_SUPER_ADMIN_BUSSINES
                        .getId()) {
            // if paar evitar eliminar el perfil bisiles
            model.addAttribute("ESTATUS",
                    "No se pueden eliminar Perfiles administrativos");
            return listProfiles(model, request);
        }

        Profile profile = profileService.findOne(idProfile);

        if (profile == null) {
            model.addAttribute("ESTATUS",
                    "No ha sido posible eliminar el perfil");
        } else {

            actionService.delete(actionService.findByProfile(profile));
            profileService.delete(profile);

            model.addAttribute("ESTATUS", "El perfil " + profile.getName()
                    + " se eliminó correctamente.");
        }
        return listProfiles(model, request);
    }

    @Transactional
    @PreAuthorize("hasRole('deleteProfile')")
    @RequestMapping(value = "eliminar/obtenerUsuariosPerfil", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> obtenerUsuariosPerfil(
            @RequestParam Map<String, String> requestParams,
            HttpServletRequest request) throws Exception {
        logUtil.logHistory(LOG, "/perfil/eliminar/obtenerUsuariosPerfil",
                ActionsEnum.DELETE, request.getRemoteAddr(),
                requestParams.toString());

        MyUserDetails userSession = (MyUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User currentUser = userService
                .findByUserName(userSession.getUsername());

        Map<String, Object> output = new HashMap<String, Object>();
        List<String> usuarios = new ArrayList<String>();

        String status = "FAIL";
        String statusProfileLoged = "false";

        int id = new Integer(requestParams.get("idProfile"));

        List<User> userList = userService.getUsersByProfile(new Profile(id));

        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                usuarios.add(user.getUserName());
            }

            if (id == currentUser.getProfile().getId()) {
                statusProfileLoged = "true";
                output.put("statusProfileLoged", statusProfileLoged);
                output.put(
                        "CURRENT_PROFILE",
                        "No es posible eliminar el perfil con el que ha iniciado sesión. Inicie sesión con otro usuario para eliminar el perfil seleccionado o cambie su perfil.");
            } else {
                output.put("statusProfileLoged", statusProfileLoged);
            }

            status = "true";
            output.put("STATUS", status);
            output.put("users", usuarios);
        } else {
            status = "false";
            output.put("STATUS", status);
        }

        return output;

    }

}
