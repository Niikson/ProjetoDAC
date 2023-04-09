package serviceImplement;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.TelefoneDAO;
import entidades.Telefone;
import filters.TelefoneFilter;
import services.ServiceDacException;
import services.TelefoneService;
import util.TransacionalCdi;

public class TelefoneServiceImplement implements Serializable, TelefoneService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TelefoneDAO telefoneDAO;

	@Override
	public List<Telefone> findBy(TelefoneFilter filter) throws ServiceDacException {
		try {
			return telefoneDAO.findBy(filter);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Telefone telefone) throws ServiceDacException {
		try {
			telefoneDAO.save(telefone);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void update(Telefone telefone) throws ServiceDacException {
		try {
			telefoneDAO.update(telefone);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Telefone telefone) throws ServiceDacException {
		try {
			telefoneDAO.delete(telefone);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public Telefone getByID(Integer id) throws ServiceDacException {
		try {
			return telefoneDAO.getByID(id);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Telefone> getAll() throws ServiceDacException {
		try {
			return telefoneDAO.getAll();
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
