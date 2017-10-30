package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.Group;
import org.lldm.xaltipac.data.model.User;
import org.lldm.xaltipac.data.model.UserDetails;
import org.lldm.xaltipac.data.repository.UserDetailsRepository;
import org.lldm.xaltipac.service.UserDetailsService;
import org.lldm.xaltipac.service.forms.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Clase servicio para usuario detalle.
 * @author Juan Mateo Sauce.
 *
 */

@Service
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetails, Integer> implements UserDetailsService{
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Override
	public void init() {
		repository = userDetailsRepository;
	}

	@Override
	public Page<UserDetails> getAllActiveUsers(PageData pageData) {
		PageRequest pageRequest = new PageRequest(pageData.getPage(), pageData.getSize(), Sort.Direction.ASC, "id");
		
		if(pageData.isPageFiltered())
			return userDetailsRepository.getAllActiveUsers(pageData.getFilter(),pageRequest);
		return userDetailsRepository.getAllActiveUsers(pageRequest);
		
	}

	@Override
	public UserDetails findByUser(User user) {
		return userDetailsRepository.findByUser(user);
	}

	@Override
	public UserDetails findByEmail(String email) {
		return userDetailsRepository.findByEmail(email);
	}

	@Override
	public List<UserDetails> findByGroup(Group group) {
		return userDetailsRepository.findByGroup(group);
	}

	@Override
	public List<UserDetails> getAllUsersActive() {
		return userDetailsRepository.getAllUsersActive();
	}

	

}
