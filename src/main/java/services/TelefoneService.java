package services;

import java.util.List;

import entidades.Telefone;
import filters.TelefoneFilter;

public interface TelefoneService {

	List<Telefone> findBy(TelefoneFilter filter) throws ServiceDacException;

	void save(Telefone telefone) throws ServiceDacException;

	void update(Telefone telefone) throws ServiceDacException;

	void delete(Telefone telefone) throws ServiceDacException;

	Telefone getByID(Integer id) throws ServiceDacException;

	List<Telefone> getAll() throws ServiceDacException;

}
