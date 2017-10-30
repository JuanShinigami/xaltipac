package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.Offering;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * @author Juan Mateo.
 *
 */

public interface OfferingRepository extends PagingAndSortingRepository<Offering, Integer>{
	public Offering findByName(String name);
	@Query("FROM Offering")
	public List<Offering> getAllOfferings();
}
