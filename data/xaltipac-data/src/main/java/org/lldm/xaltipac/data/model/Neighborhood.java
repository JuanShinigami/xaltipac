package org.lldm.xaltipac.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "neighborhood")
public class Neighborhood extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "colony")
	private String colony;
	
	@Column(name = "postal_code")
	private Integer postalCode;
	
	@ManyToOne
    @JoinColumn(name = "state_id")
	private StatesOfMexico statesOfMexico;
	
	@ManyToOne
    @JoinColumn(name = "district_id")
	private District district;
	
	public Neighborhood(){}
	
	public Neighborhood(String colony, Integer postalCode, StatesOfMexico statesOfMexico, District district){
		this.colony = colony;
		this.postalCode = postalCode;
		this.statesOfMexico = statesOfMexico;
		this.district = district;
	}
	
	public Neighborhood(Integer id, String colony, Integer postalCode, StatesOfMexico statesOfMexico, District district){
		setId(id);
		this.colony = colony;
		this.postalCode = postalCode;
		this.statesOfMexico = statesOfMexico;
		this.district = district;
	}

	public String getColony() {
		return colony;
	}

	public void setColony(String colony) {
		this.colony = colony;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public StatesOfMexico getStatesOfMexico() {
		return statesOfMexico;
	}

	public void setStateOfMexico(StatesOfMexico statesOfMexico) {
		this.statesOfMexico = statesOfMexico;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}
