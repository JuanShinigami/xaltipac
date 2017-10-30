package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.District;
import org.lldm.xaltipac.data.model.StatesOfMexico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DistrictRepository extends PagingAndSortingRepository<District, Integer>{
	
	@Query("FROM District")
	List<District> getAllDistrict();
	
	List<District> findByStatesOfMexico(StatesOfMexico statesOfMexico);
	
}
