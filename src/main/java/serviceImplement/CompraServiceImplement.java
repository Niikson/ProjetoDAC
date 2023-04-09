package serviceImplement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dao.CompraDAO;
import entidades.Compra;
import filters.CompraFilter;
import services.CompraService;
import services.ServiceDacException;
import util.TransacionalCdi;

public class CompraServiceImplement implements Serializable, CompraService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CompraDAO compraDAO;

	@Override
	public List<Compra> findBy(CompraFilter filter) throws ServiceDacException {
		try {
			return compraDAO.findBy(filter);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Compra compra) throws ServiceDacException {
		try {
			compra.limparCamposEspecificos();
			compra.setData(pegarHora());
			compra.setTotal(compra.getProduto().getPreco() * compra.getQuantidade());
			compraDAO.save(compra);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void update(Compra compra) throws ServiceDacException {
		try {
			compra.limparCamposEspecificos();
			compraDAO.update(compra);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Compra compra) throws ServiceDacException {
		try {
			compraDAO.delete(compra);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public Compra getByID(Integer id) throws ServiceDacException {
		try {
			return compraDAO.getByID(id);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Compra> getAll() throws ServiceDacException {
		try {
			return compraDAO.getAll();
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}
	
	public String pegarHora() {
		Date data_hora = new Date(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String str=fmt.format(data_hora);
		return str;
	}

}
