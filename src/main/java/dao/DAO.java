package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class DAO<O> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public abstract void save(O object) throws PersistenciaDacException;
	
	public abstract void update(O object) throws PersistenciaDacException;

	public abstract void delete(O object) throws PersistenciaDacException;
	
	public abstract O getByID(Integer id) throws PersistenciaDacException;
	
	public abstract List<O> getAll() throws PersistenciaDacException;
	
	protected boolean contains(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1 == null || s2 == null) {
			return false;
		}

		return s1.toUpperCase().contains(s2.toUpperCase());
	}

	protected boolean notEmpty(Object obj) {
		return obj != null;
	}

	protected boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
	
	protected boolean empty(Object obj) {
		return obj == null;
	}

	protected boolean inter(Integer id, Integer id2) {
		return id == id2;
	}

}
