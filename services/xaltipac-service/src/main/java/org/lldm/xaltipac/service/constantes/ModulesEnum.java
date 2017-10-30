package org.lldm.xaltipac.service.constantes;


/**
 * Constantes para los modulos a agregar.
 * @author Juan Mateo.
 *
 */
public enum ModulesEnum {
	USERS(1,"USUARIOS","USUARIOS"),
	CATEGORIES(2,"CATEGORIAS","CATEGORÍAS"),
	BUSINESS(3,"EMPRESAS","EMPRESAS"),
	ENGINES(4,"MOTORES","MOTORES"),
	ALGORITHMS(5,"ALGORITMOS","ALGORITMOS"),
	PROFILES(6,"PERFILES","PERFILES"),
	SUBCATEGORIES(7,"SUBCATEGORIAS","SUBCATEGORÍAS"),
	INFORMATIONS(8,"INFORMACION","INFORMACIÓN");
	
	
	
	private Integer id;
	private String name;
	private String htmlName;
	
	private ModulesEnum(Integer id,String name, String htmlName) {
		this.id = id;
		this.name = name;
		this.htmlName = htmlName;
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
	public String getHtmlName() {
		return htmlName;
	}
	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}

	
}
