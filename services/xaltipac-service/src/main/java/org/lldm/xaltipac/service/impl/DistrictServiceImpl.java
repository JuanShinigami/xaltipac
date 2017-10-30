package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.District;
import org.lldm.xaltipac.data.model.StatesOfMexico;
import org.lldm.xaltipac.data.repository.DistrictRepository;
import org.lldm.xaltipac.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl extends BaseServiceImpl<District, Integer> implements DistrictService{

	@Autowired
	DistrictRepository districtRepository;
	
	@Override
	public void init() {
		repository = districtRepository;
	}
	
	@Override
	public List<District> getAllDistrict() {
		init();
		return districtRepository.getAllDistrict();
	}

	@Override
	public List<District> findByStatesOfMexico(StatesOfMexico statesOfMexico) {
		init();
		return districtRepository.findByStatesOfMexico(statesOfMexico);
	}
	

}
