/**
 * 
 */
package org.lldm.xaltipac.service.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.lldm.xaltipac.data.model.Offering;
import org.lldm.xaltipac.data.model.Week;
import org.lldm.xaltipac.service.dto.OfferingDetailsOfferingDTO;

/**
 * @author Juan Mateo Sauce
 *
 */
public class WeekForm {
	
	private Integer id;
	@NotNull(message = "Debe de ingresar una fecha.")
	private String day;
	private String notes;
	private List<OfferingDetailsOfferingDTO> offeringDetilsOfferingList;
	
	public WeekForm(){}
	
	public WeekForm(List<Offering> offeringList){
		if(offeringList != null){
			offeringDetilsOfferingList = new ArrayList<OfferingDetailsOfferingDTO>();
			for (Offering offering : offeringList) {
				offeringDetilsOfferingList.add(new OfferingDetailsOfferingDTO(offering, false));
			}
		}
	}
	
	public WeekForm(List<Offering> allOfferings, Week week, List<Offering> offeringVerify){
		offeringDetilsOfferingList = new ArrayList<OfferingDetailsOfferingDTO>();
		for (Offering offering : allOfferings) {
			boolean exist = existOfferingInList(offering, offeringVerify);
			offeringDetilsOfferingList.add(new OfferingDetailsOfferingDTO(offering, exist));
		}
		
		this.id = week.getId();
		this.day = week.getDay();
		this.notes = week.getNotes();
	}
	
	private boolean existOfferingInList(Offering offering, List<Offering> offeringVerify){
		for (Offering offeringV : offeringVerify) {
			if(offeringV.getId() == offering.getId()){
				return true;
			}
		}
		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<OfferingDetailsOfferingDTO> getOfferingDetilsOfferingList() {
		return offeringDetilsOfferingList;
	}

	public void setOfferingDetilsOfferingList(List<OfferingDetailsOfferingDTO> offeringDetilsOfferingList) {
		this.offeringDetilsOfferingList = offeringDetilsOfferingList;
	}

}
