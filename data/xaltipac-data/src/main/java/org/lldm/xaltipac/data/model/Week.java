package org.lldm.xaltipac.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Juan Mateo.
 *
 */

@Entity
@Table(name = "week")
public class Week extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "day")
	private String day;

	@Column(name = "total")
	private Double total;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "enabled")
	private Integer enabled;

	public Week() {

	}

	public Week(Integer id, String day, Double total, String notes, Integer enabled) {
		setId(id);
		this.day = day;
		this.total = total;
		this.notes = notes;
		this.enabled = enabled;
	}

	public Week(String day, Double total, String notes, Integer enabled) {
		this.day = day;
		this.total = total;
		this.notes = notes;
		this.enabled = enabled;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Week [day=" + day + ", total=" + total + ", notes=" + notes + ", enabled=" + enabled + "]";
	}

}
