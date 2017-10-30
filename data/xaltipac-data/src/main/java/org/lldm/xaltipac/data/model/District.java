package org.lldm.xaltipac.data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class District extends NamedEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name = "state_id")
	private StatesOfMexico statesOfMexico;
	
	public District(){}
	
	public District(String name, StatesOfMexico statesOfMexico){
		setName(name);
		this.statesOfMexico = statesOfMexico;
	}
	
	public District(Integer id, String name, StatesOfMexico statesOfMexico){
		setId(id);
		setName(name);
		this.statesOfMexico = statesOfMexico;
	}

	public StatesOfMexico getStatesOfMexico() {
		return statesOfMexico;
	}

	public void setStateOfMexico(StatesOfMexico statesOfMexico) {
		this.statesOfMexico = statesOfMexico;
	}
	
}
