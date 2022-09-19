package com.dbtablemonitor.model;

import java.util.List;

public interface Repository<T, ID> {

	//public void flush();

	public T save(T entity);

	public List<T> saveAll(List<T> entities);

	public List<T> findAll();

	public T findById(ID id);

	public void delete(T entity);

	public void deleteById(ID id);

	public void deleteAll();

	public void deleteAllById(List<ID> ids);

	public void deleteAll(List<T> entities);

}
