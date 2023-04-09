package services;

import java.util.List;

import dao.PersistenciaDacException;
import entidades.Pessoa;
import filters.PessoaFilter;

public interface PessoaService {
	
	List<Pessoa> findBy(PessoaFilter filter) throws ServiceDacException;
	
	void save(Pessoa pessoa) throws ServiceDacException;
	
	void update(Pessoa pessoa, boolean passwordChanged) throws ServiceDacException;
	
	void delete(Pessoa pessoa) throws ServiceDacException;
	
	Pessoa getByID(Integer id) throws ServiceDacException;
	
	List<Pessoa> getAll() throws ServiceDacException;
	
	boolean existPessoaLogin(Pessoa pessoa) throws ServiceDacException;
	
	boolean existPessoaEmail(Pessoa pessoa) throws ServiceDacException;
	
	boolean senhaConfere(Pessoa pessoa, String password2) throws ServiceDacException;
	
	void validarLogin(Pessoa pessoa)throws ServiceDacException, PersistenciaDacException;
	
	void validarEmail(Pessoa pessoa)throws ServiceDacException, PersistenciaDacException;

}
