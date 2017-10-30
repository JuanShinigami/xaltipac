package org.lldm.xaltipac.service.forms;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

/**
 * Clase From de los Recursos.
 * @author Juan Mateo.
 *
 */

public class ResourceForm implements Serializable {

    private static final long serialVersionUID = 348908812220671217L;

    private Integer id;

    @Pattern(regexp = "^[a-zA-ZÑñ\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00dc\u00fc\\s\\d]{2,16}", message = "El Nombre es incorrecto. Sólo se permiten letras, números, espacios en blanco y acentos. Debe tener al menos dos caracteres.")
    public String name;
    
    @Pattern(regexp = "^[a-zA-Z\\s\\d\\/]{2,30}", message = "La Ruta es incorrecta. Sólo se permiten letras(no Ñ's), números. Debe tener al menos dos caracteres.")
    public String path;

    public ResourceForm() {
    }

    public ResourceForm(Integer id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ResourceForm [name=" + name + ", path=" + path + "]";
    }
    
    

}
