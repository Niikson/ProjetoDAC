package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entidades.Pessoa;
import filters.PessoaFilter;

public class PessoaDAO extends DAO<Pessoa>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Pessoa> findBy(PessoaFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Pessoa> resultado = new ArrayList<>();

		try {
			String jpql = "SELECT u FROM Pessoa u WHERE 1 = 1 ";
			// Nome
			if (notEmpty(filter.getNome()))
				jpql += "AND u.nome LIKE :nome ";
			
			// Login
			if (notEmpty(filter.getLogin()))
				jpql += "AND u.login LIKE :login ";
			
			// Grupo
			if (notEmpty(filter.getGrupo()))
				jpql += "AND u.grupo = :grupo ";

			TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);

			// Nome
			if (notEmpty(filter.getNome()))
				query.setParameter("nome", "%" + filter.getNome() + "%");
			
			// Login
			if (notEmpty(filter.getLogin()))
				query.setParameter("login", "%" + filter.getLogin() + "%");
			
			// Grupo
			if (notEmpty(filter.getGrupo()))
				query.setParameter("grupo", filter.getGrupo());

			resultado = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar as pessoas.", e);
		}
		return resultado;
	}

	@Override
	public void save(Pessoa object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a pessoa.", pe);
		}
	}

	@Override
	public void update(Pessoa object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.merge(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a pessoa.", pe);
		}
	}

	@Override
	public void delete(Pessoa object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			object = em.find(Pessoa.class, object.getId());
			em.remove(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a pessoa.", pe);
		}
	}

	@Override
	public Pessoa getByID(Integer id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pessoa resultado = null;
		try {
			resultado = em.find(Pessoa.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a pessoa com base no ID.",
					pe);
		}
		return resultado;
	}

	@Override
	public List<Pessoa> getAll() throws PersistenciaDacException {
		return findBy(null);
	}
	
	public boolean existPessoaLogin(Pessoa object) throws PersistenciaDacException {
		if (empty(object) || empty(object.getLogin()))
			return false;

		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM Pessoa u WHERE u.login = :login ";
		if (notEmpty(object.getId()))
			jpql += "AND u != :pessoa ";

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("login", object.getLogin());
		if (notEmpty(object.getId()))
			query.setParameter("pessoa", object);

		Long count = query.getSingleResult();
		return count > 0;
	}
	
	public boolean existPessoaEmail(Pessoa object) throws PersistenciaDacException {
		if (empty(object) || empty(object.getEmail()))
			return false;
		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM Pessoa u WHERE u.email = :email ";
		if (notEmpty(object.getId()))
			jpql += "AND u != :pessoa ";

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("email", object.getEmail());
		if (notEmpty(object.getId()))
			query.setParameter("pessoa", object);

		Long count = query.getSingleResult();
		return count > 0;
	}

}
