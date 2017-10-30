package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.Action;
import org.lldm.xaltipac.data.model.Resource;

/**
 * 
 * @author Juan Mateo Sauce
 *
 */

public interface ResourceService extends BaseService<Resource, Integer> {
    List<Resource> getAllResources();
    Resource findByName(String name);
    List<Resource> getResourcesToActions(List<Action> actions);
}
