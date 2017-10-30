package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.Offering;
import org.lldm.xaltipac.data.repository.OfferingRepository;
import org.lldm.xaltipac.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Juan Mateo.
 *
 */
@Service
public class OfferingServiceImpl extends BaseServiceImpl<Offering, Integer> implements OfferingService {

	@Autowired
	OfferingRepository offeringRepository;

	@Override
	public Offering findByName(String name) {
		return offeringRepository.findByName(name);
	}

	@Override
	public void init() {
		repository = offeringRepository;
	}

	@Override
	public List<Offering> getAllOfferings() {
		return offeringRepository.getAllOfferings();
	}

}
