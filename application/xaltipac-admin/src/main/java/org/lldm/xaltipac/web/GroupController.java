package org.lldm.xaltipac.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.lldm.xaltipac.data.model.Group;
import org.lldm.xaltipac.service.GroupService;
import org.lldm.xaltipac.service.forms.GroupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PreAuthorize("hasRole('showGroups')")
@RequestMapping(value = "/groups")
public class GroupController {

	private static final Logger LOG = Logger.getLogger(GroupController.class);

	@Autowired
	GroupService groupService;

	private final String GROUP_CREATE = "groups/createGroup";
	private final String GROUP_EDIT = "groups/editGroup";
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listGroups(Model model, HttpServletRequest request) {
//		List<Group> grupos = groupService.getAllGroups();
//		for (Group group : grupos) {
//			LOG.debug("GRUPOS ENCONTRADO CON EL NOMBRE DE  -------------- " + group.getName() + "-----------------");
//		}
		model.addAttribute("groupList", groupService.getAllGroups());
		return "groups/groupsList";
	}

	@PreAuthorize("hasRole('addGroup')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGroup(Model model, HttpServletRequest request) {

		model.addAttribute("groupForm", new GroupForm());
		return GROUP_CREATE;
	}
	
	@Transactional
	@PreAuthorize("hasRole('addGroup')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> addGroup(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) {
		
		String id = requestParams.get("id");
		String name = requestParams.get("name");
		boolean flagExist = false;
		boolean flagSave = false;
		boolean isEditable = false;
		Map<String, Object> output = new HashMap<String, Object>();
		if(id == ""){
			Group groupExist = groupService.findByName(name);
			if(groupExist != null){
				flagExist = true;
				
			}else{
				Group group = new Group(name, new Date(), new Date());
				groupService.save(group);
				flagSave = true;
			}
			LOG.debug("No tengo id ---------------");
		}else{
			Group groupExist = groupService.findByName(name);
			if(groupExist != null){
				flagExist = true;
				
			}else{
				int idGroup = Integer.parseInt(id);
				Group group = groupService.findOne(idGroup);
				group.setName(name);
				group.setLastModified(new Date());
				groupService.save(group);
				flagSave = true;
			}
			isEditable = true;
			
			LOG.debug("Tengo id ---------------");
		}
		
		output.put("isEditable", isEditable);
		output.put("flagSave", flagSave);
		output.put("flagExist", flagExist);

		return output;
	}

//	@Transactional
//	@PreAuthorize("hasRole('addGroup')")
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public String addGroupPost(@Valid GroupForm groupForm, BindingResult result, Model model,
//			HttpServletRequest request) {
//
//		if (result.hasErrors()) {
//			return GROUP_CREATE;
//		}
//		
//		LOG.debug("ENTRE AL METODO-------");
//
//		/**Group groupExist = groupService.findByName(groupForm.getName());
//
//		if (groupExist != null) {
//			result.addError(new ObjectError("exist", "No se permite dar de alta dos grupos con el mismo nombre"));
//			return GROUP_CREATE;
//		}
//
//		Group group = new Group(groupForm.getName(), new Date(), new Date());
//		groupService.save(group);
//
//		GroupForm groupFormClear = new GroupForm();
//		model.addAttribute("groupForm", groupFormClear);
//		model.addAttribute("ESTATUS", "Los datos se guardaron correctamente.");**/
//		return listGroups(model, request);
//	}

	@PreAuthorize("hasRole('editGroup')")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String editGroupId(Model model, @PathVariable Integer id, HttpServletRequest request) throws Exception {

		Group group = groupService.findOne(id);

		if (group != null) {
			model.addAttribute("groupForm", new GroupForm(group.getId(), group.getName()));
			return GROUP_EDIT;

		} else {
			return listGroups(model, request);
		}
	}

	@Transactional
	@PreAuthorize("hasRole('editGroup')")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editGroupPost(@Valid GroupForm groupForm, BindingResult result, Model model,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return GROUP_EDIT;
		}

		Group groupExist = groupService.findByName(groupForm.getName());

		if (groupExist != null && (groupExist.getId() != groupForm.getId())) {

			result.addError(
					new ObjectError("exist", "No es posible editar este grupo con este nombre por que ya existe."));
			return GROUP_EDIT;
		}

		Group group = groupService.findOne(groupForm.getId());
		group.setLastModified(new Date());
		group.setName(groupForm.getName());
		groupService.save(group);

		model.addAttribute("ESTATUS", "Los datos se guardaron correctamente.");
		return listGroups(model, request);

	}

	@Transactional
	@PreAuthorize("hasRole('deleteGroup')")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteResource(@RequestParam int idGroup, Model model, HttpServletRequest request) {

		Group group = groupService.findOne(idGroup);

		if (group == null) {
			model.addAttribute("ESTATUS", "Grupo no existente");
			return listGroups(model, request);
		}

		groupService.delete(group);
		model.addAttribute("ESTATUS", "El grupo " + group.getName() + " se eliminó correctamente.");

		return listGroups(model, request);
	}
}
