package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.data.model.Offering;
import org.lldm.xaltipac.data.model.OfferingDetails;
import org.lldm.xaltipac.data.model.UserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface OfferingDetailsRepository extends PagingAndSortingRepository<OfferingDetails, Integer>{
	List<OfferingDetails> findByOffering(Offering offering);
	List<OfferingDetails> findByWeek(Week day);
	List<OfferingDetails> findByUserDetails(UserDetails userDetails);
	List<OfferingDetails> findByUserDetailsAndWeek(UserDetails userDetails, Week week);
	List<OfferingDetails> findByOfferingAndWeek(Offering offering, Week day);
	
//	@Query("FROM OfferingDetails of WHERE of.offering = :offering AND of.day = :day AND of.userDetail = :userDetail")
//	List<OfferingDetails> getOfferingDetailByUserOfferingDay(@Param("offering")Offering offering, @Param("day")Days day, @Param("userDetail")UserDetails userDetail);
}
