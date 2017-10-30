package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.Profile;

/**
 * 
 * @author Juan Mateo Sauce
 *
 */
public interface ProfileService extends BaseService<Profile, Integer> {
	
	public List<Profile> getAll();
	public Profile findByName(String name);
	
}
