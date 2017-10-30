package org.lldm.xaltipac.service.impl;

import java.io.Serializable;

import org.lldm.xaltipac.data.model.BaseEntity;
import org.lldm.xaltipac.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;



/**
 * Clase creada para no tener que repetir codigo de implementaciones de servicio tantas veces
 * @author Juan Mateo Sauce. 
 */
public abstract class BaseServiceImpl<T extends BaseEntity,ID extends Serializable> implements BaseService<T,ID>{

	protected CrudRepository<T,ID> repository;
	
	/**
	 * Es necesario que en este metodo asignemos el valor de nuestra inyeccion de  spring a la variable
	 * repository 
	 */
	public abstract void init();
	
	
	@Transactional(readOnly = true)
	public T save(T entity){
		init();
		return repository.save(entity);
	}

	
	public Iterable<T> save(Iterable<T> entities) {
		init();
		return repository.save(entities);
	}

	@Transactional(readOnly = true)
	public T findOne(ID id) {
		init();
		return repository.findOne(id);
	}

	
	public boolean exists(ID id) {
		init();
		return repository.exists(id);
	}

	
	@Transactional(readOnly = true)
	public Iterable<T> findAll() {
		init();
		return repository.findAll();
	}

	
	public Iterable<T> findAll(Iterable<ID> ids) {
		init();
		return repository.findAll(ids);
	}

	
	public long count() {
		init();
		return repository.count();
	}

	public void delete(ID id) {
		init();
		repository.delete(id);
	}

	
	public void delete(T entity) {
		init();
		repository.delete(entity);
	}

	
	public void delete(Iterable<T> entities) {
		init();
		repository.delete(entities);
	}

	public void deleteAll() {
		init();
		repository.deleteAll();
	}
	
	
	public Page<T> findAll(Pageable pageable){
		init();
	 return ((PagingAndSortingRepository<T, ID>)repository).findAll(pageable);	
	}


}
