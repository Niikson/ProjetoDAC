package serviceImplement;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import dao.PersistenciaDacException;
import dao.PessoaDAO;
import entidades.Pessoa;
import filters.PessoaFilter;
import services.PessoaService;
import services.ServiceDacException;
import util.TransacionalCdi;

public class PessoaServiceImplement implements Serializable, PessoaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaDAO pessoaDAO;

	@Override
	public List<Pessoa> findBy(PessoaFilter filter) throws ServiceDacException {
		try {
			return pessoaDAO.findBy(filter);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void save(Pessoa pessoa) throws ServiceDacException {
		try {
			validarLogin(pessoa);
			validarEmail(pessoa);
			pessoa.limparCamposEspecificos();
			calcularHashSenha(pessoa);
			pessoaDAO.save(pessoa);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void update(Pessoa pessoa, boolean passwordChanged) throws ServiceDacException {
		try {
			validarLogin(pessoa);
			validarEmail(pessoa);
			pessoa.limparCamposEspecificos();
			if (passwordChanged)
				calcularHashSenha(pessoa);
			pessoaDAO.update(pessoa);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	@TransacionalCdi
	public void delete(Pessoa pessoa) throws ServiceDacException {
		try {
			pessoaDAO.delete(pessoa);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public Pessoa getByID(Integer id) throws ServiceDacException {
		try {
			return pessoaDAO.getByID(id);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Pessoa> getAll() throws ServiceDacException {
		try {
			return pessoaDAO.getAll();
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public boolean existPessoaLogin(Pessoa pessoa) throws ServiceDacException {
		try {
			return pessoaDAO.existPessoaLogin(pessoa);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	private String hash(String senha) throws ServiceDacException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(senha.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			return output;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new ServiceDacException("Could not calculate hash!", e);
		}
	}

	private String calcularHashSenha(Pessoa pessoa) throws ServiceDacException {
		pessoa.setSenha(hash(pessoa.getSenha()));
		return pessoa.getSenha();
	}

	@Override
	public boolean existPessoaEmail(Pessoa pessoa) throws ServiceDacException {
		try {
			return pessoaDAO.existPessoaEmail(pessoa);
		} catch (Exception e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public boolean senhaConfere(Pessoa pessoa, String password2) throws ServiceDacException {
		// Recuperar verdadeira senha atual (hash)
		String senhaHash = null;
		try {
			senhaHash = pessoaDAO.getByID(pessoa.getId()).getSenha();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

		// Programação defensiva contra NPE
		if (senhaHash == null && password2 == null) {
			return true;
		}

		if (senhaHash == null || password2 == null) {
			return false;
		}

		// Comparar hash da suposta senha com o verdadeiro hash da senha
		String supostaSenhaHash = hash(password2);

		return senhaHash.equals(supostaSenhaHash);
	}

	public void validarLogin(Pessoa pessoa) throws ServiceDacException, PersistenciaDacException {
		boolean jahExiste = pessoaDAO.existPessoaLogin(pessoa);
		if (jahExiste) {
			throw new ServiceDacException("Login already exists: " + pessoa.getLogin());
		}
	}

	@Override
	public void validarEmail(Pessoa pessoa) throws ServiceDacException, PersistenciaDacException {
		boolean jahExiste = pessoaDAO.existPessoaEmail(pessoa);
		if (jahExiste)
			throw new ServiceDacException("E-mail already exists: " + pessoa.getEmail());
	}

}
