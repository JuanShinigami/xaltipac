package org.lldm.xaltipac.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Juan Mateo.
 *
 */

@Entity
@Table(name = "offering_details")
public class OfferingDetails extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "quantity")
	private Double quantity;

	@ManyToOne
	@JoinColumn(name = "id_offering")
	private Offering offering;

	@ManyToOne
	@JoinColumn(name = "id_week")
	private Week week;

	@ManyToOne
	@JoinColumn(name = "id_user_detail")
	private UserDetails userDetails;

	public OfferingDetails() {
	}

	public OfferingDetails(Double quantity, Offering offering, Week week, UserDetails userDetails) {
		this.quantity = quantity;
		this.offering = offering;
		this.week = week;
		this.userDetails = userDetails;
	}

	public OfferingDetails(Integer id, Double quantity, Offering offering, Week week, UserDetails userDetails) {
		setId(id);
		this.quantity = quantity;
		this.offering = offering;
		this.week = week;
		this.userDetails = userDetails;
	}

	public Offering getOffering() {
		return offering;
	}

	public void setOffering(Offering offering) {
		this.offering = offering;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "OfferingDetails [quantity=" + quantity + ", offering=" + offering + ", week=" + week + ", userDetails="
				+ userDetails + "]";
	}

}
