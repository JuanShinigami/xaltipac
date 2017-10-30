package org.lldm.xaltipac.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Simple business object representing a Setting .
 *
 * @author Juan Mateo Sauce.
 */
@Entity
@Table(name = "settings")
public class Setting extends NamedEntity{
	
	@NotNull
	@Column(name = "value")
	private String value;
	
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", updatable = false) 
	private Date created;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastModified;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
}
