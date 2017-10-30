package org.lldm.xaltipac.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.lldm.xaltipac.data.model.Action;
import org.lldm.xaltipac.data.model.Resource;
import org.lldm.xaltipac.data.repository.ResourceRepository;
import org.lldm.xaltipac.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase servicio para los recursos.
 * @author Juan Mateo.
 *
 */

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Integer> implements ResourceService{
	
	@Autowired
	ResourceRepository resourceRepository;
	
	@Override
	public void init() {
		repository = resourceRepository;
	}

    @Override
    public List<Resource> getAllResources() {
        init();
        return resourceRepository.getAllResources();
    }

    @Override
    public Resource findByName(String name) {
        init();
        return resourceRepository.findByName(name);
    }

    @Override
    public List<Resource> getResourcesToActions(List<Action> actions) {
        List<Resource> resources = new ArrayList<Resource>();
        for(Action action : actions){
            resources.add(action.getResource());
        }
        return resources;
    }

	
	

}
