package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.District;
import org.lldm.xaltipac.data.model.Neighborhood;
import org.lldm.xaltipac.data.repository.NeighborhoodRepository;
import org.lldm.xaltipac.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeighborhoodServiceImpl extends BaseServiceImpl<Neighborhood, Integer> implements NeighborhoodService{
	
	@Autowired
	NeighborhoodRepository neighborhoodRepository;

	@Override
	public void init() {
		repository = neighborhoodRepository;
	}
	
	@Override
	public List<Neighborhood> getAllNeighborhood() {
		init();
		return neighborhoodRepository.getAllNeighborhood();
	}

	@Override
	public List<Neighborhood> findByDistrict(District district) {
		init();
		return neighborhoodRepository.findByDistrict(district);
	}
	
}
