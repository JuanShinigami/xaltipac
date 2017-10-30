package org.lldm.xaltipac.data.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * Java bean to represent an entity  with id an name attributes
 * @author Juan Mateo Sauce.
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {
	
	
    @Column(name = "name")
    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
