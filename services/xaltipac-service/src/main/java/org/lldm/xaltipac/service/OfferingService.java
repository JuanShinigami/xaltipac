package org.lldm.xaltipac.service;

import java.util.List;

import org.lldm.xaltipac.data.model.Offering;

/**
 * 
 * @author Juan Mateo.
 *
 */

public interface OfferingService extends BaseService<Offering, Integer>{
	public Offering findByName(String name);
	public List<Offering> getAllOfferings();
}
