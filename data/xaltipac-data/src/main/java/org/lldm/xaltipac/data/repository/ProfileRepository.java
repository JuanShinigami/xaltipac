package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Integer> {

	@Query("FROM Profile")
	public Page<Profile> getAllActiveProfiles(Pageable pageable);
	
	@Query("FROM Profile")
	public List<Profile> getAll();
	
	@Query("FROM Profile p WHERE p.id != 1 AND p.id != 1")
	public List<Profile> getProfileForEncargado();
	
	@Query("FROM Profile p WHERE p.id = 2")
	public List<Profile> getProfileForFinanzas();
	
	public Profile findByName(String name);
}
