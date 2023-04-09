package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entidades.Produto;
import filters.ProdutoFilter;

public class ProdutoDAO extends DAO<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Produto> findBy(ProdutoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Produto> resultado = new ArrayList<>();

		try {
			String jpql = "SELECT u FROM Produto u WHERE 1 = 1 ";
			// Nome
			if (notEmpty(filter.getNome()))
				jpql += "AND u.nome LIKE :nome ";

			// Tamanho
			if (notEmpty(filter.getTamanho()))
				jpql += "AND u.tamanho LIKE :tamanho ";

			// Preco
			if (notEmpty(filter.getPreco()))
				jpql += "AND u.preco = :preco ";

			// Categoria
			if (notEmpty(filter.getCategoria()))
				jpql += "AND u.categoria = :categoria ";

			// Genero
			if (notEmpty(filter.getGenero()))
				jpql += "AND u.genero = :genero";

			// Loja do produto
			if (notEmpty(filter.getNomeLoja()))
				jpql += "AND u.loja.nome LIKE :nomeLoja ";

			TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

			// Nome
			if (notEmpty(filter.getNome()))
				query.setParameter("nome", "%" + filter.getNome() + "%");

			// Tamanho
			if (notEmpty(filter.getTamanho()))
				query.setParameter("tamanho", "%" + filter.getTamanho() + "%");

			// Preco
			if (notEmpty(filter.getPreco()))
				query.setParameter("preco", filter.getPreco());

			// Categoria
			if (notEmpty(filter.getCategoria()))
				query.setParameter("categoria", filter.getCategoria());

			// Genero
			if (notEmpty(filter.getGenero()))
				query.setParameter("genero", filter.getGenero());

			// Loja do produto
			if (notEmpty(filter.getNomeLoja()))
				query.setParameter("nomeLoja", "%" + filter.getNomeLoja() + "%");

			resultado = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os produtos.", e);
		}
		return resultado;
	}
	
	public List<Produto> findByEspecifico(ProdutoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Produto> resultado = new ArrayList<>();

		try {
			String jpql = "SELECT u FROM Produto u WHERE 1 = 1 and u.loja.pessoa = " + filter.getId()+ " ";
			// Nome
			if (notEmpty(filter.getNome()))
				jpql += "AND u.nome LIKE :nome ";

			// Tamanho
			if (notEmpty(filter.getTamanho()))
				jpql += "AND u.tamanho LIKE :tamanho ";

			// Preco
			if (notEmpty(filter.getPreco()))
				jpql += "AND u.preco = :preco ";

			// Categoria
			if (notEmpty(filter.getCategoria()))
				jpql += "AND u.categoria = :categoria ";

			// Genero
			if (notEmpty(filter.getGenero()))
				jpql += "AND u.genero = :genero";

			// Loja do produto
			if (notEmpty(filter.getNomeLoja()))
				jpql += "AND u.loja.nome LIKE :nomeLoja ";

			TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

			// Nome
			if (notEmpty(filter.getNome()))
				query.setParameter("nome", "%" + filter.getNome() + "%");

			// Tamanho
			if (notEmpty(filter.getTamanho()))
				query.setParameter("tamanho", "%" + filter.getTamanho() + "%");

			// Preco
			if (notEmpty(filter.getPreco()))
				query.setParameter("preco", filter.getPreco());

			// Categoria
			if (notEmpty(filter.getCategoria()))
				query.setParameter("categoria", filter.getCategoria());

			// Genero
			if (notEmpty(filter.getGenero()))
				query.setParameter("genero", filter.getGenero());

			// Loja do produto
			if (notEmpty(filter.getNomeLoja()))
				query.setParameter("nomeLoja", "%" + filter.getNomeLoja() + "%");

			resultado = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os produtos.", e);
		}
		return resultado;
	}

	@Override
	public void save(Produto object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.persist(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o produto.", pe);
		}
	}

	@Override
	public void update(Produto object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			em.merge(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o produto.", pe);
		}
	}

	@Override
	public void delete(Produto object) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		try {
			object = em.find(Produto.class, object.getId());
			em.remove(object);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o produto.", pe);
		}
	}

	@Override
	public Produto getByID(Integer id) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Produto resultado = null;
		try {
			resultado = em.find(Produto.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o produto com base no ID.", pe);
		}
		return resultado;
	}

	@Override
	public List<Produto> getAll() throws PersistenciaDacException {
		return findBy(null);
	}

}
