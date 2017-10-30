package org.lldm.xaltipac.service.constantes;

public enum ActionsEnum {
	ADD(1),
	VIEW(2),
	UPDATE(3),
	DELETE(4);
	
	private Integer id;

	private ActionsEnum(Integer id) {
		this.id = id;
	} 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
