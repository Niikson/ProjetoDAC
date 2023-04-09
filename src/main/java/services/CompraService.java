package services;

import java.util.List;

import entidades.Compra;
import filters.CompraFilter;

public interface CompraService {
	
	List<Compra> findBy(CompraFilter filter) throws ServiceDacException;
	
	void save(Compra compra) throws ServiceDacException;
	
	void update(Compra compra) throws ServiceDacException;
	
	void delete(Compra compra) throws ServiceDacException;
	
	Compra getByID(Integer id) throws ServiceDacException;
	
	List<Compra> getAll() throws ServiceDacException;

}
