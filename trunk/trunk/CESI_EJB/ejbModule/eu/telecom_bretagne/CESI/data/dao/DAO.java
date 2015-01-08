package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

public interface DAO<T> {

	public abstract T create(T entity);

	public abstract T findById(int id);

	public abstract T update(T entity);

	public abstract void delete(T entity);
	
	public abstract List<T> findAll();
}
