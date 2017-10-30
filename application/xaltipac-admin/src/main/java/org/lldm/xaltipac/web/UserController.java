package org.lldm.xaltipac.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.District;
import org.lldm.xaltipac.data.model.Group;
import org.lldm.xaltipac.data.model.Neighborhood;
import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.data.model.StatesOfMexico;
import org.lldm.xaltipac.data.model.User;
import org.lldm.xaltipac.data.model.UserDetails;
import org.lldm.xaltipac.service.DistrictService;
import org.lldm.xaltipac.service.GroupService;
import org.lldm.xaltipac.service.NeighborhoodService;
import org.lldm.xaltipac.service.ProfileService;
import org.lldm.xaltipac.service.StatesOfMexicoService;
import org.lldm.xaltipac.service.UserDetailsService;
import org.lldm.xaltipac.service.UserService;
import org.lldm.xaltipac.service.constantes.ActionsEnum;
import org.lldm.xaltipac.service.constantes.GeneralsEnum;
import org.lldm.xaltipac.service.constantes.ModulesEnum;
import org.lldm.xaltipac.service.forms.PageData;
import org.lldm.xaltipac.service.forms.UserForm;
import org.lldm.xaltipac.service.security.model.MyUserDetails;
import org.lldm.xaltipac.service.util.EncryptMD5;
import org.lldm.xaltipac.service.util.LogUtil;
import org.lldm.xaltipac.service.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * Controlador para administrar los usuarios.
 * @author Juan Mateo Sauce.
 *
 */

@Controller
@PreAuthorize("hasRole('showUsers')")
@RequestMapping(value = "/usuario")
public class UserController extends BaseController {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    UserService userService;
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    ProfileService perfilService;
    
    @Autowired
    EncryptMD5 encryptMD5;
    
    @Autowired
    LogUtil logUtil;
    
    @Autowired
    GroupService groupService;
    
    @Autowired
    StatesOfMexicoService statesOfMexicoService;
    
    @Autowired
	NeighborhoodService neighborhoodService;
	
	@Autowired
	DistrictService districtService;

    private final String USER_CREATE = "user/createUser";
    private final String USER_EDIT = "user/editUser";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listUsers(Model model, PageData pageData,
            HttpServletRequest request) {
        logUtil.logHistory(log, "/usuario/", ActionsEnum.VIEW,
                request.getRemoteAddr(), "");

        PageWrapper<UserDetails> page = new PageWrapper<UserDetails>(
                userDetailsService.getAllActiveUsers(pageData), "usuarios");

        model.addAttribute("page", page);
        return "user/userList";
    }

    @PreAuthorize("hasRole('addUser')")
    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String newUser(Model model, HttpServletRequest request) {
        logUtil.logHistory(log, "/usuario/agregar", ActionsEnum.ADD,
                request.getRemoteAddr(), "");

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return USER_CREATE;
    }

