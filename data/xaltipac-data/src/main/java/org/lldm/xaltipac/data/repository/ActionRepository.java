package org.lldm.xaltipac.data.repository;

import java.util.List;

import org.lldm.xaltipac.data.model.Action;
import org.lldm.xaltipac.data.model.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActionRepository extends PagingAndSortingRepository<Action, Integer> {
	List<Action> findByProfile(Profile profile);
}
