package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.StatesOfMexico;

public interface StatesOfMexicoService extends BaseService<StatesOfMexico, Integer>{

	public List<StatesOfMexico> getAllStates();
	
}
