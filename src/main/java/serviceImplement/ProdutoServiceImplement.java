package serviceImplement;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.ProdutoDAO;
import entidades.Produto;
import filters.ProdutoFilter;
import services.ProdutoService;
import services.ServiceDacException;
import util.TransacionalCdi;

public class ProdutoServiceImplement implements Serializable, ProdutoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDAO produtoDAO;

	@Override
	public List<Produto> findBy(ProdutoFilter filter) throws ServiceDacException {
		try {
			return produtoDAO.findBy(filter);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Produto> findByEspecifico(ProdutoFilter filter) throws ServiceDacException {
		try {
			return produtoDAO.findByEspecifico(filter);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Produto produto) throws ServiceDacException {
		try {
			produtoDAO.save(produto);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void update(Produto produto) throws ServiceDacException {
		try {
			produtoDAO.update(produto);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Produto produto) throws ServiceDacException {
		try {
			produtoDAO.delete(produto);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public Produto getByID(Integer id) throws ServiceDacException {
		try {
			return produtoDAO.getByID(id);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Produto> getAll() throws ServiceDacException {
		try {
			return produtoDAO.getAll();
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
