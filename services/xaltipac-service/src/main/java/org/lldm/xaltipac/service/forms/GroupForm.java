package org.lldm.xaltipac.service.forms;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

/**
 * Clase From de Grupos.
 * 
 * @author Juan Mateo.
 *
 */

public class GroupForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Pattern(regexp = "^[a-zA-ZÑñ\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00dc\u00fc\\s\\d]{2,16}", message = "El Nombre es incorrecto. Sólo se permiten letras, números, espacios en blanco y acentos. Debe tener al menos dos caracteres.")
	public String name;

	public GroupForm() {
	}

	public GroupForm(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "GroupForm [id=" + id + ", name=" + name + "]";
	}

}
