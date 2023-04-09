package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entidades.Telefone;
import filters.TelefoneFilter;

public class TelefoneDAO extends DAO<Telefone> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Telefone> findBy(TelefoneFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Telefone> resultado = new ArrayList<>();

		try {
			String jpql = "SELECT u FROM Telefone u WHERE 1 = 1 and u.pessoa = " + filter.getId()+ " ";
			// DDD
			if (notEmpty(filter.getDDD()))
				jpql += "AND u.DDD = :DDD ";

			// Numero
			if (notEmpty(filter.getNumero()))
				jpql += "AND u.numero = :numero ";

			TypedQuery<Telefone> query = em.createQuery(jpql, Telefone.class);

			// DDD
			if (notEmpty(filter.getDDD()))
				query.setParameter("DDD", filter.getDDD());

			// Numero
			if (notEmpty(filter.getNumero()))
				query.setParameter("numero", filter.getNumero());

			resultado = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os telefones.", e);
		}
		return resultado;
	}

	@Override
	public void save(Telefone object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o telefone.", pe);
		}
	}

	@Override
	public void update(Telefone object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.merge(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o telefone.", pe);
		}
	}

	@Override
	public void delete(Telefone object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			object = em.find(Telefone.class, object.getId());
			em.remove(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o telefone.", pe);
		}
	}

	@Override
	public Telefone getByID(Integer id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Telefone resultado = null;
		try {
			resultado = em.find(Telefone.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o telefone com base no ID.", pe);
		}
		return resultado;
	}

	@Override
	public List<Telefone> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

}
