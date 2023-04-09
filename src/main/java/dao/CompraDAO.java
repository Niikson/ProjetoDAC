package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entidades.Compra;
import filters.CompraFilter;

public class CompraDAO extends DAO<Compra> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Compra> findBy(CompraFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Compra> resultado = new ArrayList<>();

		try {
			String jpql = "SELECT u FROM Compra u WHERE 1 = 1 and u.pessoa = " + filter.getId()+ " ";
			// Data
			if (notEmpty(filter.getData()))
				jpql += "AND u.data LIKE :data ";

			// Produto
			if (notEmpty(filter.getNomeProduto()))
				jpql += "AND u.produto.nome LIKE :nomeProduto ";

			// Tipo de compra
			if (notEmpty(filter.getTipoDeCompra()))
				jpql += "AND u.tipoDeCompra = :tipoDeCompra ";

			// Loja do produto
			if (notEmpty(filter.getNomeLoja()))
				jpql += "AND u.produto.loja.nome LIKE :nomeLoja ";

			TypedQuery<Compra> query = em.createQuery(jpql, Compra.class);

			// Data
			if (notEmpty(filter.getData()))
				query.setParameter("data", "%" + filter.getData() + "%");

			// Produto
			if (notEmpty(filter.getNomeProduto()))
				query.setParameter("nomeProduto", "%" + filter.getNomeProduto() + "%");

			// Tipo de compra
			if (notEmpty(filter.getTipoDeCompra()))
				query.setParameter("tipoDeCompra", filter.getTipoDeCompra());

			// Loja do produto
			if (notEmpty(filter.getNomeLoja()))
				query.setParameter("nomeLoja", "%" + filter.getNomeLoja() + "%");

			resultado = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar as compras.", e);
		}
		return resultado;
	}

	@Override
	public void save(Compra object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a compra.", pe);
		}
	}

	@Override
	public void update(Compra object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.merge(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar a compra.", pe);
		}
	}

	@Override
	public void delete(Compra object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			object = em.find(Compra.class, object.getId());
			em.remove(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover a compra.", pe);
		}
	}

	@Override
	public Compra getByID(Integer id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Compra resultado = null;
		try {
			resultado = em.find(Compra.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar a compra com base no ID.", pe);
		}
		return resultado;
	}

	@Override
	public List<Compra> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

}
