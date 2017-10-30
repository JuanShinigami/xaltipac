package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.District;
import org.lldm.xaltipac.data.model.Neighborhood;

public interface NeighborhoodService extends BaseService<Neighborhood, Integer> {

	public List<Neighborhood> getAllNeighborhood();
	List<Neighborhood> findByDistrict(District district);

}
