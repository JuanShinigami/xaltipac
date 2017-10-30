package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.Group;
import org.lldm.xaltipac.data.model.User;
import org.lldm.xaltipac.data.model.UserDetails;
import org.lldm.xaltipac.service.forms.PageData;
import org.springframework.data.domain.Page;

/**
 * 
 * @author Juan Mateo
 *
 */

public interface UserDetailsService extends BaseService<UserDetails, Integer> {
	public Page<UserDetails> getAllActiveUsers(PageData pageData);
	public List<UserDetails> getAllUsersActive();
	public UserDetails findByUser(User user);

	public UserDetails findByEmail(String email);

	public List<UserDetails> findByGroup(Group group);
}
