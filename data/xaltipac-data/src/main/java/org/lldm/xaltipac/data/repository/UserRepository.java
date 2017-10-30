package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.data.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	User findByUserName(String userName);
	
	@Query("FROM User u WHERE u.deleted = 0 AND profile = :profile")
	List<User> getUsersByProfile(@Param("profile")Profile profile);
}
