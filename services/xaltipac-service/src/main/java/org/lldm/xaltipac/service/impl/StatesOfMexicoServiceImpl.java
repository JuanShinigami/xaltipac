package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.StatesOfMexico;
import org.lldm.xaltipac.data.repository.StatesOfMexicoRepository;
import org.lldm.xaltipac.service.StatesOfMexicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatesOfMexicoServiceImpl extends BaseServiceImpl<StatesOfMexico, Integer> implements StatesOfMexicoService{

	@Autowired
	StatesOfMexicoRepository stateOfMexicoRepository;
	
	@Override
	public void init() {
		repository = stateOfMexicoRepository;
	}
	
	@Override
	public List<StatesOfMexico> getAllStates() {
		init();
		return stateOfMexicoRepository.getAllStates();
	}

}
