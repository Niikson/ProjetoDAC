package services;

import java.util.List;

import entidades.Loja;
import filters.LojaFilter;

public interface LojaService {
	
	List<Loja> findBy(LojaFilter filter) throws ServiceDacException;
	
	void save(Loja loja) throws ServiceDacException;
	
	void update(Loja loja) throws ServiceDacException;
	
	void delete(Loja loja) throws ServiceDacException;
	
	Loja getByID(Integer id) throws ServiceDacException;
	
	List<Loja> getAll() throws ServiceDacException;

}
