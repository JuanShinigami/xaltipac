package org.lldm.xaltipac.data.repository;

import org.lldm.xaltipac.data.model.Setting;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SettingRepository extends PagingAndSortingRepository<Setting, Integer>{
	Setting findByName(String name);
}
