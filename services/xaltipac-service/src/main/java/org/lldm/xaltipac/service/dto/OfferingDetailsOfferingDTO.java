/**
 * 
 */
package org.lldm.xaltipac.service.dto;

import java.io.Serializable;

import org.lldm.xaltipac.data.model.Offering;

/**
 * @author Juan Mateo Sauce
 *
 */
public class OfferingDetailsOfferingDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Offering offering;
	private boolean active;
	
	public OfferingDetailsOfferingDTO(){}
	
	public OfferingDetailsOfferingDTO(Offering offering, boolean active){
		this.offering = offering;
		this.active = active;
	}

	public Offering getOffering() {
		return offering;
	}

	public void setOffering(Offering offering) {
		this.offering = offering;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
