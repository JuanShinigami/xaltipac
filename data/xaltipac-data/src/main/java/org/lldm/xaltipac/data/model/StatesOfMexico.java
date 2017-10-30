package org.lldm.xaltipac.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "states_of_mexico")
public class StatesOfMexico extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "state")
	private String state;
	
	public StatesOfMexico(String state){
		this.state = state;
	}
	
	public StatesOfMexico(){}
	
	public StatesOfMexico(Integer id, String state){
		setId(id);
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
