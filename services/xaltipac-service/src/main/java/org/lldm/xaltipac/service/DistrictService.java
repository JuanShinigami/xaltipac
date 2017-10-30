package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.District;
import org.lldm.xaltipac.data.model.StatesOfMexico;

public interface DistrictService extends BaseService<District, Integer> {

	public List<District> getAllDistrict();
	List<District> findByStatesOfMexico(StatesOfMexico statesOfMexico);

}
