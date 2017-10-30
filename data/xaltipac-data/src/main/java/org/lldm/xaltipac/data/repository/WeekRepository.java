package org.lldm.xaltipac.data.repository;

import java.util.Date;
import java.util.List;

import org.lldm.xaltipac.data.model.Week;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * @author Juan Mateo.
 *
 */
public interface WeekRepository extends PagingAndSortingRepository<Week, Integer>{
	public Week findByDay(String day);
	@Query("FROM Week")
	public List<Week> getAllWeek();
}
