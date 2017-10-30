package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.data.model.User;
import org.lldm.xaltipac.data.repository.UserRepository;
import org.lldm.xaltipac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase servicio para ususario.
 * @author Juan Mateo.
 *
 */

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void init() {
		repository = userRepository;
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> getUsersByProfile(Profile profile) {
		return userRepository.getUsersByProfile(profile);
	}

	

}
