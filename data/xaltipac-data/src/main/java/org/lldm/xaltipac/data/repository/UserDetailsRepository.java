package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.Group;
import org.lldm.xaltipac.data.model.User;
import org.lldm.xaltipac.data.model.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserDetailsRepository extends PagingAndSortingRepository<UserDetails, Integer> {
	
	@Query("FROM UserDetails u WHERE u.deleted = 0")
	public Page<UserDetails> getAllActiveUsers(Pageable pageable);
	
	@Query("FROM UserDetails u WHERE u.deleted = 0")
	public List<UserDetails> getAllUsersActive();
	
	public UserDetails findByUser(User user);
	
	public UserDetails findByEmail(String email);
	
	public List<UserDetails> findByGroup(Group group);
	
	@Query("FROM UserDetails u WHERE u.deleted = 0 "
			+ "AND (u.name LIKE %:filter% "
			+ "OR u.user.userName LIKE %:filter% "
			+ "OR u.user.profile.name LIKE %:filter% "
			+ "OR ( case when day(u.user.lastModified)<10 then '0' else '' end || day(u.user.lastModified) || case when month(u.user.lastModified)<10 then '/0' else '/' end || month(u.user.lastModified) || '/' || year(u.user.lastModified) ) LIKE %:filter% )")
	public Page<UserDetails> getAllActiveUsers(@Param("filter") String filter, Pageable pageable );
}
