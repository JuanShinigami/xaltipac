package org.lldm.xaltipac.service.forms;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.lldm.xaltipac.data.model.UserDetails;

/**
 * Clase From de Usuario.
 * 
 * @author Juan Mateo.
 *
 */

public class UserForm implements Serializable {
	private static final long serialVersionUID = 6244337327344078236L;

	private Integer userDetailsId;
	private Integer userId;

	@Pattern(regexp = "^[a-zA-Z\\d_]{4,16}", message = "El Identificador es incorrecto. Sólo se permiten letras y números. Debe tener al menos 4 caracteres.")
	public String identifier;

	@Pattern(regexp = "^[a-zA-ZÑñ\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00dc\u00fc\\s]{1,55}$", message = "El Nombre es incorrecto. Sólo se permiten letras, espacios en blanco y acentos.")
	public String name;

	@Pattern(regexp = "^[a-zA-ZÑñ\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00dc\u00fc\\s]{1,75}$", message = "El apellido paterno es incorrecto. Sólo se permiten letras, espacios en blanco y acentos.")
	public String fatherLastName;

	@Pattern(regexp = "^[a-zA-ZÑñ\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00dc\u00fc\\s]{0,75}$", message = "El apellido materno es incorrecto. Sólo se permiten letras, espacios en blanco y acentos.")
	public String motherLastName;

	//Se comentó por que puede ser nulo
	//@Pattern(regexp = "[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}", message = "El Correo electrónico es incorrecto. Sólo se permiten letras, números, guion medio, guion bajo y punto. (ej. micorreo@dominio.com)")
	public String email;

	@Pattern(regexp = "^((?=.*[0-9])(?=.*[a-zA-Z]{3})(\\w|[!@#$%&?_;,\\-\\+\\*\"\\.]){4,20})*$", message = "La Contraseña es incorrecta. Debe tener al menos 3 caracteres y un número.")
	public String password;

	@NotNull(message = "Debe seleccionar un perfil")
	public Integer profileId;

	@NotNull(message = "Debe de colocar la fecha de nacimiento")
	public String birthdate;

	@NotNull(message = "Debe seleccionar un genero")
	public String gender;

	@NotNull(message = "Debe seleccionar un grupo")
	public Integer groupId;
	
	public String phone;
	
	@NotNull(message = "Debe indicar si es activo")
	public Integer active;
	
	//@NotNull(message = "Debe seleccionar una colonia.")
	public Integer neighborhoodId;
	
	//@NotNull(message = "Debe selecionar un estado")
	public Integer statesOfMexicoId;

	//@NotNull(message = "Debe selecionar un municipio")
	public Integer districtId;
	
	@NotNull(message = "Debes ingresar la calle.")
	public String street;
	
	public Integer nInside;
	
	public Integer nExterior;

	public UserForm() {
	}

	public UserForm(UserDetails userDetails) {
		this.userId = userDetails.getUser().getId();
		this.userDetailsId = userDetails.getId();
		this.identifier = userDetails.getUser().getUserName();
		this.name = userDetails.getName();
		this.fatherLastName = userDetails.getLastName();
		this.motherLastName = userDetails.getLastNameMaternal();
		this.email = userDetails.getEmail();
		this.password = userDetails.getUser().getPassword();
		this.profileId = userDetails.getUser().getProfile().getId();
		this.birthdate = userDetails.getBirthdate();
		this.gender = userDetails.getGender();
		this.groupId = userDetails.getGroup().getId();
		this.phone = userDetails.getPhone();
		this.active = userDetails.getActive();
		this.neighborhoodId = userDetails.getNeighborhood().getId();
		this.districtId = userDetails.getNeighborhood().getDistrict().getId();
		this.statesOfMexicoId = userDetails.getNeighborhood().getStatesOfMexico().getId();
		this.street = userDetails.getStreet();
		this.nInside = userDetails.getnInside();
		this.nExterior = userDetails.getnExterior();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(Integer neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public Integer getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Integer userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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

	public Integer getStatesOfMexicoId() {
		return statesOfMexicoId;
	}

	public void setStatesOfMexicoId(Integer statesOfMexicoId) {
		this.statesOfMexicoId = statesOfMexicoId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	@Override
	public String toString() {
		return "UserForm [userDetailsId=" + userDetailsId + ", userId=" + userId + ", identifier=" + identifier
				+ ", name=" + name + ", fatherLastName=" + fatherLastName + ", motherLastName=" + motherLastName
				+ ", email=" + email + ", password=" + password + ", profileId=" + profileId + ", birthdate="
				+ birthdate + ", gender=" + gender + ", groupId=" + groupId + ", phone=" + phone + ", active=" + active
				+ ", neighborhoodId=" + neighborhoodId + ", street=" + street + ", nInside=" + nInside + ", nExterior="
				+ nExterior + "]";
	}

}
