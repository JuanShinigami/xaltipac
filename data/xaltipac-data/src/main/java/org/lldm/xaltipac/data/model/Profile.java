package org.lldm.xaltipac.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Simple business object representing a Profile .
 *
 * @author Juan Mateo Sauce.
 */
@Entity
@Table(name = "profiles")
public class Profile extends NamedEntity implements Serializable {

	private static final long serialVersionUID = 8142777238748213619L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastModified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date created;

	public Profile() {
	}

	public Profile(int id) {
		this.id = id;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Perfil [name=" + getName() + "]";
	}

}
