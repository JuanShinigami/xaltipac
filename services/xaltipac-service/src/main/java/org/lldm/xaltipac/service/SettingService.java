package org.lldm.xaltipac.service;

import org.lldm.xaltipac.data.model.Setting;

/**
 * 
 * @author Juan Mateo.
 *
 */

public interface SettingService extends BaseService<Setting,Integer>{
	Setting findByName(String name);
}
