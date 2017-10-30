package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.data.model.Offering;
import org.lldm.xaltipac.data.model.OfferingDetails;
import org.lldm.xaltipac.data.model.UserDetails;
import org.lldm.xaltipac.data.repository.OfferingDetailsRepository;
import org.lldm.xaltipac.service.OfferingDetailService;
import org.lldm.xaltipac.service.UserDetailsService;
import org.lldm.xaltipac.service.dto.OfferingDetailsOfferingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Juan Mateo.
 *
 */
@Service
public class OfferingDetailServiceImpl extends BaseServiceImpl<OfferingDetails, Integer>
		implements OfferingDetailService {

	@Autowired
	OfferingDetailsRepository offeringDetailsRepository;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public List<OfferingDetails> findByOffering(Offering offering) {
		return offeringDetailsRepository.findByOffering(offering);
	}

	@Override
	public List<OfferingDetails> findByWeek(Week day) {
		return offeringDetailsRepository.findByWeek(day);
	}

	@Override
	public List<OfferingDetails> findByUserDetails(UserDetails userDetails) {
		return offeringDetailsRepository.findByUserDetails(userDetails);
	}

	@Override
	public List<OfferingDetails> findByOfferingAndWeek(Offering offering, Week day) {
		return offeringDetailsRepository.findByOfferingAndWeek(offering, day);
	}

	@Override
	public void init() {
		repository = offeringDetailsRepository;
	}

	@Override
	@Transactional
	public void saveManyOfferginDetails(Week week, List<OfferingDetailsOfferingDTO> offeringDetailsOfferingList) {
		delete(findByWeek(week));
		List<UserDetails> userDetails = userDetailsService.getAllUsersActive();
		for (OfferingDetailsOfferingDTO offeringDetails : offeringDetailsOfferingList) {
			
			if(offeringDetails.isActive()){
				for (UserDetails userDetail : userDetails) {
					save(new OfferingDetails(0.0, offeringDetails.getOffering(), week, userDetail));
				}
			}
		}
	}

	@Override
	public List<OfferingDetails> findByUserDetailsAndWeek(UserDetails userDetails, Week week) {
		return offeringDetailsRepository.findByUserDetailsAndWeek(userDetails, week);
	}

}
