package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * @author Juan Mateo.
 *
 */

public interface GroupRepository extends PagingAndSortingRepository<Group, Integer> {

	public Group findByName(String name);

	@Query("FROM Group")
	List<Group> getAllGroups();
}
