package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.Group;

/**
 * 
 * @author Juan Mateo.
 *
 */

public interface GroupService extends BaseService<Group, Integer> {
	public Group findByName(String name);
	public List<Group> getAllGroups();
}
