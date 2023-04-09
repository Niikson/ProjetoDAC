package managedBeans;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import entidades.Pessoa;
import enums.Categoria;
import enums.Genero;
import enums.Grupo;
import enums.TipoDeCompra;
import enums.TipoLoja;
import enums.TipoSetor;
import filters.PessoaFilter;
import services.PessoaService;
import services.ServiceDacException;

public class AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaService pessoaService;
	
	public Grupo[] getGrupos() {
		return Grupo.values();
	}
	
	public Categoria[] getCategorias() {
		return Categoria.values();
	}
	
	public Genero[] getGeneros() {
		return Genero.values();
	}
	
	public TipoLoja[] getTipoLojas() {
		return TipoLoja.values();
	}
	
	public TipoSetor[] getTipoSetores() {
		return TipoSetor.values();
	}
	
	public TipoDeCompra[] getTipoDeCompras() {
		return TipoDeCompra.values();
	}
	
	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe, false);
	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe, true);
	}
	
	private void reportarMensagem(boolean isErro, String detalhe, boolean keepMessages) {
		String sumario = "Success!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Error!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario + " " + detalhe, null);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(keepMessages);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}
	
	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "";
		}
		
		return userPrincipal.getName();
	}
	
	public Pessoa getPessoaLogada() {
		PessoaFilter filter = new PessoaFilter();
		filter.setLogin(getUserLogin());
		List<Pessoa> pessoas = null;
		try {
			pessoas = pessoaService.findBy(filter);
		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao recuperar o usuário logado!");
		}

		if (!pessoas.isEmpty()) {
			return pessoas.get(0);
		}
		
		return null;
	}

}
