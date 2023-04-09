package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entidades.Report;
import filters.ReportFilter;

public class ReportDAO extends DAO<Report> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Report> findBy(ReportFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Report> resultado = new ArrayList<>();

		try {
			String jpql = "SELECT u FROM Report u WHERE 1 = 1 ";
			// Data
			if (notEmpty(filter.getData()))
				jpql += "AND u.data LIKE :data ";

			// Nome do usuario
			if (notEmpty(filter.getNomeUsuario()))
				jpql += "AND u.pessoa.nome LIKE :nomeUsuario ";

			TypedQuery<Report> query = em.createQuery(jpql, Report.class);

			// Data
			if (notEmpty(filter.getData()))
				query.setParameter("data", "%" + filter.getData() + "%");

			// Nome do usuario
			if (notEmpty(filter.getNomeUsuario()))
				query.setParameter("nomeUsuario", "%" + filter.getNomeUsuario() + "%");

			resultado = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os reports.", e);
		}
		return resultado;
	}

	@Override
	public void save(Report object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o report.", pe);
		}
	}

	@Override
	public void update(Report object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.merge(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o report.", pe);
		}
	}

	@Override
	public void delete(Report object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			object = em.find(Report.class, object.getId());
			em.remove(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o report.", pe);
		}
	}

	@Override
	public Report getByID(Integer id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Report resultado = null;
		try {
			resultado = em.find(Report.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o report com base no ID.", pe);
		}
		return resultado;
	}

	@Override
	public List<Report> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

}
