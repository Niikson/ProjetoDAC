package beansTelefone;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Pessoa;
import entidades.Telefone;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.ServiceDacException;
import services.TelefoneService;

@Named
@ViewScoped
public class TelefoneEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TelefoneService telefoneService;

	private Telefone telefone;

	private Pessoa pessoa;

	public void init() {
		if (telefone == null) {
			telefone = new Telefone();
		}
	}

	public String saveTelefone() {
		try {
			if (isEdicaoDeTelefone())
				telefoneService.update(telefone);
			else {
				telefone.setPessoa(getPessoaLogada());
				telefoneService.save(telefone);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Telefone '" + telefone.getNumero() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_TELEFONE;
	}

	public Telefone selecionar() {
		try {
			telefone = telefoneService.getByID(telefone.getId());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String paginaPrincipal() {
		return EnderecoPaginas.PAGINA_PRINCIPAL_TELEFONE;
	}

	public String criarTelefone() {
		return EnderecoPaginas.PAGINA_TELEFONE_CREATE;
	}

	public boolean isTelefoneSelecionado() {
		return telefone != null;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public boolean isEdicaoDeTelefone() {
		return telefone.getId() != null;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