    @Transactional
    @PreAuthorize("hasRole('addUser')")
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public String newUserProcess(@Valid UserForm userForm,
            BindingResult result, Model model, HttpServletRequest request) {
        logUtil.logHistory(log, "/usuario/agregar", ActionsEnum.ADD,
                request.getRemoteAddr(), userForm.toString());

        if (result.hasErrors()) {
            return USER_CREATE;
        }
        
        log.debug("NOMBRE ---- " + userForm.getName() + " " + userForm.getFatherLastName() + " --- " + userForm.getBirthdate());

        
//
        User userExist = userService.findByUserName(userForm.getIdentifier());
//
        if (userExist != null) {
            result.addError(new ObjectError("exist",
                    "No se permite dar de alta dos usuarios con el mismo nombre"));
            return USER_CREATE;
        }
//
        User user = new User();
        UserDetails userDetails = new UserDetails();
        
        Profile perfil = new Profile(userForm.getProfileId());
        Group group = groupService.findOne(userForm.getGroupId());
        Neighborhood neighborhood = neighborhoodService.findOne(userForm.getNeighborhoodId());
//
        user.setUserName(userForm.getIdentifier());
        user.setProfile(perfil);
//
        if (userForm.getPassword() != null
                && !"".equals(userForm.getPassword())) {
            user.setPassword(encryptMD5.main(userForm.getPassword()));
        }
        
        String[] parts = userForm.getBirthdate().split("-");
        String bir = parts[2] + "-" + parts[1] + "-" + parts[0];
//
        user.setLastModified(new Date());
        user.setEnabled(1);
        user.setDeleted(0);
        userService.save(user);
//
        userDetails.setName(userForm.getName());
        userDetails.setLastName(userForm.getFatherLastName());
        userDetails.setLastNameMaternal(userForm.getMotherLastName());
        userDetails.setEmail(userForm.getEmail());
        userDetails.setBirthdate(userForm.getBirthdate());
        userDetails.setGender(userForm.getGender());
        userDetails.setCreated(new Date());
        userDetails.setLastModified(new Date());
        userDetails.setDeleted(0);
        userDetails.setUser(user);
        userDetails.setGroup(group);
        userDetails.setPhone(userForm.getPhone());
        userDetails.setActive(userForm.getActive());
        userDetails.setNeighborhood(neighborhood);
        userDetails.setStreet(userForm.getStreet());
        userDetails.setnExterior(userForm.getnExterior());
        userDetails.setnInside(userForm.getnInside());
        userDetailsService.save(userDetails);
//
        model.addAttribute("ESTATUS", "Los datos se guardaron correctamente.");
        UserForm userFormClean = new UserForm();
        model.addAttribute("userForm", userFormClean);
        return USER_CREATE;

    }

    @Transactional
    @PreAuthorize("hasRole('editUser')")
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editUserProcess(@Valid UserForm userForm,
            BindingResult result, Model model, HttpServletRequest request) {

        if (result.hasErrors()) {
            return USER_EDIT;
        }

        User userExist = userService.findByUserName(userForm.getIdentifier());

        if (userExist != null && userExist.getId() != userForm.getUserId()) {
            result.addError(new ObjectError("exist",
                    "No se permite dar de alta dos usuarios con el mismo nombre"));
            return USER_EDIT;
        }
        
        

        if (userForm.getUserDetailsId() == GeneralsEnum.SUPER_USER.getId()) {
            // if para evitar eliminar el super administrador
            result.addError(new ObjectError("exist",
                    "No se puede editar el Super Administrador"));
            return listUsers(model, new PageData(), request);
        }
        
        User user = new User();
        UserDetails userDetails = new UserDetails();

        userDetails = userDetailsService.findOne(userForm.getUserDetailsId());
        if(userDetails == null){
        	log.debug("NO TRAIGO NADA -------------------");
        }else{
        	log.debug("TRAIGO UN USER DETALLE ----------------- " + userDetails);
        }
        user = userDetails.getUser();

        Profile perfil = new Profile(userForm.getProfileId());
        Group group = groupService.findOne(userForm.getGroupId());
        Neighborhood neighborhood = neighborhoodService.findOne(userForm.getNeighborhoodId());
        
        user.setUserName(userForm.getIdentifier());
        user.setProfile(perfil);

        if (userForm.getPassword() != null
                && !"".equals(userForm.getPassword())) {
            user.setPassword(encryptMD5.main(userForm.getPassword()));
        }

        user.setLastModified(new Date());
        userService.save(user);

        userDetails.setName(userForm.getName());
        userDetails.setLastName(userForm.getFatherLastName());
        userDetails.setLastNameMaternal(userForm.getMotherLastName());
        userDetails.setEmail(userForm.getEmail());
        userDetails.setBirthdate(userForm.getBirthdate());
        userDetails.setGender(userForm.getGender());
        userDetails.setLastModified(new Date());
        userDetails.setUser(user);
        userDetails.setGroup(group);
        userDetails.setPhone(userForm.getPhone());
        userDetails.setActive(userForm.getActive());
        userDetails.setNeighborhood(neighborhood);
        userDetails.setStreet(userForm.getStreet());
        userDetails.setnExterior(userForm.getnExterior());
        userDetails.setnInside(userForm.getnInside());
        userDetailsService.save(userDetails);

        model.addAttribute("ESTATUS",
                "Los datos se actualizaron correctamente.");
        return USER_EDIT;

    }

