package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.data.model.User;

/**
 * 
 * @author Juan Mateo.
 *
 */

public interface UserService extends BaseService<User, Integer> {
	User findByUserName(String userName);
	List<User> getUsersByProfile(Profile profile);
}
