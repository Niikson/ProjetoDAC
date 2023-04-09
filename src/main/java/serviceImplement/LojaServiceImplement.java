package serviceImplement;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.LojaDAO;
import entidades.Loja;
import filters.LojaFilter;
import services.LojaService;
import services.ServiceDacException;
import util.TransacionalCdi;

public class LojaServiceImplement implements Serializable, LojaService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LojaDAO lojaDAO;

	@Override
	public List<Loja> findBy(LojaFilter filter) throws ServiceDacException {
		try {
			return lojaDAO.findBy(filter);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Loja loja) throws ServiceDacException {
		try {
			lojaDAO.save(loja);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void update(Loja loja) throws ServiceDacException {
		try {
			lojaDAO.update(loja);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Loja loja) throws ServiceDacException {
		try {
			lojaDAO.delete(loja);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public Loja getByID(Integer id) throws ServiceDacException {
		try {
			return lojaDAO.getByID(id);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Loja> getAll() throws ServiceDacException {
		try {
			return lojaDAO.getAll();
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
