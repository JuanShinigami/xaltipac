package org.lldm.xaltipac.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Juan Mateo.
 *
 */

@Entity
@Table(name = "groups")
public class Group extends NamedEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastModified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date created;

	public Group() {

	}
	
	public Group(String name, Date lastModified, Date created){
		setName(name);
		this.lastModified = lastModified;
		this.created = created;
	}

	public Group(Integer id, String name, Date lastModified, Date created) {
		setId(id);
		setName(name);
		this.lastModified = lastModified;
		this.created = created;
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
		return "Group [lastModified=" + lastModified + ", created=" + created + "]";
	}

}
