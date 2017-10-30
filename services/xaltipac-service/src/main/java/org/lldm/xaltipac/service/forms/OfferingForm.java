package org.lldm.xaltipac.service.forms;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

/**
 * 
 * @author Juan Mateo
 *
 */

public class OfferingForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	//@Pattern(regexp = "^[a-zA-ZÑñ\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00dc\u00fc\\s\\d]{2,60}", message = "El Nombre es incorrecto. Sólo se permiten letras, números, espacios en blanco y acentos. Debe tener al menos dos caracteres.")
	public String name;

	//@Pattern(regexp = "^[a-zA-ZÑñ\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00dc\u00fc\\s\\d]{2,500}", message = "El Nombre es incorrecto. Sólo se permiten letras, números, espacios en blanco y acentos. Debe tener al menos dos caracteres.")
	public String description;

	public OfferingForm() {
	}

	public OfferingForm(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "OfferingForm [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
