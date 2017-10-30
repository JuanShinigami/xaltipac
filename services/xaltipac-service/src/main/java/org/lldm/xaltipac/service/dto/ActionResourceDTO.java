package org.lldm.xaltipac.service.dto;

import java.io.Serializable;

import org.lldm.xaltipac.data.model.Resource;


/**
 * Clase DTO para 1 perfil a "n" recursos.
 * @author Juan Mateo.
 *
 */

public class ActionResourceDTO implements Serializable{

    private static final long serialVersionUID = 1858081141480079019L;
  

    private Resource resource;
    private boolean active;
    
    public ActionResourceDTO() {
    }
    
    public ActionResourceDTO(Resource resource, boolean active){
        this.resource = resource;
        this.active = active;
    }
    
    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
