package org.lldm.xaltipac.service.impl;

import org.lldm.xaltipac.data.model.Setting;
import org.lldm.xaltipac.data.repository.SettingRepository;
import org.lldm.xaltipac.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase servicio para configuracion general.
 * @author Juan Mateo.
 *
 */

@Service
public class SettingServiceImpl extends BaseServiceImpl<Setting, Integer>implements SettingService{

	@Autowired
	SettingRepository settingRepository;
	
	@Override
	public Setting findByName(String name) {
		return settingRepository.findByName(name);
	}

	@Override
	public void init() {
		repository = settingRepository;
	}
	
}
