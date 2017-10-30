package org.lldm.xaltipac.service.impl;

import java.util.List;

import org.lldm.xaltipac.data.model.Action;
import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.data.repository.ActionRepository;
import org.lldm.xaltipac.service.ActionService;
import org.lldm.xaltipac.service.dto.ActionResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ActionServiceImpl extends BaseServiceImpl<Action, Integer>
        implements ActionService {

    @Autowired(required = true)
    private ActionRepository profileActionRepository;

    @Override
    public void init() {
        repository = profileActionRepository;

    }

    public Page<Action> findAllPageable(int pageIndex, int pageSize) {
        PageRequest request = new PageRequest(pageIndex, pageSize,
                Sort.Direction.ASC, "id");
        return findAll(request);
    }

    @Override
    public List<Action> findByProfile(Profile profile) {
        return profileActionRepository.findByProfile(profile);
    }

    @Override
    @Transactional
    public void saveMany(Profile profile,
            List<ActionResourceDTO> actionResourcesList) {

        delete(findByProfile(profile));

        for (ActionResourceDTO actions : actionResourcesList) {
            if (actions.isActive()) {
                save(new Action(profile, actions.getResource()));
            }
        }

    }

}
