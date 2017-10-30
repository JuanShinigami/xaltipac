package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.StatesOfMexico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatesOfMexicoRepository extends PagingAndSortingRepository<StatesOfMexico, Integer>{
	
	@Query("FROM StatesOfMexico")
	public List<StatesOfMexico> getAllStates();
}
