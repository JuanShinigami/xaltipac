package org.lldm.xaltipac.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Simple business object representing a UserdeTail.
 *
 * @author Juan Mateo Sauce.
 */
@Entity
@Table(name = "users_detail")
public class UserDetails extends NamedEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "lastname_maternal")
	private String lastNameMaternal;

	@Column(name = "email")
	private String email;

	@Column(name = "birthdate")
	private String birthdate;

	@Column(name = "gender")
	private String gender;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastModified;
	
	@Column(name = "deleted")
	private int deleted;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_group")
	private Group group;

	@Column(name = "phone")
	private String phone;

	@Column(name = "active")
	private Integer active;

	@ManyToOne
	@JoinColumn(name = "id_neighborhood")
	private Neighborhood neighborhood;
	
	@Column(name = "street")
	private String street;

	@Column(name = "n_inside")
	private Integer nInside;
	
	@Column(name = "n_exterior")
	private Integer nExterior;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNameMaternal() {
		return lastNameMaternal;
	}

	public void setLastNameMaternal(String lastNameMaternal) {
		this.lastNameMaternal = lastNameMaternal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getnInside() {
		return nInside;
	}

	public void setnInside(Integer nInside) {
		this.nInside = nInside;
	}

	public Integer getnExterior() {
		return nExterior;
	}

	public void setnExterior(Integer nExterior) {
		this.nExterior = nExterior;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}

	@Override
	public String toString() {
		return "UserDetails [lastName=" + lastName + ", lastNameMaternal=" + lastNameMaternal + ", email=" + email
				+ ", birthdate=" + birthdate + ", gender=" + gender + ", created=" + created + ", lastModified="
				+ lastModified + ", deleted=" + deleted + ", user=" + user + ", group=" + group + ", phone=" + phone
				+ ", active=" + active + ", neighborhood=" + neighborhood + ", street=" + street + ", nInside="
				+ nInside + ", nExterior=" + nExterior + "]";
	}

}
