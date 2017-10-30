package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.Group;
import org.lldm.xaltipac.data.repository.GroupRepository;
import org.lldm.xaltipac.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Juan Mateo.
 *
 */
@Service
public class GroupServiceImpl extends BaseServiceImpl<Group, Integer> implements GroupService {

	@Autowired
	GroupRepository groupRepository;

	@Override
	public Group findByName(String name) {
		return groupRepository.findByName(name);
	}

	@Override
	public List<Group> getAllGroups() {
		return groupRepository.getAllGroups();
	}
	
	@Override
	public void init() {
		repository = groupRepository;
	}

	

}
