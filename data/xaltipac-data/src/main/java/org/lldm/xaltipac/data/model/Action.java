package org.lldm.xaltipac.data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Simple business object representing a Action.
 *
 * @author Juan Mateo Sauce.
 */

@Entity
@Table(name = "actions")
public class Action extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6869366248297417606L;

    @ManyToOne
    @JoinColumn(name = "id_profile")
    private Profile profile;
    
    @ManyToOne
    @JoinColumn(name = "id_resource")
    private Resource resource;
    
    public Action() {
    
    }

    public Action(Profile profile, Resource resource) {
        this.profile = profile;
        this.resource = resource;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