    @Transactional
    @PreAuthorize("hasRole('deleteUser')")
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String deleteUserProcess(@RequestParam int idUserDetails,
            Model model, HttpServletRequest request) {
        logUtil.logHistory(log, "/usuario/eliminar", ActionsEnum.DELETE,
                request.getRemoteAddr(), String.valueOf(idUserDetails));

        if (idUserDetails == GeneralsEnum.SUPER_USER.getId()) {

            // if paar evitar eliminar el super administrador
            model.addAttribute("ESTATUS",
                    "¡No se puede Eliminar el Super Administrador!");
            return listUsers(model, new PageData(), request);
        }

        MyUserDetails userSession = (MyUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = userDetailsService.findOne(idUserDetails);

        if (userDetails == null) {
            model.addAttribute("ESTATUS",
                    "No ha sido posible eliminar el usuario");
        } else {

            if (userDetails.getUser().getUserName()
                    .equals(userSession.getUsername())) {
                model.addAttribute(
                        "ESTATUS",
                        "No es posible eliminar el usuario con el que ha iniciado sesión. "
                                + "\n Inicie sesión con otro usuario para eliminar el usuario seleccionado.");
                return listUsers(model, new PageData(), request);
            }

            User user = userDetails.getUser();
            user.setDeleted(1);

            userDetails.setDeleted(1);
            userService.save(user);
            userDetailsService.save(userDetails);

            model.addAttribute("ESTATUS", "El usuario "
                    + userDetails.getUser().getUserName()
                    + " se eliminó correctamente.");
        }
        return listUsers(model, new PageData(), request);
    }

    @PreAuthorize("hasRole('editUser')")
    @RequestMapping(value = "/{id}/actualizar", method = RequestMethod.POST)
    public String show(Model model, @PathVariable Integer id,
            HttpServletRequest request) {
        logUtil.logHistory(log, "/usuario/actualizar", ActionsEnum.UPDATE,
                request.getRemoteAddr(), String.valueOf(id));

        UserDetails userDetails = userDetailsService.findOne(id);

        if (userDetails != null) {

            if (id == GeneralsEnum.SUPER_USER.getId()) {
                // if paar evitar eliminar el super administrador
                model.addAttribute("ESTATUS",
                        "No se puede Editar el Super Administrador");
                return listUsers(model, new PageData(), request);
            }

            UserForm userForm = new UserForm(userDetails);
            model.addAttribute("userForm", userForm);
            return USER_EDIT;

        } else {
            return listUsers(model, new PageData(), request);
        }
    }
    
    @PreAuthorize("hasRole('addUser')")
	@RequestMapping(value = "/searchDistrict", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> SearchDistrict(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {

		Map<String, Object> output = new HashMap<String, Object>();
		int idState = Integer.parseInt(requestParams.get("newstate"));
		StatesOfMexico statesOfMexico = statesOfMexicoService.findOne(idState);
		List<District> listDistrict = districtService.findByStatesOfMexico(statesOfMexico);
		output.put("districts", listDistrict);

		return output;
	}
    
    
    @PreAuthorize("hasRole('addUser')")
	@RequestMapping(value = "/searchNeighborhood", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> searchNeighborhood(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {

		Map<String, Object> output = new HashMap<String, Object>();
		int idDistrict = Integer.parseInt(requestParams.get("newdistrict"));
		District district = districtService.findOne(idDistrict);
		List<Neighborhood> listNeighborhood = neighborhoodService.findByDistrict(district);
		output.put("neighborhoods", listNeighborhood);

		return output;
	}
    
    
    @ModelAttribute(value = "states")
    public List<StatesOfMexico> getStatesOfMexico() {
        return statesOfMexicoService.getAllStates();
    }
    

    @ModelAttribute(value = "profiles")
    public List<Profile> getPerfiles() {
        return perfilService.getAll();
    }
    
    @ModelAttribute(value = "groups")
    public List<Group> getGroups(){
    	return groupService.getAllGroups();
    }

    @Override
    Map<String, String> getActions() {
        return swithMapAcions(ModulesEnum.USERS);
    }

}
