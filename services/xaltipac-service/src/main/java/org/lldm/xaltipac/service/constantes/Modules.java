package org.lldm.xaltipac.service.constantes;

/**
 * 
 * @author Juan Mateo
 *
 */

public class Modules {
	
	private String name;
	private String htmlName;
	private Boolean view;
	private Boolean add;
	private Boolean update;
	private Boolean delete;
	
	public Modules() {
	}
	
	public Modules(String name, Boolean view, Boolean update, Boolean delete,Boolean add) {
		this.name = name;
		this.view = view;
		this.add = add;
		this.update = update;
		this.delete = delete;
	}
	
	public Modules(ModulesEnum modulesEnum) {
		this.htmlName = modulesEnum.getHtmlName();
		this.name  = modulesEnum.getName();
		this.view = false;
		this.add = false;
		this.update = false;
		this.delete = false;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getView() {
		return view;
	}
	public void setView(Boolean view) {
		this.view = view;
	}
	public Boolean getUpdate() {
		return update;
	}
	public void setUpdate(Boolean update) {
		this.update = update;
	}
	public Boolean getDelete() {
		return delete;
	}
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Boolean getAdd() {
		return add;
	}

	public void setAdd(Boolean add) {
		this.add = add;
	}

	@Override
	public String toString() {
		return "Modules [name=" + name + ", view=" + view + ", add=" + add
				+ ", update=" + update + ", delete=" + delete + "]";
	}

	public String getHtmlName() {
		return htmlName;
	}

	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}
	
}
