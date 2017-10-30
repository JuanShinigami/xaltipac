package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.data.repository.ProfileRepository;
import org.lldm.xaltipac.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Clase Servicio de perfiles.
 * @author Juan Mateo.
 *
 */

@Service
public class ProfileServiceImpl extends BaseServiceImpl<Profile, Integer> implements ProfileService {

	@Autowired(required=true)
	private ProfileRepository profileRepository;
	
	@Override
	public void init() {
		repository = profileRepository;
		
	}
	
	
	@Override
	public List<Profile> getAll() {
		return profileRepository.getAll();
	}

	@Override
	public Profile findByName(String name) {
		return profileRepository.findByName(name);
	}
	
}
