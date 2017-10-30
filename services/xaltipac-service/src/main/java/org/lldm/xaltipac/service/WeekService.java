package org.lldm.xaltipac.service;

import java.util.Date;
import java.util.List;

import org.lldm.xaltipac.data.model.Week;

/**
 * 
 * @author Juan Mateo.
 *
 */

public interface WeekService extends BaseService<Week, Integer>{
	public Week findByDay(String day);
	public List<Week> getAllWeek();
}
