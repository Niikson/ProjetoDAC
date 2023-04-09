package beansPessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.PersistenciaDacException;
import entidades.Endereco;
import entidades.Pessoa;
import enums.Grupo;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.PessoaService;
import services.ServiceDacException;

@Named
@ViewScoped
public class PessoaEdit extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaService pessoaService;
	
	private Pessoa pessoa;
	
	public void init() {
		if (pessoa == null) {
			pessoa = new Pessoa();
			pessoa.setEndereco(new Endereco());
		}
		else {
			if (pessoa.getEndereco() == null)
				pessoa.setEndereco(new Endereco());
		}
	}
	
	public String savePessoa() {
		try {
			if (isEdicaoDePessoa())
				pessoaService.update(pessoa, false);
			else
				pessoaService.save(pessoa);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Pessoa '" + pessoa.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}

	public Pessoa selecionar() {
		try {
			pessoa = pessoaService.getByID(pessoa.getId());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}
	
	public void salvarEndereco() {
		reportarMensagemDeSucesso(String.format("Endereço '%s' is saved.", pessoa.getEndereco().getLogradouro()));
	}

	public String paginaPrincipal() {
		return EnderecoPaginas.PAGINA_PRINCIPAL_PESSOA;
	}

	public String criarPessoa() {
		return EnderecoPaginas.PAGINA_PESSOA_CREATE;
	}

	public boolean isPessoaSelecionado() {
		return pessoa != null;
	}
	
	public boolean isProprietario() {
		return pessoa != null && pessoa.getGrupo() == Grupo.PROPRIETARIO;
	}

	public boolean isCliente() {
		return pessoa != null && pessoa.getGrupo() == Grupo.CLIENTE;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isEdicaoDePessoa() {
		return pessoa.getId() != null;
	}
	
	public boolean isEdicaoDePessoaLogada() {
		return getPessoaLogada().getId() != null;
	}
	
	public void checarDisponibilidadeLogin() {
		try {
			pessoaService.validarLogin(pessoa);
			reportarMensagemDeSucesso(String.format("Login '%s' is available.", pessoa.getLogin()));
		} catch (ServiceDacException | PersistenciaDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public void checarDisponibilidadeEmail() {
		try {
			pessoaService.validarEmail(pessoa);
			reportarMensagemDeSucesso(String.format("E-mail '%s' is available.", pessoa.getEmail()));
		} catch (ServiceDacException | PersistenciaDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

}
