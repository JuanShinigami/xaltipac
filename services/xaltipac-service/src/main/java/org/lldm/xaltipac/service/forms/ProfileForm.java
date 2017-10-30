package org.lldm.xaltipac.service.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.lldm.xaltipac.data.model.Profile;
import org.lldm.xaltipac.data.model.Resource;
import org.lldm.xaltipac.service.dto.ActionResourceDTO;


/**
 * Clase From de Perfiles.
 * @author Juan Mateo.
 *
 */

public class ProfileForm implements Serializable {

    private static final long serialVersionUID = 6244337327344078236L;

    private Integer id;
    private List<ActionResourceDTO> actionResourceList;

    public ProfileForm() {
    }

    public ProfileForm(List<Resource> resourceList) {

        if (resourceList != null) {

            actionResourceList = new ArrayList<ActionResourceDTO>();

            for (Resource resource : resourceList) {
                actionResourceList.add(new ActionResourceDTO(resource, false));
            }
        }

    }

    public ProfileForm(List<Resource> allResources, Profile profile,
            List<Resource> userResources) {

        actionResourceList = new ArrayList<ActionResourceDTO>();

        for (Resource resource : allResources) {
            boolean exist = existResourceInList(resource, userResources);
            actionResourceList.add(new ActionResourceDTO(resource, exist));
        }
        
        this.id = profile.getId();
        this.name = profile.getName();

    }

    private boolean existResourceInList(Resource resource,
            List<Resource> userResources) {
        for (Resource resou : userResources) {
            if (resou.getId() == resource.getId()) {
                return true;
            }
        }
        return false;
    }

    @Pattern(regexp = "^[a-zA-ZÑñ\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00dc\u00fc\\s\\d]{2,16}", message = "El Nombre es incorrecto. Sólo se permiten letras, números, espacios en blanco y acentos. Debe tener al menos dos caracteres.")
    public String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActionResourceDTO> getActionResourceList() {
        return actionResourceList;
    }

    public void setActionResourceList(List<ActionResourceDTO> actionResourceList) {
        this.actionResourceList = actionResourceList;
    }

}
