package beansPessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidades.Pessoa;
import managedBeans.AbstractBean;
import managedBeans.EnderecoPaginas;
import services.PessoaService;
import services.ServiceDacException;

@Named
@ViewScoped
public class PessoaEditSenha extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	
	private String confirmacaoPasswordAtual;

	private String newPassword;
	
	@Inject
	private PessoaService pessoaService;
	
	public String changePassword() {

		try {
			// Confirmar que senha atual equivale a armazenada
			if (!senhaAtualConfere()) {
				reportarMensagemDeErro("Your current password is missing or incorrect!");
				return null; // Permanecer na mesma página
			}

			pessoa.setSenha(getNewPassword());
			pessoaService.update(pessoa, true);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Password sucessfully changed for user '" + pessoa.getNome() + "'");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PESSOA;
	}

	private boolean senhaAtualConfere() throws ServiceDacException {
		return pessoaService.senhaConfere(this.pessoa, getConfirmacaoPasswordAtual());
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getConfirmacaoPasswordAtual() {
		return confirmacaoPasswordAtual;
	}

	public void setConfirmacaoPasswordAtual(String confirmacaoPasswordAtual) {
		this.confirmacaoPasswordAtual = confirmacaoPasswordAtual;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
