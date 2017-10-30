package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.Action;
import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.service.dto.ActionResourceDTO;
import org.springframework.data.domain.Page;

/**
 * 
 * @author Juan Mateo Sauce
 *
 */
public interface ActionService extends BaseService<Action, Integer> {
	
	Page<Action> findAllPageable(int pageIndex, int pageSize);
	List<Action> findByProfile(Profile profile);
	void saveMany(Profile profile, List<ActionResourceDTO> actionResourcesList);
}
