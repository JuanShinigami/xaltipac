package org.lldm.xaltipac.service.impl;

import java.util.Date;
import java.util.List;

import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.data.repository.WeekRepository;
import org.lldm.xaltipac.service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Juan Mateo.
 *
 */
@Service
public class WeekServiceImpl extends BaseServiceImpl<Week, Integer> implements WeekService {

	@Autowired
	WeekRepository weekRepository;

	@Override
	public Week findByDay(String day) {
		return weekRepository.findByDay(day);
	}

	@Override
	public void init() {
		repository = weekRepository;

	}

	@Override
	public List<Week> getAllWeek() {
		return weekRepository.getAllWeek();
	}

}
