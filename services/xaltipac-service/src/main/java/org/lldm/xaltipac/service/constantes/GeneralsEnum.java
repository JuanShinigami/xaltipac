package org.lldm.xaltipac.service.constantes;
/**
 * Clase de constantes generales
 * @author Juan Mateo.
 *
 */
public enum GeneralsEnum {
	SUPER_USER(1),
	PROFILE_SUPER_ADMIN(1),
	PROFILE_SUPER_ADMIN_BUSSINES(2),
	lOGIC_DELETE(1);
	
	private int id;
	
	private GeneralsEnum(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	}
