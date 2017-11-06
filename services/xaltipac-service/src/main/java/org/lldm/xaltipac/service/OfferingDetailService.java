package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.Offering;
import org.lldm.xaltipac.data.model.OfferingDetails;
import org.lldm.xaltipac.data.model.UserDetails;
import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.service.dto.OfferingDetailsOfferingDTO;

/**
 * 
 * @author Juan Mateo.
 *
 */

public interface OfferingDetailService extends BaseService<OfferingDetails, Integer>{
	List<OfferingDetails> findByOffering(Offering offering);
	List<OfferingDetails> findByWeek(Week day);
	List<OfferingDetails> findByUserDetails(UserDetails userDetails);
	List<OfferingDetails> findByUserDetailsAndWeek(UserDetails userDetails, Week week);
	List<OfferingDetails> findByOfferingAndWeek(Offering offering, Week day);
	List<OfferingDetails> searchByWeek(Week week);
	Double getTotalOffering(Week week);
	void saveManyOfferginDetails(Week week, List<OfferingDetailsOfferingDTO> offeringDetailsOfferingList);
}
