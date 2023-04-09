package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entidades.Loja;
import filters.LojaFilter;

public class LojaDAO extends DAO<Loja> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Loja> findBy(LojaFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Loja> resultado = new ArrayList<>();

		try {
			String jpql = "SELECT u FROM Loja u WHERE 1 = 1 and u.pessoa = " + filter.getId()+ " ";
			// Nome
			if (notEmpty(filter.getNome()))
				jpql += "AND u.nome LIKE :nome ";

			// Tipo
			if (notEmpty(filter.getTipoLoja()))
				jpql += "AND u.tipoLoja = :tipoLoja ";
			

			TypedQuery<Loja> query = em.createQuery(jpql, Loja.class);

			// Nome
			if (notEmpty(filter.getNome()))
				query.setParameter("nome", "%" + filter.getNome() + "%");

			// Tipo
			if (notEmpty(filter.getTipoLoja()))
				query.setParameter("tipoLoja", filter.getTipoLoja());

			resultado = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar as lojas.", e);
		}
		return resultado;
	}

	@Override
	public void save(Loja object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a loja.", pe);
		}
	}

	@Override
	public void update(Loja object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.merge(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a loja.", pe);
		}
	}

	@Override
	public void delete(Loja object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			object = em.find(Loja.class, object.getId());
			em.remove(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a loja.", pe);
		}
	}

	@Override
	public Loja getByID(Integer id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Loja resultado = null;
		try {
			resultado = em.find(Loja.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a loja com base no ID.", pe);
		}
		return resultado;
	}

	@Override
	public List<Loja> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

}
