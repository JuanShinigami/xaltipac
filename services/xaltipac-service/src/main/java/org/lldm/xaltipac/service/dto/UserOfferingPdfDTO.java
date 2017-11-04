/**
 * 
 */
package org.lldm.xaltipac.service.dto;

import java.util.List;

/**
 * @author Juan Mateo Sauce
 *
 */
public class UserOfferingPdfDTO {
	
	private String name;
	
	private List<Double> quantity;
	
	public UserOfferingPdfDTO(){}
	
	public UserOfferingPdfDTO(String name, List<Double> quantity){
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<Double> quantity) {
		this.quantity = quantity;
	}

}
