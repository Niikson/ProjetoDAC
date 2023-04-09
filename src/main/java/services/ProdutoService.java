package services;

import java.util.List;

import entidades.Produto;
import filters.ProdutoFilter;

public interface ProdutoService {
	
	List<Produto> findBy(ProdutoFilter filter) throws ServiceDacException;
	
	List<Produto> findByEspecifico(ProdutoFilter filter) throws ServiceDacException;
	
	void save(Produto produto) throws ServiceDacException;
	
	void update(Produto produto) throws ServiceDacException;
	
	void delete(Produto produto) throws ServiceDacException;
	
	Produto getByID(Integer id) throws ServiceDacException;
	
	List<Produto> getAll() throws ServiceDacException;

}
