package org.lldm.xaltipac.service;

import java.io.Serializable;

import org.lldm.xaltipac.data.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author Juan Mateo Sauce
 *
 */

public interface BaseService<T extends BaseEntity, ID extends Serializable>{

	T save(T entity);
	Iterable<T> save(Iterable<T> entities);
	T findOne(ID id);
	boolean exists(ID id);
	Iterable<T> findAll();
	Iterable<T> findAll(Iterable<ID> ids);
	long count();
	void delete(ID id);
	void delete(T entity);
	void delete(Iterable<T> entities);
	void deleteAll();
	public Page<T> findAll(Pageable pageable);
}
