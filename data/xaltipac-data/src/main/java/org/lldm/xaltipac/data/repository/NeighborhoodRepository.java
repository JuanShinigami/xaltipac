package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.District;
import org.lldm.xaltipac.data.model.Neighborhood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NeighborhoodRepository extends PagingAndSortingRepository<Neighborhood, Integer>{
	
	@Query("FROM Neighborhood")
	public List<Neighborhood> getAllNeighborhood();
	
	List<Neighborhood> findByDistrict(District district);
}
